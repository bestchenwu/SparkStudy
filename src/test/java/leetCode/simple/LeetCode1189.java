package leetCode.simple;

//给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
//
// 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
//
//
//
// 示例 1：
//
//
//
// 输入：text = "nlaebolko"
//输出：1
//
//
// 示例 2：
//
//
//
// 输入：text = "loonbalxballpoon"
//输出：2
//
//
// 示例 3：
//
// 输入：text = "leetcode"
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= text.length <= 10^4
// text 全部由小写英文字母组成

import org.junit.Test;

import java.util.Map;
import java.util.HashMap;

public class LeetCode1189 {

    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> characterMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char item = text.charAt(i);
            if (item == 'b' || item == 'a' || item == 'l' || item == 'o' || item == 'n') {
                Integer characterCount = characterMap.getOrDefault(item, 0);
                characterCount += 1;
                characterMap.put(item, characterCount);
            }
        }
        int minBanCount = Integer.MAX_VALUE;
        int minLOCount = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : characterMap.entrySet()) {
            if (entry.getKey() == 'l' || entry.getKey() == 'o') {
                minLOCount = Math.min(minLOCount, entry.getValue());
            } else {
                minBanCount = Math.min(minBanCount, entry.getValue());
            }
        }
        return (minBanCount == Integer.MAX_VALUE || minLOCount == Integer.MAX_VALUE) ? 0 : Math.min(minBanCount,
                minLOCount / 2);
    }

    @Test
    public void testMaxNumberOfBalloons() {
        String str = "lloo";
        int result = maxNumberOfBalloons(str);
        System.out.println("result=" + result);
    }
}
