package leetCode.simple;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 *
 * @author  chenwu on 2019.9.25
 */
public class LeetCode483 {

    public boolean compareString(String str1,String str2){
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        boolean result = true;
        if(charArray1.length!=charArray2.length){
            return false;
        }
        for(int index = 0;index<charArray1.length;index++){
            if(charArray1[index]!=charArray2[index]){
                return false;
            }
        }
        return true;
    }

    public List<Integer> findAnagrams1(String s, String p) {
        char[] s_arr = s.toCharArray();
        char[] p_arr = p.toCharArray();
        List<Integer> list = new ArrayList<>();

        char[] p_letter = new char[26];
        for(int i = 0; i < p_arr.length;i++){
            p_letter[p_arr[i] - 'a']++;
        }


        for(int i = 0;i < s_arr.length - p_arr.length + 1;i++){
            char[] temp = new char[26];
            //放入hash
            if(p_letter[s_arr[i] - 'a'] == 0) continue;
            for(int j = i; j < i + p_arr.length;j++){
                temp[s_arr[j] - 'a']++;
            }

            //匹配hash
            boolean ok = true;
            for(int j = 0;j < p_arr.length;j++){
                if(temp[p_arr[j] - 'a'] == 0){
                    ok = false;
                    break;
                }else{
                    temp[p_arr[j] - 'a']--;
                }
            }

            //ok 加入list
            if(ok){
                list.add(i);
            }

        }

        return list;
    }

    public List<Integer> findAnagramsByHash(String s,String p){
        List<Integer> list = new ArrayList<Integer>();
        if(s==null||s.length()==0){
            return list;
        }
        if(p==null||p.length()==0){
            return list;
        }
        if(s.length()<p.length()){
            return list;
        }
        char[] sCharArray = s.toCharArray();
        char[] pCharArray = p.toCharArray();
        int sLength = sCharArray.length;
        int pLength = pCharArray.length;
        //初始化哈希表
        char[] pHashArray = new char[26];
        for(int i=0;i<pCharArray.length;i++){
            pHashArray[pCharArray[i]-'a']++;
        }
        for(int i=0;i<=sLength-pLength;i++){
            if(pHashArray[sCharArray[i]-'a']==0){
                continue;
            }
            char[] tempHashArray = new char[26];
            for(int j=i;j<i+pLength;j++){
                tempHashArray[sCharArray[j]-'a']++;
            }
            //比较两个hash array是否相同
            boolean ok = true;
            for(int index = 0;index<pLength;index++){
                if(tempHashArray[pCharArray[index]-'a']==0){
                    ok = false;
                    break;
                }else {
                    tempHashArray[pCharArray[index]-'a']--;
                }
            }
            if(ok){
                list.add(i);
            }
        }
        return list;
    }

    public int[] twoSum(int[] nums, int target) {
        if(nums==null||nums.length==0){
            return null;
        }
        //第一个key存放值,value存放数组下标
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int complement = target-nums[i];
            if(map.containsKey(complement)){
                return new int[]{i,map.get(complement)};
            }else{
                map.put(nums[i],i);
            }
        }
        return null;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if(s==null||s.length()==0){
            return list;
        }
        if(p==null||p.length()==0){
            return list;
        }
        if(s.length()<p.length()){
            return list;
        }
        int sLength = s.length();
        int pLength = p.length();
        for(int i=0;i<=sLength-pLength;i++){
            String subString = s.substring(i,i+pLength);
            if(compareString(subString,p)){
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args){
        String s = "cbaebabacd";
        String p = "abc";
        LeetCode483 leetCode483 = new LeetCode483();
        List<Integer> list = leetCode483.findAnagramsByHash(s,p);
        System.out.println(list);
    }
}
