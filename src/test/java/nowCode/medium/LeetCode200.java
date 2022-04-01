package nowCode.medium;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1：
//
//
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
//
//
// 示例 2：
//
//
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] 的值为 '0' 或 '1'
public class LeetCode200 {

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int res = 0;
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<columns;j++){
                if(grid[i][j]=='1'){
                    res+=1;
                    dfs(i,j,grid,rows,columns);
                }
            }
        }
        return res;
    }

    private void dfs(int i,int j,char[][] grid,int rows,int columns){
        if((i<0 || i>=rows) || (j<0 || j>=columns) || grid[i][j]!='1'){
            return;
        }
        grid[i][j]='2';
        dfs(i+1,j,grid,rows,columns);
        dfs(i-1,j,grid,rows,columns);
        dfs(i,j+1,grid,rows,columns);
        dfs(i,j-1,grid,rows,columns);
    }
}
