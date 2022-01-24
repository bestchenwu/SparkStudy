package nowCode.simple;
//三步问题。有个小孩正在爬楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模100
//0000007。
//
// 示例1:
//
//
// 输入：n = 3
// 输出：4
// 说明: 有四种走法
//
//
// 示例2:
//
//
// 输入：n = 5
// 输出：13
//
//
// 提示:
//
//
// n范围在[1, 1000000]之间
//
public class LeetCode0801 {

    public int waysToStep(int n) {
        if(n<3){
            return n;
        }
        long i = 1,j = 2,k = 4;
        int index = 4;
        while(index<=n){
            long tmp = k;
            k = (i+j+k)%1000000007;
            i = j;
            j = tmp;
            index++;
        }
        return (int)k;
    }
}
