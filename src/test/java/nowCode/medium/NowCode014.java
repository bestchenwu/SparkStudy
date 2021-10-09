package nowCode.medium;

import org.junit.Test;

import java.util.Arrays;

//给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
//
// 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
//
//
//
// 示例 1：
//
//
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
//
//
// 示例 2：
//
//
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
//
//
//
//
// 提示：
//
//
// 1 <= s1.length, s2.length <= 10⁴
// s1 和 s2 仅包含小写字母
public class NowCode014 {

    private int[] getHash(String s) {
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] += 1;
        }
        return hash;
    }

    private boolean checkHashIsEquals(int[] hash, String str, int start, int end) {
        for (int j = start; j <= end; j++) {
            int index = str.charAt(j) - 'a';
            hash[index] -= 1;
            if (hash[index] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        //先构建s1的哈希表
        int[] s1Hash = getHash(s1);
        int len1 = s1.length(), len2 = s2.length();
        for (int i = 0; i < len2; i++) {
            if (len2 - i < len1) {
                break;
            }
            int[] copyHash = Arrays.copyOf(s1Hash, s1Hash.length);
            boolean flag = checkHashIsEquals(copyHash, s2, i, i + len1 - 1);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 滑动窗口
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion1(String s1, String s2) {
        if(s2.length()<s1.length()){
            return false;
        }
        int[] count = new int[26];
        int left = 0,right = 0;
        int len1 = s1.length(),len2 = s2.length();
        while(right<len1){
            count[s1.charAt(right)-'a']++;
            count[s2.charAt(right)-'a']--;
            right++;
        }
        if(isAllZero(count)){
            return true;
        }
        //此时right = len1,left = 0
        while(right<len2){
            //始终保持right-left = len1,所以是[left,right)
            count[s2.charAt(left++)-'a']++;
            count[s2.charAt(right++)-'a']--;
            if(isAllZero(count)){
                return true;
            }
        }
        return false;
    }

    private boolean isAllZero(int[] count){
        for(int i = 0;i<count.length;i++){
            if(count[i]!=0){
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCheckInclusion() {
        String str1 = "ab";
        //String str2 = "eidbaooo";
        String str2 = "eidboaoo";
        boolean flag = checkInclusion(str1, str2);
        System.out.println("flag=" + flag);
    }
}
