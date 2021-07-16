package leetCode.medium;

/**
 * 带下一个节点指针的树node
 *
 * @author chenwu on 2021.7.16
 */
public class LinkTreeNode {

    public int val;
    public LinkTreeNode left;
    public LinkTreeNode right;
    public LinkTreeNode next;

    public LinkTreeNode() {}

    public LinkTreeNode(int _val) {
        val = _val;
    }

    public LinkTreeNode(int _val,LinkTreeNode _left, LinkTreeNode _right){
        this.val = _val;
        this.left = _left;
        this.right = _right;
    }

    public LinkTreeNode(int _val, LinkTreeNode _left, LinkTreeNode _right, LinkTreeNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
