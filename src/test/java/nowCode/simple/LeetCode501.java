package nowCode.simple;

import leetCode.TreeNode;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class LeetCode501 {

    private Map<Integer,Integer> map = new HashMap<>();

    private void helpFindMode(TreeNode root){
        if(root==null){
            return;
        }
        Integer count = map.getOrDefault(root.val,0);
        map.put(root.val,count+1);
        helpFindMode(root.left);
        helpFindMode(root.right);
    }

    public int[] findMode(TreeNode root) {
        helpFindMode(root);
        Stack<Map.Entry<Integer,Integer>> stack = new Stack<Map.Entry<Integer,Integer>>();
        map.entrySet().stream().forEach(entry->{
            if(stack.isEmpty()){
                stack.push(entry);
            }else{
                Map.Entry<Integer,Integer> peek = stack.peek();
                if(entry.getValue()>peek.getValue()){
                    while(!stack.isEmpty()){
                        stack.pop();
                    }
                    stack.push(entry);
                }else if(entry.getValue()==peek.getValue()){
                    stack.push(entry);
                }
            }
        });
        int[] result = new int[stack.size()];
        int index = 0;
        while(!stack.isEmpty()){
            Map.Entry<Integer, Integer> pop = stack.pop();
            result[index++] = pop.getKey();
        }
        return result;
    }
}
