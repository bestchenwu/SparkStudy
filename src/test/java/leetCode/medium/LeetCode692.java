package leetCode.medium;
import java.util.*;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 示例 1：
 *
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 *  
 *
 * 示例 2：
 *
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 *  
 *
 * 注意：
 *
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 *  
 *
 * 扩展练习：
 *
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 *
 */
public class LeetCode692 {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> wordCountMap = new HashMap<>();
        for(String word:words){
            wordCountMap.put(word,wordCountMap.getOrDefault(word,0)+1);
        }
        PriorityQueue<String> priorityQueue = new PriorityQueue<>((s1,s2)->{
            Integer s1Count = wordCountMap.get(s1);
            Integer s2Count = wordCountMap.get(s2);
            if(s1Count!=s2Count){
                return s1Count.compareTo(s2Count);
            }else{
                return s2.compareTo(s1);
            }
        });
        for(String word:wordCountMap.keySet()){
            priorityQueue.add(word);
            if(priorityQueue.size()>k){
                priorityQueue.poll();
            }
        }
        List<String> resultList = new ArrayList<>();
        while(!priorityQueue.isEmpty()){
            resultList.add(priorityQueue.poll());
        }
        Collections.reverse(resultList);
        return resultList;
    }

    public static void main(String[] args) {
        LeetCode692 leetCode692 = new LeetCode692();
//        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
//        int k = 2;
        String[] words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;
        List<String> list = leetCode692.topKFrequent(words, k);
        System.out.println(list);
    }
}
