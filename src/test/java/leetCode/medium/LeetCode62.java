package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/unique-paths/
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * 例如输入: m = 3, n = 2
 *
 *   o o o
 *   o o o
 *
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 */
public class LeetCode62 {

    /**
     * 超出了时间限制
     *
     * 假定处于最后一个元素，那么路径条数=F(m,n) = F(m,n-1)+F(m-1,n)
     * 当m,n<=1的时候 可以得出相应的轨技数
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths0(int m, int n) {
        if(n==1 && m==1){
            return 1;
        }
        if(m>1 && n>1){
            return uniquePaths0(m,n-1)+uniquePaths0(m-1,n);
        }else if(m>1){
            return uniquePaths0(m-1,n);
        }else if(n>1){
            return uniquePaths0(m,n-1);
        }else{
            return 0;
        }
    }

    /**
     * 首先根据动态公式F(m,n)=F(m,n-1)+F(n,m-1)得出,
     * 当m=0或者n=0的时候,只能靠边走了,所以路径为1
     * dp[i][j]表示到达行为i,列为j的点需要的步骤
     * 显然dp[i][j]=dp[i-1][j]+dp[i][j-1]
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for(int j = 0;j<n;j++){
            dp[0][j]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=dp[i][j-1]+dp[i-1][j];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        LeetCode62 leetCode62 = new LeetCode62();
        int result = leetCode62.uniquePaths(19,13);
        System.out.println(result);
    }

}
