package leetCode.medium;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 * <p>
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class LeetCode739 {

    /**
     * 构造一个map,用于保存当前温度对应的比它大的天数
     * 同时构造一个栈,保存未找到比它高的温度,栈里面循环的次数就是匹配的天数
     * 从右往左构造
     *
     * @param T
     * @return
     */
    //todo:效率不高
    public int[] dailyTemperatures1(int[] T) {
        Stack<Integer> matchedStack = new Stack<>();
        Stack<Integer> unMatchedStack = new Stack<Integer>();
        int length = T.length;
        for (int i = length - 1; i >= 0; i--) {
            int currentTemperature = T[i];
            if (!unMatchedStack.isEmpty()) {
                Integer peekTemperature = unMatchedStack.peek();
                if (peekTemperature > currentTemperature) {
                    matchedStack.push(1);
                } else {
                    int top_size = unMatchedStack.size() - 1;
                    int current_length = 0;
                    while (top_size >= 0) {
                        current_length += 1;
                        peekTemperature = unMatchedStack.get(top_size);
                        if (peekTemperature > currentTemperature) {
                            break;
                        }
                        top_size--;
                    }
                    if (top_size >= 0) {
                        matchedStack.push(current_length);
                    } else {
                        matchedStack.push(0);
                    }
                }
            } else {
                matchedStack.push(0);
            }
            unMatchedStack.push(currentTemperature);
        }
        int[] result = new int[matchedStack.size()];
        int index = 0;
        while (!matchedStack.empty()) {
            result[index++] = matchedStack.pop();
        }
        return result;
    }

    /**
     * 首先建立一个int[] result，用于保存结果，即比当前温度高的下一个温度的下标差距。
     * 其次建立一个单调递减的堆栈，从栈底到栈顶单调递减，堆栈里保存温度的索引
     *
     * 具体逻辑如下:
     * 1、从右往左循环T数组,每次循环获得的索引计为i
     * 2、每次进来一个新的温度,计为t0，都与当前栈的栈顶索引对应的t[栈顶索引](计为tMax)进行比较。
     *    如果t0>=tMax,则将tMax弹出，并继续循环获取栈顶元素重复2判断
     * 3、如果2步骤结束,则判断栈是否为空，如果为空,说明当前温度是最大的，
     *      将result[i] = 0
     *    如果不为空,则说明堆栈中有比t0大的温度。那么:
     *      result[i] = 栈顶索引-i
     * 4、将当前温度压入堆栈,继续保持单调递减栈
     * 5、重复步骤1
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = length - 1; i >= 0; i--) {
            int currentTemperature = T[i];
            while (!stack.isEmpty() && currentTemperature >= T[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] num = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        LeetCode739 leetCode739 = new LeetCode739();
        int[] dailyTemperatures = leetCode739.dailyTemperatures(num);
        System.out.println(Arrays.toString(dailyTemperatures));
    }

}
