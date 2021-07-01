package nowCode.medium;

import org.junit.Test;

//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
//
//
//
// 参考以下这颗二叉搜索树：
//
//      5
//    / \
//   2   6
//  / \
// 1   3
//
// 示例 1：
//
// 输入: [1,6,3,2,5]
//输出: false
//
// 示例 2：
//
// 输入: [1,3,2,6,5]
//输出: true
public class NowCode33 {

    private boolean helpVerifyPostorder(int[] postorder, int left, int right, int min, int max) {
        if (left > right) {
            return true;
        }
        if (left == right) {
            return postorder[left] > min && postorder[left] < max;
        }
        int root = postorder[right];
        if(root<min || root>max){
            return false;
        }
        //寻找分界点
        int i = left;
        for (; i < right; i++) {
            if (postorder[i] > root) {
                break;
            }
        }
        boolean leftFlag = helpVerifyPostorder(postorder, left, i - 1, min, root);
        boolean rightFlag = helpVerifyPostorder(postorder, i, right-1, root, max);
        return leftFlag && rightFlag;
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length <= 1) {
            return true;
        }
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        boolean flag = helpVerifyPostorder(postorder, 0, postorder.length - 1, min, max);
        return flag;
    }

    @Test
    public void testNowCode33(){
        int[] postorder = new int[]{1,6,3,2,5};
        boolean result = verifyPostorder(postorder);
        System.out.println("result="+result);
    }
}
