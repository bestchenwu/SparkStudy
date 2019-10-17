package leetCode;

/**
 * https://leetcode-cn.com/problems/path-sum-iii/submissions/
 *
 * @author chenwu on 2019.10.17
 */
public class LeetCode438 {

    /**
     * 对根节点采用深度优先遍历
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum1(TreeNode root,int sum){
        if(root==null){
            return 0;
        }
        int res = 0;
        if(root.val==sum){
            res+=1;
        }
        res += pathSum1(root.left,sum-root.val);
        res += pathSum1(root.right,sum-root.val);
        return res;
    }

    public int pathSum(TreeNode root, int sum) {
        if(root==null){
            return 0;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        return pathSum1(root,sum)+pathSum(leftNode,sum)+pathSum(rightNode,sum);
    }
}
