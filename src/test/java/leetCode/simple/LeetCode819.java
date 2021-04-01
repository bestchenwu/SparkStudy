package leetCode.simple;

import org.junit.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
//
// 题目保证至少有一个词不在禁用列表中，而且答案唯一。
//
// 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
//
//
//
// 示例：
//
// 输入:
//paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
//banned = ["hit"]
//输出: "ball"
//解释:
//"hit" 出现了3次，但它是一个禁用的单词。
//"ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
//注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
//"hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
public class LeetCode819 {

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        for (String str : banned) {
            bannedSet.add(str);
        }
        boolean lastIsNotWord = true;
        Map<String, Integer> map = new HashMap<String, Integer>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paragraph.length(); i++) {
            char item = paragraph.charAt(i);
            if (checkCharacter(item)) {
                sb.append(item);
            } else {
                if (lastIsNotWord && sb.length() > 0) {
                    String word = sb.toString().toLowerCase();
                    if (!bannedSet.contains(word)) {
                        Integer count = map.getOrDefault(word, 0);
                        count += 1;
                        map.put(word, count);
                    }
                    sb = new StringBuilder();
                }
                lastIsNotWord = true;
            }
        }
        if(sb.length()>0){
            String word = sb.toString().toLowerCase();
            if (!bannedSet.contains(word)) {
                Integer count = map.getOrDefault(word, 0);
                count += 1;
                map.put(word, count);
            }
        }
        String result = "";
        int lastCount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > lastCount) {
                result = entry.getKey();
                lastCount = entry.getValue();
            }
        }
        return result;
    }

    private boolean checkCharacter(char item) {
        return (item >= 'a' && item <= 'z') || (item >= 'A' && item <= 'Z');
    }

    @Test
    public void testMostCommonWord(){
        String str = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = new String[]{"hit"};
//        String str = "Bob";
//        String[] banned = new String[]{};
        String result = mostCommonWord(str,banned);
        System.out.println("most common word:"+result);
    }
}
