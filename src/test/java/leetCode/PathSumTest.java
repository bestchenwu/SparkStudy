package leetCode;

/**
 * https://leetcode-cn.com/problems/path-sum-iii/
 * <p>
 * 计算二叉树里面路径之和等于sum的路径个数
 */
public class PathSumTest {

    /**
     * 以根节点开始,到任意一个节点结束,只要路径和等于sum,则计算为成功
     *
     * @param root
     * @param sum
     * @return
     */
    private int paths(TreeNode root,int sum){
        if(root==null){
            return 0;
        }
        int res = 0;
        if(root.val==sum){
            res+=1;
        }
        res+=paths(root.left,sum-root.val);
        res+=paths(root.right,sum-root.val);
        return res;
    }

    /**
     * 对于树而言，可以视为从左节点 根节点 右节点出发都可
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
       return paths(root,sum)+pathSum(root.left,sum)+pathSum(root.right,sum);

    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        node4.right = node5;

        TreeNode node3 = new TreeNode(3);
        node3.right = node4;

        TreeNode node2 = new TreeNode(2);
        node2.right = node3;

        TreeNode node1 = new TreeNode(1);
        node1.right = node2;

        PathSumTest test = new PathSumTest();
        int result = test.pathSum(node1,3);
        System.out.println(result);
    }
}
