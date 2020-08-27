package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/maximal-square/
 * 221. 最大正方形
 * <p>
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 *
 * @author chenwu on 2020.8.27
 */
public class LeetCode221_20200827 {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int maxRowLength = matrix.length;
        int maxColumnLength = matrix[0].length;
        //每个坐标都表示到目前为止，当前节点所拥有的最大正方形边长
        int[][] dp = new int[maxRowLength][maxColumnLength];
        int maxArea = 0;
        for (int i = 0; i < maxRowLength; i++) {
            for (int j = 0; j < maxColumnLength; j++) {
                if(matrix[i][j] == '0'){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = Math.min(i-1>=0&&j-1>=0?dp[i-1][j-1]:0,Math.min(i-1>=0?dp[i-1][j]:0,j-1>=0?dp[i][j-1]:0))+1;
                }
                if(dp[i][j]>0){
                    maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
//        char[][] matrix = new char[][]{
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '0', '1'},
//        };
        char[][] matrix = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '1', '1', '1'}
        };
        LeetCode221_20200827 leetCode221_20200827 = new LeetCode221_20200827();
        int result = leetCode221_20200827.maximalSquare(matrix);
        System.out.println(result);
    }
}
