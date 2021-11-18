package nowCode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
//输入一个字符串，打印出该字符串中字符的所有排列。
//
//
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//
//
//
// 示例:
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
//
//
//
//
// 限制：
//
// 1 <= s 的长度 <= 8
public class NowCode38 {

    private List<String> res = new ArrayList<>();

    private void helpPermutation(Stack<Character> path, boolean[] isUsed, char[] charArray){
        if(path.size()==charArray.length){
            StringBuilder sb = new StringBuilder();
            for(Character item : path){
                sb.append(item);
            }
            res.add(sb.toString());
        }else{
            for(int i = 0;i<charArray.length;i++){
                if(i>0 &&  !isUsed[i-1] && charArray[i]==charArray[i-1]){
                    continue;
                }
                if(!isUsed[i]){
                    path.push(charArray[i]);
                    isUsed[i]=true;
                    helpPermutation(path,isUsed,charArray);
                    isUsed[i]=false;
                    path.pop();
                }

            }
        }
    }

    public String[] permutation(String s) {
        Stack<Character> path = new Stack<>();
        boolean[] isUsed = new boolean[s.length()];
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        helpPermutation(path,isUsed,charArray);
        String[] result = new String[res.size()];
        res.toArray(result);
        return result;
    }

    @Test
    public void testPermutation(){
        String s = "dkjphedy";
        String[] res = permutation(s);
        System.out.println("res="+ Arrays.toString(res));
    }
}
