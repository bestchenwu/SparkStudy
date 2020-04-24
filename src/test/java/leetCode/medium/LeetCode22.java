package leetCode.medium;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 * <p>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class LeetCode22 {

    public void generateParenthesis1(String str, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(str);
            return;
        }
        //如果left >0 表明还可以放左括号
        if (left > 0) {
            generateParenthesis1(str + "(", left - 1, right, result);
        }
        //说明右括号比左括号少,可以继续放右括号
        if (left < right) {
            generateParenthesis1(str + ")", left, right - 1, result);
        }
    }

    public List<String> generateParenthesis1(int n) {
        List<String> list = new ArrayList<>();
        generateParenthesis1("", n, n, list);
        return list;
    }

    private char[] chars = new char[]{'(',')'};

    private boolean checkPathIsValid(Stack<Character> path){
        Stack<Character> checkPath = new Stack<>();
        for(int i = 0;i<path.size();i++){
            Character item =  path.get(i);
            if(item=='('){
                checkPath.push(item);
            }else{
                if(checkPath.isEmpty() || checkPath.peek()!='('){
                    return false;
                }else{
                    checkPath.pop();
                }
            }
        }
        return checkPath.isEmpty();
    }

    private void helpGenerateParenthesis(Stack<Character> path,List<String> list,int count,int startIndex){
        if(path.size()==count){
            //检查path是否是一个有效的括号
            boolean checkResult = checkPathIsValid(path);
            if(checkResult){
                String newResult = "";
                for(int i = 0;i<path.size();i++){
                    newResult+=path.get(i);
                }
                list.add(newResult);
            }
            return;
        }
        for(int i =0;i<2;i++){
            path.push(chars[i]);
            helpGenerateParenthesis(path,list,count,i);
            path.pop();
        }
    }

    /**
     * 回溯算法2
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis0(int n) {
        Stack<Character> path  = new Stack<>();
        List<String> list = new ArrayList<>();
        int count = n*2;
        helpGenerateParenthesis(path,list,count,0);
        return list;
    }


    private void helpGenerateParenthesis(Stack<Character> path,int open,int close,int max,List<String> list){
        if(path.size()==max*2){
            String newResult = "";
            for(int i =0;i<path.size();i++){
                newResult+=path.get(i);
            }
            list.add(newResult);
            return;
        }
        if(open<max){
            path.push('(');
            helpGenerateParenthesis(path,open+1,close,max,list);
            path.pop();
        }
        if(open>close){
            path.push(')');
            helpGenerateParenthesis(path,open,close+1,max,list);
            path.pop();
        }
    }

    public List<String> generateParenthesis(int n) {
        Stack<Character> path = new Stack<>();
        List<String> list = new ArrayList<>();
        helpGenerateParenthesis(path,0,0,n,list);
        return list;
    }

    public static void main(String[] args) {
        LeetCode22 leetCode22 = new LeetCode22();
        List<String> list = leetCode22.generateParenthesis(3);
        System.out.println(list);
    }
}
