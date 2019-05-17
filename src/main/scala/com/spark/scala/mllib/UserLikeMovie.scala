package com.spark.scala.mllib

import org.apache.spark.{SparkConf, SparkContext}

import scala.io.Source
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD

/**
  * 用户、电影的协同过滤算法<br/>
  * 协同过滤算法使用spark mllib包中自带的ALS(最小二乘法)算法
  * 最小二乘法原理:
  *  假定我们存在一个用户对商品的评分矩阵R[m*n]
  * U/V      item1  item2  item3  ...
  * user1     score1
  * user2                   score2
  * user3     score3
  * ...
  *
  * 我们假定商品item有4种隐藏特征,那么上述矩阵可以转换为:
  * 用户和特征的矩阵U[m*k]
  * U/特征    feature1 feature2  feature3
  * user1      score1
  * user2                score2
  * user3                            score3
  *
  * 商品和特征的矩阵V[n*k]
  *  V/特征   feature1   feature2   feature3
  *  product1   score1
  *  product2              ...
  *  product3
  *
  *  我们假设商品特征数远远小于用户数和商品数,那么
  *  R[m*n]==Um*k * Vn*k[T]
  *  所以预测Rm*n矩阵就变成了求解Um*k和Vn*k
  *
  *  我们再进一步假定只要Rm*n和Um*k * Vn*k[T]的误差在允许范围内即可
  *  误差C = (Aij-UiVj[T])*(Aij-UiVj[T]) (i从0-用户的个数,j从0到商品的个数),为了避免过度拟合,引入正则化因子x(x>0)  Aij是用户i对商品j的打分
  *  引入后
  *  C=(Aij-UiVj[T])*(Aij-UiVj[T])+x(||Ui||*||Ui||+||Vi||*||Vi||) //||Ui||表示K维向量的模
  *  根据多元函数求导规则
  *  Un*k[T] = (xEk*k+Um*k[T]Um*k)-1Um*k[T]Rm*n
  *  Vm*k[T] = (xEk*k+Vn*K[T]Vn*k)-1Vn*k[T]Rm*n[T]
  *
  *  计算步骤现在正式开始:
  *  1、固定住隐藏因子个数rank,参数x(过拟合因子),迭代总次数numIter
  *     随机产生Um*k
  *  2、将Um*k代入步骤Vm*k[T] = (xEk*k+Vn*K[T]Vn*k)-1Vn*k[T]Rm*n[T]获取Vn*k
  *  3、再将Vn*k代入Un*k[T] = (xEk*k+Um*k[T]Um*k)-1Um*k[T]Rm*n获取Un*k  完
  *     成了一次迭代
  *  4、循环2、3直到循环次数>=numIter
  *
  *  由于矩阵的乘法很适合用spark的并行化来完成,所以选择了spark的最小二乘法开源实现ALS
  *
  * 以上逻辑参考https://blog.csdn.net/l_15156024189/article/details/81712519
  *
  * @author chenwu on 2019.5.17
  */
object UserLikeMovie {

