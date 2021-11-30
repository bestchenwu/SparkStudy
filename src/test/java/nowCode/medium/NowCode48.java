package nowCode.medium;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
//
//
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
//
//
// 提示：
//
//
// s.length <= 40000
public class NowCode48 {

    public int lengthOfLongestSubstring(String s) {
        int i = 0,j = 0,len = s.length();
        int maxLength = 0;
        Set<Character> set = new HashSet<>();
        while(j<len){
            char item = s.charAt(j);
            if(set.contains(item)){
                set.remove(s.charAt(i));
                i+=1;
            }else{
                set.add(item);
                j+=1;
                maxLength = Math.max(maxLength,j-i);
            }
        }
        return maxLength;
    }

    @Test
    public void testLengthOfLongestSubstring(){
        String s = "pwwkew";
        int res = lengthOfLongestSubstring(s);
        System.out.println("res="+res);
    }
}
