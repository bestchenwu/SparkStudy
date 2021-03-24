package leetCode.simple;

//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
//
// 示例 1:
//
//
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
//
//
// 示例 2:
//
//
//输入: "aba"
//
//输出: False
//
//
// 示例 3:
//
//
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)

import org.junit.Assert;
import org.junit.Test;

/**
 * @author chenwu on 2021.3.19
 */
public class LeetCode459 {

    private boolean canRepeatedString(int k, String s) {
        int length = s.length();
        int i = k;
        for (; i < length; i += 1) {
            if (s.charAt(i) != s.charAt(i - k)) {
                return false;
            }
        }
        return i >= length;
    }

    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int sLength = s.length();
        for (int i = 1; i < s.length(); i++) {
            if (sLength % i != 0) {
                continue;
            }
            if (canRepeatedString(i, s)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testRepeat() {
        String str = "aba";
        boolean result = repeatedSubstringPattern(str);
        Assert.assertFalse(result);
        str = "abcabcabcabc";
        result = repeatedSubstringPattern(str);
        Assert.assertTrue(result);
        str = "abab";
        result = repeatedSubstringPattern(str);
        Assert.assertTrue(result);
        str = "ccccc";
        result = repeatedSubstringPattern(str);
        Assert.assertTrue(result);
        str = "abac";
        result = repeatedSubstringPattern(str);
        Assert.assertFalse(result);
    }
}
