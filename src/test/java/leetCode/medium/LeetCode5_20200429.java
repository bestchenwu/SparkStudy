package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LeetCode5_20200429 {

    public String longestPalindrome(String s) {
        //假设i位置处的最长回文子串为s1,以i位置为结尾的最长回文子串为s2,那么在i+1位置处最长的回文子串是
        //s1结束位置为i s.charAt(i+1) == s.charAt(i+1-s1)
        //i+1的字符与s2能否拼成一个更大的子串
        if(s==null || s.length()==0){
            return "";
        }
        if(s.length()==1){
            return s;
        }

        String dp1 = s.substring(0,1);
        String dp2 = dp1;
        int sLength = s.length();
        for(int i = 1;i<sLength;i++){
            int  leftIndex = i-dp2.length()-1;
            if(leftIndex>=0 && s.charAt(i) == s.charAt(leftIndex)){
                dp2=s.charAt(leftIndex)+dp2+s.charAt(i);
            }else if(s.charAt(i)==s.charAt(i-1)){
                dp2 = ""+s.charAt(i-1)+s.charAt(i);
            }else{
                dp2 = String.valueOf(s.charAt(i));
            }
            if(dp1.length()<=dp2.length()){
                dp1 = dp2;
            }
        }
        String result = dp1.length()>dp2.length()?dp1:dp2;
        return result;
    }

    private Map<String,Boolean> memoMap = new HashMap<>();

    private boolean checkStringIsPalindrome(String s){
        if(s.length()==1){
            return true;
        }
        if(memoMap.get(s)!=null){
            return memoMap.get(s);
        }else{
            int i = 0;
            int j = s.length()-1;
            while(j>=i && s.charAt(i) == s.charAt(j)){
                i+=1;
                j-=1;
            }
            boolean result = j<i?true:false;
            memoMap.put(s,result);
            return result;
        }
    }

    public String longestPalindrome1(String s) {
        //对于任意(i,j)处的字符串,i-1==j+1
        int sLength = s.length();
        String maxString = "";
        for(int j = 0;j<sLength;j++){
                String dpj = "";
                for(int i = j;i>=0;i--){
                    //判断[i,j]组成的字符串是否是回文串
                    if(i==j){
                        if(dpj.length()==0){
                            dpj = String.valueOf(s.charAt(i));
                        }
                    }else if(i+1==j ){
                        if(s.charAt(i)==s.charAt(j)){
                            if(dpj.length()<2){
                                dpj = s.substring(i,j+1);
                            }
                        }
                    }else{
                        String currentString = s.substring(i,j+1);

                        String subString = s.substring(i+1,j);
                        if(s.charAt(i) == s.charAt(j) && checkStringIsPalindrome(subString)){
                            memoMap.put(currentString,true);
                            if(dpj.length()<(j-i+1)){
                                dpj = s.substring(i,j+1);
                            }
                        }
                    }
                }
                if(dpj.length()>maxString.length()){
                    maxString = dpj;
                }
        }
        return maxString;
    }


    public String longestPalindrome2(String s) {
        int length = s.length();
        //这里的boolean数组的第一维代表起始位置
        //第二维代表结束位置
        boolean[][] p = new boolean[length][length];
        int maxLen = 0;
        String maxString = "";
        for(int len=1;len<=length;len++){
            for(int start = 0;start<length;start++){
                int end  = start+len-1;
                if(end>=length){
                    continue;
                }
                p[start][end] = (len == 1 || len == 2 || p[start+1][end-1]) && s.charAt(start)==s.charAt(end);
                if(p[start][end]){
                    if(end-start>=maxLen){
                        maxLen = end-start;
                        maxString = s.substring(start,end+1);
                    }
                }
            }
        }
        return maxString;
    }

    public static void main(String[] args) {
        LeetCode5_20200429 leetCode5_20200429 = new LeetCode5_20200429();
        String s = "babab";
        String s1 = leetCode5_20200429.longestPalindrome2(s);
        System.out.println(s1);
    }
}
