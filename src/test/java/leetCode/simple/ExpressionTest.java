package leetCode.simple;
import org.junit.Test;

import java.util.Stack;
public class ExpressionTest {

    /**
     * 请不要使用内置库函数eval 字符串表达式仅包含整数、+、-两种运算符。没有括号。
     * 示例1：
     * 输入 "-3+22+2"
     * 输出：21
     * 示例2：
     * 输入 "3-2"
     * 输出：1
     *
     * @param str
     * @return
     */
    public int cal(String str){

        if(str==null || str.length()==0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int  sign = 1;
        int num = 0;
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i)=='-' || str.charAt(i)=='+'){
                if(num!=0){
                    num = num*sign;
                    if(stack.isEmpty()){
                        stack.push(num);
                    }else{
                        int top = stack.pop();
                        int newNum = top+num;
                        stack.push(newNum);
                    }
                    num = 0;
                }
                sign = (str.charAt(i)=='+')?1:-1;
            }else{
                num = num*10+(str.charAt(i)-'0');
            }
        }
        int sum = stack.pop();
        if(num!=0){
            sum+=num*sign;
        }
        return sum;
    }


    public long getBigNumber(String str,int cnt){
        if(str==null || str.length()==0  || cnt>str.length()){
            return 0;
        }
        int index = 0;
        long sum = 0;
        Stack<Integer> stack = new Stack<>();
        int deleteNum = 0;
        for(int i = 0;i<str.length();i++){
            int num = str.charAt(i)-'0';
            while(!stack.isEmpty() && stack.peek()<num && deleteNum<cnt){
                stack.pop();
                deleteNum++;
            }
            stack.push(num);
        }
        for(int i = 0;i<stack.size();i++){
            sum = sum*10+stack.get(i);
        }
        return sum;
    }

    @Test
    public void testGetBigNumber(){
        String str = "333544";
        int cnt = 3;
        long bigNumber = getBigNumber(str, cnt);
        System.out.println(bigNumber);
    }

    //@Test
    public void testCal(){
        String str = "-3+22+2";
        int cal = cal(str);
        System.out.println(cal);
    }
}
