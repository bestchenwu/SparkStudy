package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/spiral-matrix/
 * <p>
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class LeetCode54 {

    private void output(int[][] matrix,boolean[][] visited, int row, int column, int rowUpdate, int columnUpdate, int index, int maxRows,int maxColumns,List<Integer> result) {
        if(index<maxRows*maxColumns){
            result.add(matrix[row][column]);
            visited[row][column]=true;
            if(rowUpdate!=0){
                //说明是按行加
                int rowTemp = row+rowUpdate;
                if(rowTemp==maxRows || rowTemp == -1 || visited[rowTemp][column]==true){
                    //说明达到了数组的行拐角
                    //尝试性的先给column+1
                    int columnTemp = column+1;
                    if(columnTemp<maxColumns && visited[row][columnTemp]==false){
                        columnUpdate = 1;
                    }else{
                        columnUpdate = -1;
                    }
                    rowUpdate = 0;
                }
            }else{
                //说明是按列累加
                int columnTemp = column+columnUpdate;
                if(columnTemp == maxColumns || columnTemp == -1 || visited[row][columnTemp]==true){
                    //说明达到了数组的列拐角
                    //尝试性的先给row+1
                    int rowTemp = row+1;
                    if(rowTemp<maxRows && visited[rowTemp][column]==false){
                        rowUpdate = 1;
                    }else{
                        rowUpdate = -1;
                    }
                    columnUpdate = 0;
                }
            }
            row = row+rowUpdate;
            column = column+columnUpdate;
            index+=1;
            output(matrix,visited,row,column,rowUpdate,columnUpdate,index,maxRows,maxColumns,result);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length==0){
            return new ArrayList<>();
        }
        int maxRows = matrix.length;
        int maxColumns = matrix[0].length;
        boolean[][] visited = new boolean[maxRows][maxColumns];
        List<Integer> result = new ArrayList<>();
        output(matrix,visited,0,0,0,1,0,maxRows,maxColumns,result);
        return result;
    }

    public static void main(String[] args) {
        LeetCode54 leetCode54 = new LeetCode54();
        //int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = new int[][]{{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        List<Integer> integerList = leetCode54.spiralOrder(matrix);
        System.out.println(integerList);
    }
}
