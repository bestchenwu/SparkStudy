package leetCode.medium;

import leetCode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode106_1 {

    private int[] inorder;
    private int[] postorder;
    private Map<Integer,Integer> inorderIndexMap = new HashMap<>();
    private Map<Integer,Integer> postorderIndexMap = new HashMap<>();
    private int postOrderIndex ;

    private TreeNode helpBuildTree(int leftInorderIndex,int rightInorderIndex){
        if(leftInorderIndex>rightInorderIndex){
            return null;
        }
        int rootNodeVal = postorder[postOrderIndex];
        TreeNode rootNode = new TreeNode(rootNodeVal);
        postOrderIndex--;
        int inorderIndex = inorderIndexMap.get(rootNodeVal);
        //注意这里要先遍历右边，再遍历左边
        rootNode.right = helpBuildTree(inorderIndex+1,rightInorderIndex);
        rootNode.left = helpBuildTree(leftInorderIndex,inorderIndex-1);
        return rootNode;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder==null || inorder.length==0){
            return null;
        }
        this.inorder = inorder;
        this.postorder = postorder;
        for(int i = 0;i<inorder.length;i++){
            inorderIndexMap.put(inorder[i],i);
        }
        for(int i = 0;i<postorder.length;i++){
            postorderIndexMap.put(postorder[i],i);
        }
        postOrderIndex = postorder.length-1;
        TreeNode rootNode = helpBuildTree(0,inorder.length-1);
        return rootNode;
    }

    public static void main(String[] args) {

    }
}
