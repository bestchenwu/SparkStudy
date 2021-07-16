package leetCode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
//
//
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
//
// 初始状态下，所有 next 指针都被设置为 NULL。
//
//
//
// 进阶：
//
//
// 你只能使用常量级额外空间。
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
//
//
//
//
// 示例：
//
//
//
//
//输入：root = [1,2,3,4,5,6,7]
//输出：[1,#,2,3,#,4,5,6,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由
//next 指针连接，'#' 标志着每一层的结束。
public class LeetCode116 {

    private void helpConnect(List<LinkTreeNode> nodeList) {
        if (nodeList.isEmpty()) {
            return;
        }
        List<LinkTreeNode> newNodeList = new ArrayList<>();
        int size = nodeList.size();
        for (int i = 0; i < size; i++) {
            LinkTreeNode tmp = nodeList.get(i);
            if (i + 1 < size) {
                tmp.next = nodeList.get(i + 1);
            } else {
                tmp.next = null;
            }
            if (tmp.left != null) {
                newNodeList.add(tmp.left);
            }
            if (tmp.right != null) {
                newNodeList.add(tmp.right);
            }
        }
        helpConnect(newNodeList);
    }

    /**
     * 广度优先遍历
     *
     * @param root
     * @return
     */
    public LinkTreeNode connect(LinkTreeNode root) {
        if(root == null){
            return null;
        }
        List<LinkTreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        helpConnect(nodeList);
        return root;
    }

    @Test
    public void testConnect() {
        LinkTreeNode root = new LinkTreeNode(1);
        root.left = new LinkTreeNode(2, new LinkTreeNode(4), new LinkTreeNode(5));
        root.right = new LinkTreeNode(3, new LinkTreeNode(6), new LinkTreeNode(7));
        LinkTreeNode newRoot = connect(root);
        System.out.println("newRoot=" + newRoot);

    }
}
