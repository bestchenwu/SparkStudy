package leetCode.medium;

import org.junit.Test;

import java.util.Stack;

//给你一个字符串 s 和两个整数 x 和 y 。你可以执行下面两种操作任意次。
//
//
// 删除子字符串 "ab" 并得到 x 分。
//
//
// 比方说，从 "cabxbae" 删除 ab ，得到 "cxbae" 。
//
//
// 删除子字符串"ba" 并得到 y 分。
//
// 比方说，从 "cabxbae" 删除 ba ，得到 "cabxe" 。
//
//
//
//
// 请返回对 s 字符串执行上面操作若干次能得到的最大得分。
//
//
//
// 示例 1：
//
// 输入：s = "cdbcbbaaabab", x = 4, y = 5
//输出：19
//解释：
//- 删除 "cdbcbbaaabab" 中加粗的 "ba" ，得到 s = "cdbcbbaaab" ，加 5 分。
//- 删除 "cdbcbbaaab" 中加粗的 "ab" ，得到 s = "cdbcbbaa" ，加 4 分。
//- 删除 "cdbcbbaa" 中加粗的 "ba" ，得到 s = "cdbcba" ，加 5 分。
//- 删除 "cdbcba" 中加粗的 "ba" ，得到 s = "cdbc" ，加 5 分。
//总得分为 5 + 4 + 5 + 5 = 19 。
//
// 示例 2：
//
// 输入：s = "aabbaaxybbaabb", x = 5, y = 4
//输出：20
public class LeetCode1717 {

    public int maximumGain(String s, int x, int y) {
        //我们认为ab的分数大,如果不是,那么交换x,y 同时将字符串反转
        if(x<y){
            int tmp = x;
            x = y;
            y = tmp;
            s = new StringBuilder(s).reverse().toString();
        }
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        int count = 0;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)!='b'){
                stack1.push(s.charAt(i));
            }else{
                if(!stack1.isEmpty() && stack1.peek()=='a'){
                    stack1.pop();
                    count+=x;
                }else{
                    stack1.push(s.charAt(i));
                }
            }
        }
        while(!stack1.isEmpty()){
            char tmp = stack1.pop();
            if(stack2.isEmpty()){
                stack2.push(tmp);
                continue;
            }
            if(tmp == 'b' && stack2.peek()=='a'){
                stack2.pop();
                count+=y;
            }else{
                stack2.push(tmp);
            }
        }
        return count;
    }

    @Test
    public void testMaximumGain(){
        String str = "cdbcbbaaabab";
        int x = 4,y = 5;
        int maxGain = maximumGain(str, x,y);
        System.out.println("maxGain="+maxGain);
    }
}
