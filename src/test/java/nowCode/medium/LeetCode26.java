package nowCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

public class LeetCode26 {

    private boolean isEquals(TreeNode t1, TreeNode t2) {
        if(t2==null){
            return true;
        }
        if(t1==null){
            return false;
        }
        if(t1.val!=t2.val){
            return false;
        }
        return isEquals(t1.left, t2.left) && isEquals(t1.right,t2.right );
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        boolean flag1 = isEquals(A,B );
        if(flag1){
            return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    @Test
    public void testIsSubStructure() {
        TreeNode A = new TreeNode(10);
        A.left = new TreeNode(12, 6, 8);
        A.right = new TreeNode(3, 11, null);
        TreeNode B = new TreeNode(10);
        B.left = new TreeNode(12, 6, 8);
        boolean result = isSubStructure(A, B);
        System.out.println("result=" + result);
    }
}
