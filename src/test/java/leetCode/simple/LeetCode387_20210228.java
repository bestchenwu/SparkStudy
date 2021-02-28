package leetCode.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//
//
//
// 示例：
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
//
//
//
//
// 提示：你可以假定该字符串只包含小写字母。
public class LeetCode387_20210228 {

    public int firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), -1);
            } else {
                map.put(s.charAt(i), i);
            }
        }

        int first = n;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int posValue = entry.getValue();
            if (posValue != -1 && posValue < first) {
                first = posValue;
            }
        }
        if (first == n) {
            return -1;
        }
        return first;
    }

    @Test
    public void test(){
        String value = "loveleetcode";
        Assert.assertEquals(2,firstUniqChar(value));
    }
}
