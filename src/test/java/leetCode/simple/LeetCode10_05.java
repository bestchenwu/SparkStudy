package leetCode.simple;

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
//
//
public class LeetCode10_05 {

    private int helpFindString(String[] words, String s, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = 0;
        while (left <= right) {
            if (words[left] == "") {
                left += 1;
                continue;
            }
            if (words[right] == "") {
                right -= 1;
                continue;
            }
            middle = left + (right - left) / 2;
            if (words[middle].equals(s)) {
                return middle;
            } else if (words[middle].length()==0) {
                int leftFind = helpFindString(words, s, left, middle - 1);
                int rightFind = helpFindString(words, s, middle + 1, right);
                return leftFind != -1 ? leftFind : (rightFind != -1 ? rightFind : -1);
            } else if (words[middle].charAt(0) > s.charAt(0)) {
                right = middle-1;
            } else {
                left = middle+1;
            }
        }
        return -1;
    }

    public int findString(String[] words, String s) {
        int left = 0, right = words.length - 1;
        int result = helpFindString(words, s, left, right);
        return result;
    }

    @Test
    public void testFindString() {
        //String[] words = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        //String s = "ball";
        String[] words = new String[]{"DirNnILhARNS hOYIFB", "SM ", "YSPBaovrZBS", "evMMBOf", "mCrS", "oRJfjw gwuo",
                "xOpSEXvfI"};
        String s = "mCrS";
        int result = findString(words, s);
        System.out.println("result=" + result);
    }
}
