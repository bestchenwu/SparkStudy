package leetCode.simple;

import org.junit.Test;

//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
//
//
// 示例 1：
//
//
//输入：strs = ["flower","flow","flight"]
//输出："fl"
//
//
// 示例 2：
//
//
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 200
// 0 <= strs[i].length <= 200
// strs[i] 仅由小写英文字母组成
public class LeetCode14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int index = 0;
        char item;
        boolean flag = true;
        while (index < strs[0].length()) {
            item = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() < index + 1) {
                    flag = false;
                    break;
                }
                if (strs[i].charAt(index) != item) {
                    flag = false;
                    break;
                }
            }
            if(!flag){
                break;
            }
            index += 1;
        }
        if (index == 0) {
            return "";
        } else {
            return strs[0].substring(0, index);
        }

    }

    @Test
    public void testLongestCommonPrefix() {
        //String[] strs = new String[]{"flower", "flow", "flight"};
        String[] strs = new String[]{"dog","racecar","car"};
        String res = longestCommonPrefix(strs);
        System.out.println("res=" + res);
    }
}
