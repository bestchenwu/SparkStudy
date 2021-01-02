package nowCode.simple;

//你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。

//
// 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
//
// 示例 1:
//
//
//输入: 二叉树: [1,2,3,4]
//       1
//     /   \
//    2     3
//   /
//  4
//
//输出: "1(2(4))(3)"
//
//解释: 原本将是“1(2(4)())(3())”，
//在你省略所有不必要的空括号对之后，
//它将是“1(2(4))(3)”。
//
//
// 示例 2:
//
//
//输入: 二叉树: [1,2,3,null,4]
//       1
//     /   \
//    2     3
//     \
//      4
//
//输出: "1(2()(4))(3)"
//
//解释: 和第一个示例相似，
//除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。

import leetCode.TreeNode;

public class LeetCode606 {

    public String tree2str(TreeNode t) {
        if(t==null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        String left = tree2str(t.left);
        String right = tree2str(t.right);
        if(left == ""){
            if(right!=""){
                sb.append("(");
                sb.append(left);
                sb.append(")");
                sb.append("(");
                sb.append(right);
                sb.append(")");
            }
        }else{
            sb.append("(");
            sb.append(left);
            sb.append(")");
            if(right != ""){
                sb.append("(");
                sb.append(right);
                sb.append(")");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2,new TreeNode(4),null);
        root.right = new TreeNode(3);
        LeetCode606 leetCode606 = new LeetCode606();
        String tree2str = leetCode606.tree2str(root);
        System.out.println(tree2str);
    }
}
