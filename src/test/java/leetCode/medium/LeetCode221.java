package leetCode.medium;

/**
 * 221. 最大正方形
 *
 * https://leetcode-cn.com/problems/maximal-square/
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 */
public class LeetCode221 {

    public int maximalSquare(char[][] matrix) {
        if(matrix==null||matrix.length<=0){
            return 0;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        //这里要把数组的长度+1的原因是要将右下角的元素包进来
        int[][] dp = new int[rows+1][columns+1];
        int maxLen = 0;
        //dp[i][j]表示由matrix[i-1][j-1]参与构成的最大正方形边长
        //那么dp[i][j] = min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1])+1
        for(int i = 1;i<=rows;i++){
            for(int j = 1;j<=columns;j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
                    maxLen = Math.max(dp[i][j],maxLen);
                }
            }
        }
        return maxLen*maxLen;
    }

    public static void main(String[] args) {

    }
}
