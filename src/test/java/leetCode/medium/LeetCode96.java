package leetCode.medium;

import leetCode.TreeNode;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    n = 2
 *    1              2
 *     \          /
 *      2        1
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class LeetCode96 {

    private int[] nums = new int[]{};
    private boolean[] visited = new boolean[]{};

    private boolean insertTreeNode(TreeNode root,int n){
        //由于不存在重复节点,所以n要么比root小,要么比root大
        if(n<root.val){
            if(root.left==null){
                root.left = new TreeNode(n);
                return true;
            }else{
                return insertTreeNode(root.left,n);

            }
        }else{
            if(root.right==null){
                root.right = new TreeNode(n);
                return true;
            }else{
                return insertTreeNode(root.right,n);
            }
        }
    }

    //深度优先遍历
    private void nodesToList(TreeNode root,List<Integer> result){
        if(root==null){
            return;
        }
        result.add(root.val);
        nodesToList(root.left,result);
        nodesToList(root.right,result);
    }

    private TreeNode buildTree(TreeNode treeNode, int n, AtomicInteger depth){
        if(treeNode==null){
            treeNode = new TreeNode(n);

        }else{
            insertTreeNode(treeNode,n);
        }
        depth.incrementAndGet();
        return treeNode;
    }

    private TreeNode remove(TreeNode root,int i,AtomicInteger depth){
        if(root.val == i){
            depth.decrementAndGet();
            return null;
        }
        //i一定是root的叶子节点
        TreeNode tmp =  root;
        TreeNode previousNode = null;
        boolean isLeft = true;
        while(tmp!=null){
            if(tmp.val<i){
                previousNode = tmp;
                tmp = tmp.right;
                isLeft = false;
            }else if(tmp.val>i){
                previousNode = tmp;
                tmp = tmp.left;
                isLeft = true;
            }else{
                if(isLeft){
                    previousNode.left = null;
                }else{
                    previousNode.right = null;
                }
                depth.decrementAndGet();
                break;
            }
        }
        return root;
    }

    private void visitPath(int[] nums, boolean[] visited, TreeNode root, AtomicInteger depth, Set<List<Integer>> result){
        if(depth.get() == nums.length){
            List<Integer> nodeList = new ArrayList<>();
            nodesToList(root,nodeList);
            if(!result.contains(nodeList)){
                result.add(nodeList);
            }
            return;
        }
        for(int i = 0 ;i<nums.length;i++){
            if(!visited[i]){
                visited[i] = true;
                root = buildTree(root,nums[i],depth);
                visitPath(nums,visited,root,depth,result);
                visited[i]=false;
                root = remove(root,nums[i],depth);
            }
        }
    }

    /**
     * 提交时候提示我时间超出限制
     *
     * @param n
     * @return
     */
    @Deprecated
    public int numTrees0(int n) {
        this.nums = new int[n];
        for(int i=1;i<=n;i++){
            nums[i-1]=i;
        }
        this.visited = new boolean[n];
        for(int i=1;i<=n;i++){
            visited[i-1]=false;
        }
        TreeNode root = null;
        AtomicInteger depth = new AtomicInteger(0);
        Set<List<Integer>> result = new HashSet<>();
        visitPath(nums,visited,root,depth,result);
        return result.size();
    }

    /**
     * 结果不对
     *
     * @param n
     * @return
     */
    @Deprecated
    public int numTrees1(int n) {
        List<TreeNode> treeNodes = numTreesHelp(n);
        return treeNodes.size();
    }

    /**
     * 对于任意一个节点i
     * 它的左子树是1到i-1 和 i+1到n
     *
     * 我们定义两个函数
     * 对于任意的元素n，G(n)表示n序列的二叉搜索树的个数
     * 对于任意的n中的一个i,以i为根节点不同的二叉搜索树的个数 F(i,n)
     * 那么G(n) = i从1到n求和 F(i,n)
     * 对于F(i,n)而言  F(i,n) = G(i-1)*G(n-i) 第一项左边n-1组成的子树个数,第二项表示右边n-i组成的子树个数
     * 所以G(n) = i从1到n求和  G(i-1)*G(n-i)
     *
     * @param n
     * @return
     */
    public int numTrees(int n){
        if(n == 1 || n == 0){
            //n = 0表示空树  n=1表示只有根节点
            return 1;
        }
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++){
               G[i] = G[j-1]*G[i-j];
            }
        }
        return G[n];
    }

    private List<TreeNode> numTreesHelp(int n){
        if(n==2){
            TreeNode root1 = new TreeNode(1);
            root1.right = new TreeNode(2);
            TreeNode root2 = new TreeNode(2);
            root2.left = new TreeNode(1);
            return Arrays.asList(root1,root2);
        }else{
            List<TreeNode> lastN_1TreeNodes = numTreesHelp(n-1);
            List<TreeNode> resultNodes = new ArrayList<>();
            for(TreeNode root : lastN_1TreeNodes){
                List<TreeNode> treeNodes = insertNodeInLeaf(root, n);
                if(!treeNodes.isEmpty()){
                    resultNodes.addAll(treeNodes);
                }
            }
            return resultNodes;
        }
    }

    private List<TreeNode> insertNodeInLeaf(TreeNode root,int n){
        if(root==null){
            return new ArrayList<>();
        }
        //因为数据不重复,所以n要么比root大  要么比root小
        if(n<root.val){
            if(root.left==null){
                TreeNode newNode = new TreeNode(n);
                TreeNode rightNode = new TreeNode(root.val);
                rightNode.right = root.right;
                newNode.right = rightNode;
                TreeNode newNode1 = new TreeNode(root.val);
                newNode1.left = new TreeNode(n);
                newNode1.right = root.right;
                return Arrays.asList(newNode,newNode1);
            }else{
                List<TreeNode> treeNodes = new ArrayList<>();
                TreeNode newRoot = insertNodeInRoot(root,n);
                List<TreeNode> leftNodes = insertNodeInLeaf(root.left,n);
                treeNodes.add(newRoot);
                for(TreeNode leftNode : leftNodes){
                    TreeNode newNode = new TreeNode(root.val);
                    newNode.left = leftNode;
                    treeNodes.add(newNode);
                }
                return treeNodes;
            }
        }else{
            if(root.right==null){
                TreeNode newNode = new TreeNode(n);
                TreeNode leftNode = new TreeNode(root.val);
                leftNode.left = root.left;
                newNode.left = leftNode;
                TreeNode newNode1 = new TreeNode(root.val);
                newNode1.left = root.left;
                newNode1.right = new TreeNode(n);
                return Arrays.asList(newNode,newNode1);
            }else{
                List<TreeNode> treeNodes = new ArrayList<>();
                TreeNode newRoot = insertNodeInRoot(root,n);
                treeNodes.add(newRoot);
                List<TreeNode> rightNodes = insertNodeInLeaf(root.right,n);
                for(TreeNode rightNode :rightNodes){
                    TreeNode newNode = new TreeNode(root.val);
                    newNode.right = rightNode;
                    treeNodes.add(newNode);
                }
                return treeNodes;
            }
        }
    }

    private TreeNode insertNodeInRoot(TreeNode root,int n){
        TreeNode newRoot = new TreeNode(n);
        if(newRoot.val<n){
            newRoot.left = root;
        }else{
            newRoot.right = root;
        }
        return newRoot;
    }

    public static void main(String[] args) {
        LeetCode96 leetCode96 = new LeetCode96();
        int result = leetCode96.numTrees(4);
        System.out.println(result);
    }
}
