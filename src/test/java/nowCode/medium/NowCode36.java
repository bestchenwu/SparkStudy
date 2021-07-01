package nowCode.medium;

import org.junit.Test;

//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
//
//
//
// 为了让您更好地理解问题，以下面的二叉搜索树为例：
//
//
//
//
//
//
//
// 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
//第一个节点。
//
// 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
//
//
//
//
//
//
//
// 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
//
//
//
// 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
//to-sorted-doubly-linked-list/
class Node1 {
    public int val;
    public Node1 left;
    public Node1 right;

    public Node1() {}

    public Node1(int _val) {
        val = _val;
    }

    public Node1(int _val,Node1 _left,Node1 _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
public class NowCode36 {

    private Node1 pre,head;

    public Node1 treeToDoublyList(Node1 root) {
        if(root==null){
            return null;
        }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs(Node1 root){
        if(root == null){
            return;
        }
        dfs(root.left);
        if(pre!=null){
            pre.right = root;
        }else{
            head = root;
        }
        root.left = pre;
        pre = root;
        dfs(root.right);
    }

    @Test
    public void testNowCode36(){
        Node1 root = new Node1(4);
        root.left = new Node1(2, new Node1(1), new Node1(3));
        root.right = new Node1(5);
        Node1 result = treeToDoublyList(root);
        System.out.println(result);
    }
}
