package leetCode.simple;

import org.junit.Test;

//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
//
//
// 示例 1：
//
//
//输入：strs = ["flower","flow","flight"]
//输出："fl"
//
//
// 示例 2：
//
//
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 200
// 0 <= strs[i].length <= 200
// strs[i] 仅由小写英文字母组成
//https://leetcode.cn/problems/longest-common-prefix/
public class LeetCode14_20220616 {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        String str = strs[0];
        while(str.length()>0){
            int i = 1;
            for(;i<strs.length;i++){
                if(strs[i].indexOf(str)!=0){
                    break;
                }
            }
            if(i==strs.length){
                return str;
            }else{
                str = str.substring(0,str.length()-1);
            }
        }
        return str;
    }

    @Test
    public void testLongestCommonPrefix(){
        String[] strs = new String[]{"c","acc","ccc"};
        String str = longestCommonPrefix(strs);
            System.out.println(str);
    }
}
