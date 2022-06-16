package leetCode.medium;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 *
 * 提示：
 *
 *     0 <= s.length <= 5 * 104
 *     s 由英文字母、数字、符号和空格组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
 */
public class LeetCode3_20220616 {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int i = 0,j = 0,len = s.length();
        int maxLen = 1;
        while(j<len && i<len){
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
                i++;
                maxLen = Math.max(maxLen,i-j);
            }else{
                set.remove(s.charAt(j));
                j++;
            }
        }
        return maxLen;
    }

    @Test
    public void testLengthOfLongestSubstring(){
        String str = "bbbbb";
        int i = lengthOfLongestSubstring(str);
        System.out.println(i);
    }
}
