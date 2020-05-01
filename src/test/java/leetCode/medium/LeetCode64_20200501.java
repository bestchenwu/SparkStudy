package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class LeetCode64_20200501 {

    public int calculate(int[][] grid,int i,int j,int m,int n){
        if(i>=m-1 && j >= n-1){
            return grid[m-1][n-1];
        }else if(i>=m-1){
            return grid[i][j]+calculate(grid,i,j+1,m,n);
        }else if(j >= n-1){
            return grid[i][j]+calculate(grid,i+1,j,m,n);
        }else {
            return grid[i][j]+Math.min(calculate(grid,i+1,j,m,n),calculate(grid,i,j+1,m,n));
        }
    }

    /**
     * todo:超出时间限制
     *
     * @param grid
     * @return
     */
    public int minPathSum0(int[][] grid) {
        int m = grid.length;
        if(m==0){
            return 0;
        }
        int n = grid[0].length;
        int minPath = calculate(grid,0,0,m,n);
        return minPath;
    }

    public int minPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = m-1;i>=0;i--){
            for(int j = n-1;j>=0;j--){
                if(i==m-1 && j==n-1){
                    dp[i][j] = grid[m-1][n-1];
                }else if(i==m-1 && j+1<=n-1){
                    dp[i][j] = grid[i][j]+dp[i][j+1];
                }else if(i+1<=m-1&&j==n-1){
                    dp[i][j] = grid[i][j]+dp[i+1][j];
                }else{
                    dp[i][j] = grid[i][j]+Math.min(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        LeetCode64_20200501 leetCode64_20200501 = new LeetCode64_20200501();
        int[][] grid1 = new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        int min = leetCode64_20200501.minPathSum(grid1);
        System.out.println("min="+min);
    }
}
