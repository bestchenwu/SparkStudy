package nowCode.medium;

import org.junit.Test;

import java.util.*;

public class LeetCode0303 {

    private LinkedList<Stack<Integer>> list = new LinkedList<>();
    private int cap;

    public LeetCode0303(int cap) {
        this.cap = cap;
    }

    public void push(int val) {
        if(this.cap<=0){
            return;
        }
        if(list.isEmpty() || list.get(list.size()-1).size()==cap){
            Stack<Integer> stack = new Stack<>();
            stack.push(val);
            list.addLast(stack);
        }else{
            list.get(list.size()-1).push(val);
        }
    }

    public int pop() {
       return popAt(list.size()-1);
    }

    public int popAt(int index) {
        if(index<0 || index>=list.size()){
            return -1;
        }
        Stack<Integer> stack = list.get(index);
        if(stack.isEmpty()){
            return -1;
        }
        int res = stack.pop();
        if(stack.isEmpty()){
            list.remove(index);
        }
        return res;
    }

    public static void main(String[] args) {
//        LeetCode0303 leetCode0303 = new LeetCode0303(1);
//        leetCode0303.push(1);
//        leetCode0303.push(2);
//        int result = leetCode0303.popAt(1);
//        System.out.println("result="+result);
//        result = leetCode0303.pop();
//        System.out.println("result="+result);
//        result = leetCode0303.pop();
//        System.out.println("result="+result);
        LeetCode0303 leetCode0303 = new LeetCode0303(2);
        leetCode0303.push(1);
        leetCode0303.push(2);
        leetCode0303.push(3);
        int result = leetCode0303.popAt(0);
        System.out.println("result=" + result);
        result = leetCode0303.popAt(0);
        System.out.println("result=" + result);
        result = leetCode0303.popAt(0);
        System.out.println("result=" + result);
    }
}
