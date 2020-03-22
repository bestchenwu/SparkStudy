package leetCode.medium;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/word-search/
 * <p>
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 *
 * [
 *      C A A
 *      A A A
 *      B C D
 * ]
 *
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * <p>
 * board 和 word 中只包含大写和小写英文字母
 */
public class LeetCode79 {

    private void initIsVisited(boolean[][] isVisted) {
        int maxRow = isVisted.length;
        int maxColumn = isVisted[0].length;
        for(int i = 0;i<maxRow;i++){
            for(int j = 0;j<maxColumn;j++){
                isVisted[i][j] = false;
            }
        }
    }

    private boolean helpExist(char[][] board, String word, AtomicInteger wordIndex, int row, int column, int maxRow, int maxColumn, int count, boolean[][] isVisted) {
        if (row < 0 || row >= maxRow) {
            return false;
        }
        if (column < 0 || column >= maxColumn) {
            return false;
        }
        if (isVisted[row][column] == true) {
            return false;
        }
        if (board[row][column] == word.charAt(wordIndex.get())) {
            count += 1;
            if (count == word.length()) {
                return true;
            } else {
                wordIndex.addAndGet(1);
                isVisted[row][column] = true;
            }
            boolean up = helpExist(board, word, wordIndex, row - 1, column, maxRow, maxColumn, count, isVisted);
            if(up){
                return true;
            }
            boolean down = helpExist(board, word, wordIndex, row + 1, column, maxRow, maxColumn, count, isVisted);
            if(down){
                return true;
            }
            boolean left = helpExist(board, word, wordIndex, row, column - 1, maxRow, maxColumn, count, isVisted);
            if(left){
                return true;
            }
            boolean right = helpExist(board, word, wordIndex, row, column + 1, maxRow, maxColumn, count, isVisted);
            if(right){
                return true;
            }
            wordIndex.decrementAndGet();
            isVisted[row][column] = false;
        }
        return false;
    }

    /**
     * 循环board 从任意一组坐标(i,j)出发,往四个方向寻找,如果发现了word的对应顺序的字符，则将count+=1
     * 如果count==word  退出
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int maxRow = board.length;
        int maxColumn = board[0].length;
        boolean[][] isVisted = new boolean[maxRow][maxColumn];
        initIsVisited(isVisted);
        AtomicInteger wordIndex = new AtomicInteger(0);
        int row = 0, column = 0;
        boolean result = false;
        while (row < maxRow && column < maxColumn) {
            result = helpExist(board, word, wordIndex, row, column, maxRow, maxColumn, 0, isVisted);
            if (result) {
                break;
            } else {
                //向右移动一步
                if (column == maxColumn - 1) {
                    //到达右边界,向下移动一行
                    row += 1;
                    column = 0;
                } else {
                    column += 1;
                }
                //证明以row column为坐标的探索失败
                //initIsVisited(isVisted);
                //wordIndex.set(0);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode79 leetCode79 = new LeetCode79();
//        char[][] board = new char[][]{
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//        char[][] board = new char[][]{
//                {'C', 'A', 'A'},
//                {'A', 'A', 'A'},
//                {'B', 'C', 'D'}
//
//        };

        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}

        };
        String word = "ABCESEEEFS";
        //String word = "AAB";
        //String word = "ABCCED";
        //String word = "SEE";
        //String word = "ABCB";
        boolean exist = leetCode79.exist(board, word);
        System.out.println("exist=" + exist);
    }
}
