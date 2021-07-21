package leetCode;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val,TreeNode left,TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val,Integer left,Integer right){
        this.val = val;
        if(left!=null){
            this.left = new TreeNode(left);
        }
        if(right!=null){
            this.right = new TreeNode(right);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof TreeNode)){
            return false;
        }
        TreeNode other = (TreeNode)obj;
        return this.val==other.val;
    }

    @Override
    public int hashCode() {
        return this.val;
    }

    @Override
    public String toString() {
        return "root[val="+val+"],left=["+left+"],right=["+right+"]";
    }
}
