package nowCode.simple;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *  1 2 3 4
 *  5 6 7 8
 *  9 10 11 12
 *
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * @author chenwu on 2023.5.21
 */
public class NowCode29 {

    public int[] spiralOrder(int[][] matrix) {
       int rowLength = matrix.length;
       if(rowLength<=0){
           return new int[]{};
       }
       int columnLength = matrix[0].length;
        int[] res = new int[rowLength*columnLength];
        int index = 0;
        int left = 0,right = columnLength-1,top = 0,bottom = rowLength-1;
        while(left<=right&&top<=bottom){
            for(int column = left;column<=right;column++){
                res[index++]=matrix[top][column];
            }
            for(int row = top+1;row<=bottom;row++){
                res[index++] = matrix[row][right];
            }
            if(right>left&&bottom>top){
                for(int colum = right-1;colum>left;colum--){
                    res[index++] = matrix[bottom][colum];
                }
                for(int row = bottom;row>top;row--){
                    res[index++]=matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        NowCode29 nowCode29 = new NowCode29();
        int[] res = nowCode29.spiralOrder(matrix);
        System.out.println("res="+ Arrays.toString(res));
    }
}
