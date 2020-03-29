package leetCode.medium;

/**
 * 240. 搜索二维矩阵 II
 *
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 */
public class LeetCode240 {

    /**
     * 假设从当前位置判断有没有target存在
     *
     * @param matrix
     * @param startRow
     * @param startColumn
     * @param maxRow
     * @param maxColumn
     * @param target
     * @return
     */
    //todo:超出时间限制
    private boolean  searchMatrix0(int[][] matrix,int startRow,int startColumn,int maxRow,int maxColumn,int target){
        if(startRow>=maxRow || startColumn>=maxColumn || matrix[startRow][startColumn]>target){
            return false;
        }
        if(matrix[startRow][startColumn]==target){
            return true;
        }
        //判断matrix[startRow+1][startColumn]和matrix[startRow][startColumn+1]哪个更接近target
        boolean findResult = searchMatrix0(matrix, startRow + 1, startColumn, maxRow, maxColumn, target);
        if(findResult){
            return findResult;
        }else{
            findResult = searchMatrix0(matrix, startRow, startColumn+1, maxRow, maxColumn, target);
        }
        return findResult;
    }


    public boolean searchMatrix0(int[][] matrix, int target) {
        if(matrix==null){
            return false;
        }
        int maxRow = matrix.length-1;
        if(maxRow==0){
            return false;
        }
        int maxColumn = matrix[0].length-1;
        boolean result = searchMatrix0(matrix, 0, 0, maxRow, maxColumn, target);
        return result;
    }

    /**
     * 假设我们开始将指针指向矩阵的左下角，那么当前元素比target大的时候,row-1,比target小的时候column+1,如此反复直到指针失效。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null){
            return false;
        }
        int maxRow = matrix.length-1;
        if(maxRow==0){
            return false;
        }
        int maxColumn = matrix[0].length-1;
        //初始化左下角的指针
        int startRow = maxRow;
        int startColumn = 0;
        while(startRow>=0&&startColumn<=maxColumn){
            if(matrix[startRow][startColumn]>target){
                startRow--;
            }else if(matrix[startRow][startColumn]<target){
                startColumn++;
            }else{
                return true;
            }
        }
       return false;
    }

    public static void main(String[] args) {
        LeetCode240 leetCode240 = new LeetCode240();
        int[][] matrix = new int[][]{
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        //int target = 5;
        //int target = 11;
        //int target = 21;
        //int target = 20;
        //int target = 30;
        //int target = 35;
        //int target = 27;
        //int target = 19;
        int target = 3;
        boolean result = leetCode240.searchMatrix(matrix, target);
        System.out.println("result:"+result);
    }
}