  /**
    * 获取数据文件,进行模型训练<br/>
    * 最终输出模型和所有的用户ID列表
    *
    * @return (model,allUserIdList)
    * @author chenwu on 2019.5.17
    */
  def initModel() = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("UserLikeMovie")
    val sc = new SparkContext(sparkConf)
    //这里只利用到了用户对电影的评分数据文件
    val userLikeMovieList = loadFile("D:\\data\\ratings.csv")
    //获取时间戳%10,用户ID,电影ID,用户对电影的评分
    val userRatings = userLikeMovieList.map(line => {
      val array = line.split(",")
      //按照时间戳对10求余,进行细分
      val key = array(3).toLong % 10
      //组成时间戳-(用户ID,用户看过的电影,评分)
      (key, Rating(array(0).toInt, array(1).toInt, array(2).toDouble))
    })
    //获取所有用户
    val allUsers = userLikeMovieList.map(line => {
      val movieAndTitle = line.split(",")
      line(0).toInt
    }).distinct.toList
    //对userRatings划分成三等分  60%用于训练,20%用于校验,20%用于测试
    val trainUsers = sc.parallelize(userRatings.filter(_._1 < 6).map(_._2).toList, 4)
    val validationUsers = sc.parallelize(userRatings.filter(x =>
      x._1 >= 6 && x._1 < 8
    ).map(_._2).toList, 4)
    val testUsers = sc.parallelize(userRatings.filter(_._1 >= 8).map(_._2).toList, 4)
    val numValidation = validationUsers.count()
    val numTest = testUsers.count()
    val rank: Int = 8//模型的潜在因素个数，也是矩阵的阶
    val numIter: Int = 10//矩阵分解迭代次数
    val model = ALS.train(trainUsers, rank, numIter)
    val validationRmse = computeRmse(model, validationUsers, numValidation)
    println("RMSE (validation) = " + validationRmse + " for the model trained with rank = "
      + rank + ",numIter = " + numIter + ".")
    // 用最佳模型预测测试集的评分，并计算和实际评分之间的均方根误差
    val testRmse = computeRmse(model, testUsers, numTest)
    println("The model was trained with rank = " + rank + ", and numIter = " + numIter + ", and its RMSE on the test set is " + testRmse + ".")

    (model, allUsers)
  }

  /**
    * 计算哪些用户可能喜欢该电影,以及喜欢该电影的用户可能喜欢哪些其他电影
    *
    * @param model
    * @param allUsers
    * @param movieId
    * @author chenwu on 2019.5.17
    */
  def getWhoLikeThisMovie(model: MatrixFactorizationModel, allUsers: List[Int], movieId: Int) = {
    //我们认为大于4分的用户对该电影感兴趣
    val maybeLikeThisMovieUsers = allUsers.filter(userId => {
      val predictValue = model.predict(userId, movieId)
      //假定预测分值>4,则认为这个用户对该电影感兴趣
      if (predictValue > 4) {
        true
      } else {
        false
      }
    })
    //输出对该电影感兴趣的用户
    println("maybeLikeThisMovieUsers:" + maybeLikeThisMovieUsers)
    //输出这些感兴趣的用户可能喜欢的其他电影,每个用户最多输出10个
    maybeLikeThisMovieUsers.foreach(userId => {
      println("recommend products :" + model.recommendProducts(userId, 10).map(_.product).toList)
    })

  }

  /**
    * 程序的主入口
    *
    * @param args
    * @author chenwu on 2019.5.17
    */
  def main(args: Array[String]): Unit = {
    val inputMovieId = 500 //该参数代表输入的电影ID
    val (model, allUsers) = initModel()
    getWhoLikeThisMovie(model, allUsers, inputMovieId)
    //运行该段代码输出
    /**
      * //表明ID为53的用户对电影感兴趣
      * maybeLikeThisMovieUsers:List(53)
      * //表明ID为53的用户对ID为2328, 5666, 663, 2136, 2990, 5047, 8372, 47423, 800, 2524的电影感兴趣
      * recommend products :List(2328, 5666, 663, 2136, 2990, 5047, 8372, 47423, 800, 2524)
      */
  }

  /**
    * 加载数据文件,并去掉文件的标题行
    *
    * @param fileName
    * @return List[String]
    * @author chenwu on 2019.5.17
    */
  def loadFile(fileName: String) = {
    val lines = Source.fromFile(fileName).getLines()
    //删除第一行标题
    lines.drop(1)
    lines.toList
  }

  /**
    * 校验集预测数据和实际数据之间的均方根误差
    *
    * @param model
    * @param data
    * @param n
    * @return
    * @author chenwu on 2019.5.17
    */
  def computeRmse(model: MatrixFactorizationModel, data: RDD[Rating], n: Long): Double = {
    val predictions: RDD[Rating] = model.predict(data.map(x => (x.user, x.product)))
    val dataRDD = data.map(x => ((x.user, x.product), x.rating))
    val predictionsAndRatings = predictions.map(x => ((x.user, x.product), x.rating))
      .join(dataRDD)
      .values
    if (predictionsAndRatings.isEmpty()) {
      0.0
    } else {
      math.sqrt(predictionsAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / n)
    }

  }
}