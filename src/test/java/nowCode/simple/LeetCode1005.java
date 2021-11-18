package nowCode.simple;

import org.junit.Test;

//稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
//
// 示例1:
//
//  输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""],
// s = "ta"
// 输出：-1
// 说明: 不存在返回-1。
//
//
// 示例2:
//
//  输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""],
//s = "ball"
// 输出：4
public class LeetCode1005 {

    private int helpFindString(String[] words, String s, int start, int end) {
        while (start <= end) {
            int middle = (start + end) / 2;
            if (words[middle].equals("")) {
                int left = helpFindString(words, s, start, middle - 1);
                if (left != -1) {
                    return left;
                }
                int right = helpFindString(words, s, middle + 1, end);
                return right != -1 ? right : -1;
            }
            if (words[middle].equals(s)) {
                return middle;
            }
            if (words[middle].compareTo(s) < 0) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }

    public int findString(String[] words, String s) {
        int index = helpFindString(words, s, 0, words.length - 1);
        return index;
    }

    @Test
    public void testFindString() {
        String[] words = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String s = "dad";
        int index = findString(words, s);
        System.out.println("index=" + index);
    }
}
