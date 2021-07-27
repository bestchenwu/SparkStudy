package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

//返回与给定的前序和后序遍历匹配的任何二叉树。
//
// pre 和 post 遍历中的值是不同的正整数。
//
//
//
// 示例：
//
// 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
//输出：[1,2,3,4,5,6,7]
//
//
//
//
// 提示：
//
//
// 1 <= pre.length == post.length <= 30
// pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
// 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
import java.util.*;

public class LeetCode889 {

    //todo:可见:https://blog.csdn.net/m0_47671600/article/details/106019902
    // 新建两个下标，分别指向前序遍历结果和后序遍历结果
    int preIndex = 0;
    int postIndex = 0;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        // 新建一个父节点，建立之后 preIndex 向右移动一位
        TreeNode root = new TreeNode(pre[preIndex++]);
        // （前提条件，父节点不是叶子节点）建立左子树
        if(root.val != post[postIndex]){
            root.left = constructFromPrePost(pre, post);
        }
        // （前提条件，父节点不是叶子节点）左子树建立完毕，建立右子树
        if(root.val != post[postIndex]){
            root.right = constructFromPrePost(pre, post);
        }
        // 这里的 postIndex++ 很关键（理解）
        postIndex++;
        // 走到这一步的时候，一般情况会是：没有左右孩子（叶子节点 root.val == post[postIndex]）
        // 或者左右孩子的递归已经返回（那么此时 root.val 必定等于 post[postIndex] 后序递归：左右中）
        return root;
    }

    @Test
    public void helpConstructFromPrePost(){
        int[] pre = new int[]{1,2,4,5,3,6,7};
        int[] post = new int[]{4,5,2,6,7,3,1};
        TreeNode root = constructFromPrePost(pre,post);
        System.out.println("root="+root);
    }
}
