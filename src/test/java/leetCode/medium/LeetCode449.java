package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
//
// 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序
//列化为最初的二叉搜索树。
//
// 编码的字符串应尽可能紧凑。
//
//
//
// 示例 1：
//
//
//输入：root = [2,1,3]
//输出：[2,1,3]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点数范围是 [0, 104]
// 0 <= Node.val <= 104
// 题目数据 保证 输入的树是一棵二叉搜索树。
public class LeetCode449 {

    //[2 1 3]输出(1)(3)2 左右根的方式
    //(((4)(5)1)(3)2)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        StringBuilder sb = new StringBuilder();
        if(left.length()>0){
            sb.append(left);
            sb.append(" ");
        }
        if(right.length()>0){
            sb.append(right);
            sb.append(" ");
        }
        sb.append(root.val);
        return sb.toString();
    }

    private TreeNode help(int min,int max,Deque<Integer> queue){
        if(queue.isEmpty()){
            return null;
        }
        int rootVal = queue.peekLast();
        if(rootVal<min || rootVal>max){
            return null;
        }
        TreeNode root = new TreeNode(queue.pollLast());
        root.right = help(rootVal,max,queue);
        root.left = help(min,rootVal,queue);
        return root;

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data==null){
            return null;
        }
        String[] array = data.trim().split("\\s+");
        if(array.length==0){
            return null;
        }
        Deque<Integer> queue = new LinkedList<>();
        for(String item : array){
            if(item.length()>0){
                queue.add(Integer.parseInt(item));
            }
        }
        TreeNode root = help(Integer.MIN_VALUE, Integer.MAX_VALUE, queue);
        return root;
    }

    /**
     *     5
     *   4    7
     * 1     6  8
     *         9
     */
    @Test
    public void test(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4,new TreeNode(1),null);
        root.right = new TreeNode(7,new TreeNode(6),new TreeNode(8, new TreeNode(9), null));
        String serialize = serialize(root);
        System.out.println("serialize="+serialize);
        TreeNode newRoot = deserialize(serialize);
        System.out.println(newRoot);
    }
}
