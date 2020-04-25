package leetCode.medium;
import java.util.*;
/**
 * 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 */
public class LeetCode49 {

    private String getHashCode(String str){
        int length = str.length();
       char[] hashCodeArray = new char[length];
        int j = 0;
        for(int i=0;i<length;i++){
            hashCodeArray[j++]=(char)str.charAt(i);
        }
        Arrays.sort(hashCodeArray);
        String hashCodeStr = String.valueOf(hashCodeArray);

        return hashCodeStr;
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String,List<String>> hashMap = new HashMap<>();
        for(String item:strs){
            String hashCode = getHashCode(item);
            List<String> list = hashMap.getOrDefault(hashCode,new ArrayList<String>());
            list.add(item);
            hashMap.put(hashCode,list);
        }
        List<List<String>> result = new ArrayList<>();
        for(List<String> list:hashMap.values()){
            result.add(list);
        }
        return result;
    }

    public List<List<String>> groupAnagrams0(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String item:strs){
            //利用字符串在[a,b,c,d...,z]上的映射 例如abbc 映射为[1 2 1]
            //那么对应的hashCode为#1#2#1#0#0...)
            //如果是bbcd  那么对应的hashCode为#0#2#1#1...
            int[] hashCodeArray = new int[26];
            for(char itemChar:item.toCharArray()){
                hashCodeArray[itemChar-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<26;i++){
                sb.append("#");
                sb.append(hashCodeArray[i]);
            }
            List<String> list = map.getOrDefault(sb.toString(), new ArrayList<>());
            list.add(item);
            map.put(sb.toString(),list);
        }
        return new ArrayList<>(map.values());
    }


    private String generateHashCode(String str){
        int[] hashCodeArray = new int[26];
        for(int i = 0;i<str.length();i++){
            char currentChar = str.charAt(i);
            hashCodeArray[currentChar-'a']+=1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<hashCodeArray.length;i++){
            if(hashCodeArray[i]>0){
                sb.append(i+1);
                for(int j = 1;j<=hashCodeArray[i];j++){
                    sb.append("#");
                }
            }
        }
        return sb.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String str:strs){
            String hashCode = generateHashCode(str);
            List<String> list = map.getOrDefault(hashCode,new ArrayList<String>());
            list.add(str);
            map.put(hashCode,list);
        }
        List<List<String>> result = new ArrayList<>();
        for(List<String> itemList:map.values()){
            result.add(itemList);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode49 leetCode49 = new LeetCode49();
//        String[] st = new String[]{"fat","asp"};
//        for(String item:st){
//            System.out.println("hashCode:"+leetCode49.getHashCode(item));
//        }
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = leetCode49.groupAnagrams(strs);
        System.out.println(lists);
    }
}
