package leetCode.medium;

public class LeetCode221_20200430 {

    public int maximalSquare(char[][] matrix) {
        if(matrix==null){
            return 0;
        }
        int maxRow = matrix.length;
        if(maxRow==0){
            return 0;
        }
        int maxColumn = matrix[0].length;
        if(maxColumn==0){
            return 0;
        }
        //dp表示(i,j)位置处的最大边长
        int[][] dp = new int[maxRow][maxColumn];
        dp[0][0] = (matrix[0][0]=='1'?1:0);
        int maxArea = dp[0][0]*dp[0][0];
        for(int i = 0;i<maxRow;i++){
            for(int j = 0;j<maxColumn;j++){
                if(matrix[i][j]=='0'){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = Math.min((i-1>=0&&j-1>=0?dp[i-1][j-1]:0),Math.min((i-1>=0?dp[i-1][j]:0),(j-1>=0?dp[i][j-1]:0)))+(matrix[i][j] == '1'?1:0);
                }
                if(dp[i][j]>0){
                    maxArea = Math.max(dp[i][j]*dp[i][j],maxArea);
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LeetCode221_20200430 leetCode221_20200430 = new LeetCode221_20200430();
        char[][] matrix = new char[][]{
                {'1','0','1','0'},
                {'1','0','1','1'},
                {'1','0','1','1'},
                {'1','1','1','1'}
        };
        int area = leetCode221_20200430.maximalSquare(matrix);
        System.out.println(area);
    }
}
