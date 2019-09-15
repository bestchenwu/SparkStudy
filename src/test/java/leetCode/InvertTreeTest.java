package leetCode;

public class InvertTreeTest {

    public void invertNode(TreeNode left, TreeNode right) {
        TreeNode tmp = left;
        left = right;
        right = tmp;
        System.out.println("invert left:"+left);
        System.out.println("invert right:"+right);

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if(root.left==null&&root.right == null){
            return root;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        InvertTreeTest test = new InvertTreeTest();
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3));
        test.invertTree(treeNode);
        System.out.println(treeNode);
    }
}