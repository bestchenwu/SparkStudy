package leetCode.medium;

import org.junit.Test;

//序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
//
//      _9_
//    /   \
//   3     2
//  / \   / \
// 4   1  #  6
/// \ / \   / \
//# # # #   # #
//
//
// 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
//
// 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
//
// 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
//
// 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
//
// 示例 1:
//
// 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
//输出: true
//
// 示例 2:
//
// 输入: "1,#"
//输出: false
//
//
// 示例 3:
//
// 输入: "9,#,#,1"
//输出: false
public class LeetCode331 {

    /**
     * //入度表示进入节点的边
     * //出度表示离开节点的边
     * //对于非叶子节点  它有一条入边,两条出边
     * //对于叶子节点  它有一条入边,0个出边
     * //对于所有二叉树 以及图来说 所有入边之和==出边之和
     * //每个节点都累加 diff = 出度-入度
     * //在任何节点之前都要求diff >=0
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        String[] split = preorder.split(",");
        if (split.length == 0) {
            return false;
        }
        if (split[0] == "#" && split.length == 1) {
            return true;
        }
        if (split[0] == "#" || split.length % 2 == 0) {
            return false;
        }
        int count = 1;
        for (int i = 0; i < split.length; i++) {
            count--;
            if (count < 0) {
                break;
            }
            if (!split[i].equals("#")) {
                count += 2;
            }
        }
        return count == 0;
    }

    @Test
    public void testIsValidSerialization() {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        boolean validSerialization = isValidSerialization(preorder);
        System.out.println("validSerialization=" + validSerialization);
    }
}
