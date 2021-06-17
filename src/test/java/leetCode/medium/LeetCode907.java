package leetCode.medium;


import java.util.Stack;

//给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
//
// 由于答案可能很大，因此 返回答案模 10^9 + 7 。
//
//
//
// 示例 1：
//
//
//输入：arr = [3,1,2,4]
//输出：17
//解释：
//子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
//最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
//
// 示例 2：
//
//
//输入：arr = [11,81,94,43,3]
//输出：444
//
//
//
//
// 提示：
//
//
// 1 <= arr.length <= 3 * 104
// 1 <= arr[i] <= 3 * 104
public class LeetCode907 {

    //https://pic.leetcode-cn.com/2f59d73899312f0c53eafdc5fc71daa31e221036b8eaca7e897a30c57b44b648-clipboard.png

    int MOD = 1000000007;

    public int sumSubarrayMins(int[] arr) {
        Stack<Pair> stack = new Stack<>();
        int tmp = 0, result = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int count = 1;
            while (!stack.isEmpty() && arr[i] <= stack.peek().value) {
                Pair pair = stack.pop();
                count += pair.count;
                tmp -= pair.count * pair.value;
            }
            stack.push(new Pair(arr[i],count));
            tmp += arr[i] * count;
            result += tmp;
            result %= MOD;
        }
        return result;
    }

    class Pair {
        //数组的值
        public int value;
        //从它开始,之前所有比它大的元素个数
        public int count;

        public Pair(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
