package leetCode.medium;

import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * <p>
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * @author chenwu on 2020.3.1
 */
public class LeetCode64 {


    /**
     * 首先分析共有多少个路径
     * 对于[
     *       [1,2,3]
     *       [4,5,6]
     *        [7,8,9]
     *    ]
     * 共有12369,12569,12589,14789,14569,14589 6种
     *
     * 对于[
     *    [1,2,3]
     *    [4,5,6]
     *    ]
     * 共有1236,1256,1456
     *
     * 获取基于坐标(x,y)->(m,n)的所有路径列表
     *
     * @param grid       表示当前数组
     * @param x          表示当前纵轴坐标
     * @param y          y表示横轴坐标
     * @param m          数组的横轴长度
     * @param n          数组的纵轴长度
     * @param resultList 中间临时的路径列表
     * @return  List<List<Integer>> 最终的路径列表
     * @author chenwu on 2020.3.1
     */
    public List<List<Integer>> buildList(int[][] grid, int x, int y, int m, int n, List<List<Integer>> resultList) {
        if (x >= n - 1 && y >= m - 1) {
            return resultList;
        }
        int currentElement = grid[x][y];
        //说明x到达了纵轴的最下端,只能往右边移动
        if (x == n - 1 && y < m - 1) {
            while (y <= m - 1) {
                currentElement = grid[x][y];
                for (List<Integer> list : resultList) {
                    list.add(currentElement);
                }
                y += 1;
            }
            return resultList;
        }
        //说明y到达了纵轴的最右端,只能向下移动
        if (y == m - 1 && x <= n - 1) {
            while (x <= n - 1) {
                currentElement = grid[x][y];
                for (List<Integer> list : resultList) {
                    list.add(currentElement);
                }
                x += 1;
            }
            return resultList;
        }
        for (List<Integer> list : resultList) {
            list.add(currentElement);
        }
        //向右移动的结果列表
        List<List<Integer>> moveRightList = buildList(grid, x + 1, y, m, n, resultList);
        List<List<Integer>> moveDownList = buildList(grid, x, y + 1, m, n, resultList);
        List<List<Integer>> finalList = new ArrayList<>();
        finalList.addAll(moveDownList);
        finalList.addAll(moveRightList);
        return finalList;
    }

    /**
     * 回溯算法
     *
     * @param grid
     * @param x 纵轴坐标
     * @param y 横轴坐标
     * @param m 横轴长度
     * @param n 纵轴长度
     * @param minSum
     */
    public void buildList1(int[][] grid, int x, int y, int m, int n, Stack<Integer> path , AtomicInteger minSum) {
        if(x==n-1 || y==m-1){
            //记住当前path的深度
            int depth = path.size();
            if(x==n-1){
                //表示到达了最下端
                for(int j=y;j<=m-1;j++){
                    path.add(grid[x][j]);
                }
            }else{
                //表示到达了最右端
                for(int i=x;i<=n-1;i++){
                    path.add(grid[i][y]);
                }
            }
            //表示到达了终点
            List<Integer> newPath = new ArrayList<>();
            newPath.addAll(path);
            int sum = newPath.stream().reduce(0,(a,b)->(a+b)).intValue();
            minSum.set(Math.min(sum,minSum.get()));
            while(path.size()>depth){
                path.pop();
            }
            return;
        }
        int currentElement = grid[x][y];
        path.add(currentElement);
        //尝试向下边移动
        buildList1(grid,x+1,y,m,n,path,minSum);
        //尝试向右边移动
        buildList1(grid,x,y+1,m,n,path,minSum);
        path.pop();
    }

    /**
     *
     *
     * @param x 纵轴坐标
     * @param y 横轴坐标
     * @param m 横轴长度
     * @param n 纵轴长度
     * @return int
     */
    public int minPathSumHelp(int[][] grid,int x,int y,int m,int n){
        if(x==n||y==m){
            return Integer.MAX_VALUE;
        }
        if(x==n-1 && y==m-1){
            return grid[x][y];
        }
        return grid[x][y]+Math.min(minPathSumHelp(grid,x+1,y,m,n),minPathSumHelp(grid,x,y+1,m,n));
    }

        public int minPathSum1(int[][] grid) {
            int m = grid[0].length;
            int n = grid.length;
            return minPathSumHelp(grid,0,0,m,n);
        }

    public int minPathSum(int[][] grid) {
        int m = grid[0].length;
        int n = grid.length;
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        AtomicInteger minSum = new AtomicInteger(Integer.MAX_VALUE);
        buildList1(grid,0,0,m,n,path,minSum);

        return minSum.get();
    }

    public int minPathSum2(int[][] grid) {
        //我们建立一个和原数组相同大小的dp数组
        //dp[][] dp[i][j]表示i,j坐标到右下角的最短距离
        //那么dp[i][j] = grid[i][j]+Math.min(dp[i][j+1],dp[i+1][j])
        //这样我们从右下角元素开始遍历
        //最后返回dp[0][0]即表示左上角到右下角的最短距离
        int m = grid[0].length;//m表示横轴长度
        int n = grid.length;//n表示纵轴长度
        int[][] dp = new int[n][m];
        for(int i=n-1;i>=0;i--){
            int j = m-1;
            while(j>=0){
                int minDp;
                if(i+1<=n-1&&j+1<=m-1){
                    minDp = Math.min(dp[i][j+1],dp[i+1][j]);
                }else if(i+1<=n-1){
                    minDp = dp[i+1][j];
                }else if(j+1<=m-1){
                    minDp = dp[i][j+1];
                }else{
                    minDp = 0;
                }

                dp[i][j] = grid[i][j]+minDp;
                j--;
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        LeetCode64 leetCode64 = new LeetCode64();
        int[][] grid1 = new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        //todo:buildList1面对这样的数组直接超时了
        int[][] grid2 = new int[][]{{7,1,3,5,8,9,9,2,1,9,0,8,3,1,6,6,9,5},{9,5,9,4,0,4,8,8,9,5,7,3,6,6,6,9,1,6},{8,2,9,1,3,1,9,7,2,5,3,1,2,4,8,2,8,8},{6,7,9,8,4,8,3,0,4,0,9,6,6,0,0,5,1,4},{7,1,3,1,8,8,3,1,2,1,5,0,2,1,9,1,1,4},{9,5,4,3,5,6,1,3,6,4,9,7,0,8,0,3,9,9},{1,4,2,5,8,7,7,0,0,7,1,2,1,2,7,7,7,4},{3,9,7,9,5,8,9,5,6,9,8,8,0,1,4,2,8,2},{1,5,2,2,2,5,6,3,9,3,1,7,9,6,8,6,8,3},{5,7,8,3,8,8,3,9,9,8,1,9,2,5,4,7,7,7},{2,3,2,4,8,5,1,7,2,9,5,2,4,2,9,2,8,7},{0,1,6,1,1,0,0,6,5,4,3,4,3,7,9,6,1,9}};
//        int minPathSum = leetCode64.minPathSum(grid2);
        //int minPathSum = leetCode64.minPathSum1(grid2);
        int minPathSum2 = leetCode64.minPathSum2(grid2);
        System.out.println(minPathSum2);
    }
}

