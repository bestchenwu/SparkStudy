package leetCode.medium;

import leetCode.TreeNode;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 *     5
 *    / \
 *   1   7
 *  / \  / \
 * 2   4  3   9
 *
 *    10
 *   5   15
 *  null null 6 20
 *
 */
public class LeetCode98 {

    boolean validFlag = true;

    /**
     * 先遍历左子树，找到左子树的最大值,显然该值就是rootVal
     * 那么右子树的所有节点值都不能小于该值
     *
     * @param root
     * @param superRootValue
     * @return
     */
    private boolean isValidBSTHelp(TreeNode root,int superRootValue,boolean toBeLarge){
        if(root==null){
            return true;
        }
        if(validFlag==false){
            return false;
        }
        int rootVal = root.val;
        if(toBeLarge==false){
            if(rootVal>=superRootValue){
                return false;
            }
        }else{
            if(rootVal<=superRootValue){
                return false;
            }
        }
        if(root.left!=null){
            int rootLeftVal = root.left.val;
            if(rootLeftVal>=rootVal){
                return false;
            }

            validFlag = validFlag&&isValidBSTHelp(root.left,root.val,toBeLarge);
            if(validFlag==false){
                return false;
            }
        }

        if(root.right!=null){
            int rootRightVal = root.right.val;
            if(rootRightVal<=rootVal){
                return false;
            }
                validFlag = validFlag&&isValidBSTHelp(root.right,root.val,toBeLarge);
            if(validFlag==false){
                return false;
            }
        }
        return validFlag;
    }

    /**
     * 1、对于一颗搜索二叉树而言，每个节点都存在一个上限和下限，假定上限定义为upper,下限定义为lower
     * root
     * left right
     * left.left left.right right.left right.right
     *
     * 那么对于left而言,下限为null,上限为rootVal
     * 对于right而言，下限为rootVal,上限为null
     * 对于left的左边子节点而言,下限为null,上限为rootVal,这里的上限是它的根节点的值,下限来自于left的传承
     * 对于left的右边子节点而言,下限为left.val,上限为rootVal,而这里的上限就是来自于left的传承,下限为根节点的值
     * 对于right的左边子节点而言,下限为rootVal,上限为right.val,这里的上限为根节点的值,下限也是来自于right的传承
     * 对于right的右边子节点而言,下限为right.val,上限为null,这里的上限来自于right的传承,下限为根节点的值
     *
     *
     *
     * 所以我们总结下，
     * 假定存在一个方法help(root,upper,lower)用于判断root是否为二叉搜索树
     * 对于任意一颗节点root，假定它上界为upper,下界限为lower
     * 判断它的左子节点是否是二叉搜索树 help(root.left,root.val,lower)
     * 判断它的右子节点是否是二叉搜索树 help(root.right,upper,root.val)
     *
     * 所以我们有了下面的方法
     * public boolean help(TreeNode root,Integer upper,Integer lower){
     * if(root==null){
     * return true;
     * }
     * int rootVal = root.val;
     * if(upper!=null && root>=upper ) return false;
     * if(lower!=null&&root<=lower) return false;
     * if(!help(root.left,rootVal,lower)) return false;
     * if(!help(root.right,upper,rootVal)) return false;
     * return true;
     * }
     *
     * @param root
     * @param lower
     * @param upper
     * @return
     */
    private boolean isValidBST0(TreeNode root,Integer lower,Integer upper){
        if(root==null){
            return true;
        }
        int rootVal = root.val;
        if(lower!=null && rootVal<=lower){
            return false;
        }
        if(upper!=null && rootVal>=upper){
            return false;
        }
        if(!isValidBST0(root.left,lower,rootVal)){
            return false;
        }
        if(!isValidBST0(root.right,rootVal,upper)){
            return false;
        }
        return validFlag;
    }

    /**
     * root.left<root.val
     * root.right>root.val
     * isValidBST(root.left)&&isValidBST(root.right)
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
                if(root==null){
                    return true;
                }
                return isValidBST0(root,null,null);
    }

    public static void main(String[] args) {
        LeetCode98 leetCode98 = new LeetCode98();
        //TreeNode root = new TreeNode(5,new TreeNode(1,new TreeNode(2),new TreeNode(4)),new TreeNode(7,new TreeNode(8),new TreeNode(9)));
        //TreeNode root = new TreeNode(2,new TreeNode(1),new TreeNode(3));
        TreeNode root = new TreeNode(10,new TreeNode(5),new TreeNode(15,new TreeNode(6),new TreeNode(20)));
        boolean validBST = leetCode98.isValidBST(root);
        System.out.println("validBST="+validBST);
    }
}
