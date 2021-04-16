package leetCode.simple;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
//
// 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
//
// 回文串不一定是字典当中的单词。
//
//
//
// 示例1：
//
// 输入："tactcoa"
//输出：true（排列有"tacocat"、"atcocta"，等等）
public class LeetCode0104 {

    public boolean canPermutePalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int count = map.getOrDefault(s.charAt(i), 0);
            count += 1;
            map.put(s.charAt(i), count);
        }
        int octNumber = 0;
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            if (value % 2 == 0) {
                continue;
            }
            octNumber += 1;
            if (octNumber > 1) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCanPermutePalindrome() {
        String str = "ab//a";
        boolean result = canPermutePalindrome(str);
        System.out.println("result=" + result);
    }
}
