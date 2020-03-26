package leetCode.medium;

import java.util.*;

/**
 * 3. 无重复字符的最长子串
 * <p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LeetCode3 {

    /**
     * 1、首先我们考虑无重复字符的情况，也就是形如abcbacacb这样的形式
     * 我们建立一个List<Character> list来保存无重复的子串
     * lastIndex = s.length-1;
     * int startIndex = 0;
     * int maxLength = 0;
     * 那么假定我们已经保存了abc这样的列表，当加入了一个新字符包含在这个列表的时候，例如b
     * 我们应该先计算当前无重复列表的长度，与maxLength进行比较。
     * 然后获取原有列表里面从b所在位置的开始的子列表,作为当前无重复的子列表
     * 现在可以把b加入到list中了
     *
     * while(startIndex<=lastIndex){
     * Character currentChar = s.charAt(startIndex);
     * if(list.contains(currentChar)){
     * maxLength = Math.max(list.size(),maxLength);
     * int firstIndex = list.indexOf(currentChar);
     * if(firstIndex=list.size()-1){
     * //最后一个字符跟当前字符相同
     * list.clear();
     * }else{
     * list = list.subList(firstIndex+1,list.size());
     * }
     * }
     * list.add(currentChar);
     * startIndex++;
     * }
     * //最后我们要判断下list是否为空
     * if(!list.isEmpty()){
     * maxLength = Math.max(list.size(),maxLegnth);
     * }
     *
     * 2、接下来我们考虑在有重复字符的情况
     * 以eabcbeea为例
     * 我们在碰到第二个e的时候，此时list = [abcb]
     * 或者以abcdeea为例
     * 我们在碰到第二个e的时候，此时list = []
     * 这时我们知道list与e结合是一个无重复字符列表，并且下一个无重复字符串肯定是[e]
     * 所以我们在list截取之后,做一个下一个字符判断
     * int nextIndex = startIndex+1;
     * if(nextIndex<=lastIndex&&s.chartAt(nextIndex)==currentChar){
     * list.add(currentChar);
     * maxLength = Math.max(list.size(),maxLength);
     * list.clear();
     * //然后提前移动startIndex
     * while(nextIndex<=lastIndex&&s.chartAt(nextIndex)==currentChar){
     * startIndex = nextIndex;
     * nextIndex+=1;
     * }
     * }
     * 把这一段逻辑加到if contains的判断里面
     *
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring0(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        List<Character> list = new ArrayList<>();
        int maxLength = 0;
        int lastIndex = s.length() - 1;
        int startIndex = 0;
        while (startIndex <= lastIndex) {
            char currentChar = s.charAt(startIndex);
            if (list.contains(currentChar)) {
                maxLength = Math.max(maxLength, list.size());
                //找出currentChar在list其中的位置t
                //获得从位置t到list结束的新索引
                int firstIndex = list.indexOf(currentChar);
                if (firstIndex == list.size() - 1) {
                    list.clear();
                } else {
                    list = list.subList(firstIndex + 1, list.size());
                }
            }
            int nextIndex = startIndex + 1;
            if(nextIndex<=lastIndex && s.charAt(nextIndex)==currentChar){
                list.add(currentChar);
                maxLength = Math.max(maxLength, list.size());
                while (nextIndex <= lastIndex && s.charAt(nextIndex) == currentChar) {
                    startIndex = nextIndex;
                    nextIndex += 1;
                }
                list.clear();
            }
            list.add(currentChar);
            startIndex++;
        }
        if (!list.isEmpty()) {
            maxLength = Math.max(maxLength, list.size());
        }
        return maxLength;
    }

    //暴利法
    private boolean isUnique(String s,int i,int j){
        Set<Character> set = new HashSet<>();
        for(int k = i;k<j;k++){
            char currentChar = s.charAt(k);
            if(set.contains(currentChar)){
                return false;
            }
            set.add(currentChar);
        }
        return true;
    }

    //超出时间限制
    public int lengthOfLongestSubstring2(String s) {
        int i = 0,j= 0;
        int maxLength = 0;
        for(i = 0;i<s.length();i++){
            for(j=i+1;j<=s.length();j++){
                if(isUnique(s,i,j)) maxLength = Math.max(maxLength,j-i);
            }
        }
        return maxLength;
    }

    /**
     * 从i = 0 ,j = 0 开始循环
     * 构造一个[i,j]的无重复子串的队列
     * 那么当s.charAt[j+1]包含在[i,j]中的时候，我们不知道是哪一个，但是知道这个元素位于[i,j]之间，所以我们从头开始删除
     *  我们将[i,j]中的元素保存为Set
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int i = 0,j = 0;
        int maxLength = 0;
        Set<Character> set = new HashSet<>();
        int lastIndex = s.length()-1;
        while(i<=lastIndex && j<=lastIndex){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                maxLength = Math.max(maxLength,j-i);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int i = 0,j = 0;
        int lastIndex = s.length()-1;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(;j<=lastIndex;j++){
            char charJ = s.charAt(j);
            if(map.containsKey(charJ)){
                i = Math.max(map.get(charJ),i);
            }
            maxLength = Math.max(maxLength,j-i);
            map.put(charJ,j);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LeetCode3 leetCode3 = new LeetCode3();
        //String str = "abcabcbb";
        //String str = "abcbcacbdb";

        //String str = "abbbbc";
        //String str = "pwwkew";
        //String str = "umvejcuuk";
        String str = "abcbac";
        int maxLength = leetCode3.lengthOfLongestSubstring(str);
        System.out.println(maxLength);
    }
}
