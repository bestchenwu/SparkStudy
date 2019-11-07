package leetCode.simple;
import java.util.Stack;
/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author chenwu on 2019.9.27
 */
public class LeetCode20 {

    private boolean checkCharIsPair(char left,char right){
        if(left == '(' && right == ')' ){
            return true;
        }
        if(left == '[' && right == ']'){
            return true;
        }
        if(left == '{' && right == '}'){
            return true;
        }
        return false;
    }

    public boolean isValid(String s) {
        if(s==null||s.length()==0){
            return true;
        }
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char item = s.charAt(i);
            if(stack.isEmpty()){
                stack.push(item);
            }else{
                char topChar = stack.peek();
                if(checkCharIsPair(topChar,item)){
                    stack.pop();
                }else{
                    stack.push(item);
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        LeetCode20 test = new LeetCode20();
        boolean result = test.isValid("{[]}");
        System.out.println(result);
    }
}
