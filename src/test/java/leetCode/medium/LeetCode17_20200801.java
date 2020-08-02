package leetCode.medium;

import org.mortbay.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author chenwu on 2020.8.1(第二遍)
 */
public class LeetCode17_20200801 {

    char[][] phoneNumbers = new char[][]{
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'},
            {'p','q','r','s'},
            {'t','u','v'},
            {'w','x','y','z'}
    };



    private void build(String digits, int index, Stack<Character> path, List<String> resultList){
        if(index==digits.length()){
            StringBuffer result = new StringBuffer();
            for(Character character:path){
                result.append(character);
            }
            resultList.add(result.toString());
            return;
        }
        int phoneIndex = digits.charAt(index)- '2';
        char[] phoneNumber = phoneNumbers[phoneIndex];
        for(int j=0;j<phoneNumber.length;j++){
            path.push(phoneNumber[j]);
            build(digits,index+1,path,resultList);
            path.pop();
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> resultList = new ArrayList<>();
        if(digits==null || digits.length()==0){
            return resultList;
        }
        Stack<Character> path = new Stack<>();
        Integer index = 0;
        build(digits,index,path,resultList);
        return resultList;
    }

    public static void main(String[] args) {
        String digits = "22";
        LeetCode17_20200801 leetCode17_20200801 = new LeetCode17_20200801();
        List<String> list = leetCode17_20200801.letterCombinations(digits);
        System.out.println(list);
    }
}
