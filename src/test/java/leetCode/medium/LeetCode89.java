package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/gray-code/
 * <p>
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * <p>
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * <p>
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * <p>
 * 示例 2:
 * <p>
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 * 给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 * 因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class LeetCode89 {

    private int[] nums = new int[]{0, 1};


    private String concatList(List<String> list) {
        String result = list.stream().reduce("", (s1, s2) -> s1 + s2);
        return result;
    }


    private void purge(Stack<String> path, List<String> result, int n) {
        if (path.size() > n) {
            return;
        }
        if (!path.isEmpty()) {
            List<String> list = new ArrayList<>(path);
            String str = concatList(list);
            result.add(str);
        }
        for (int i = 0; i < nums.length; i++) {
            path.push(String.valueOf(nums[i]));
            purge(path, result, n);
            path.pop();
        }

    }

    /**
     * 从0到n n=0的时候返回0
     * n=1的时候返回[0 1]
     * n=2的时候 返回 [0,1,3,2] = [00 01 11 10]
     * n=3的时候 返回[0 1 3 2 6 7 5 4] = [0 1 00 01 11 10 011 111 101 100 ]
     * 可以推测出
     * 结果=由[0,1]两个数字组成的长度为n的字符串全排列
     *
     * @param n
     * @return
     */
    //这实际上返回的是全排列
    @Deprecated
    public List<Integer> grayCode0(int n) {
        if (n == 0) {
            return Arrays.asList(0);
        }
        Stack<String> path = new Stack<String>();
        List<String> list = new ArrayList<>();
        purge(path, list, n);
        List<Integer> resultList = new ArrayList<>();
        list.stream().map(str -> {
            return Integer.parseInt(str, 2);
        }).distinct().forEach(number -> resultList.add(number));
        return resultList;
    }

    /**
     * 从0到n n=0的时候返回0
     * n=1的时候返回[0 1]
     * n=2的时候 返回 [0,1,3,2] = [00 01 11 10]
     * n=3的时候 返回[0 1 3 2 6 7 5 4] = [0 1 00 01 11 10 011 111 101 100 ]
     * 可以推测出
     * n=3等于n=2的时候所有的数字倒序从右往左依次加上2的(n-1)次方,也就是1<<n
     */
    public List<Integer> grayCode(int n) {
        List<Integer> grays = new ArrayList<>();
        grays.add(0);
        for(int i=0;i<n;i++){
            int add = 1<<i;
            for(int index = grays.size()-1;index>=0;index--){
                grays.add(grays.get(index)+add);
            }
        }
        return grays;
    }

    public static void main(String[] args) {
        LeetCode89 leetCode89 = new LeetCode89();
        List<Integer> integers = leetCode89.grayCode(2);
        System.out.println(integers);
    }
}
