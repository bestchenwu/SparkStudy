package leetCode.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class LeetCode56_20200430 {

    private void sort(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i][0] > intervals[j][0]) {
                    //交换intervals[i]和intervals[j]
                    int tmp1 = intervals[i][0];
                    int tmp2 = intervals[i][1];
                    intervals[i][0] = intervals[j][0];
                    intervals[i][1] = intervals[j][1];
                    intervals[j][0] = tmp1;
                    intervals[j][1] = tmp2;
                }
            }
        }
    }

    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        sort(intervals);
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < intervals.length; i++) {
            if (stack.isEmpty()) {
                stack.push(new int[]{intervals[i][0], intervals[i][1]});
            } else {
                int[] peek = stack.peek();
                if (intervals[i][0] <= peek[1]) {
                    int min = Math.min(peek[0], intervals[i][0]);
                    int max = Math.max(peek[1], intervals[i][1]);
                    stack.pop();
                    stack.push(new int[]{min, max});
                }else{
                    stack.push(new int[]{intervals[i][0],intervals[i][1]});
                }
            }
        }
        int[][] output = new int[stack.size()][2];
        for (int i = 0; i < stack.size(); i++) {
            int[] item = stack.get(i);
            output[i][0] = item[0];
            output[i][1] = item[1];
        }
        return output;
    }

    public int[][] merge(int[][] intervals) {
        if(intervals==null || intervals.length==0){
            return intervals;
        }
        Arrays.sort(intervals,(v1,v2)->v1[0]-v2[0]);
        int[][] output = new int[intervals.length][2];
        int index = -1;
        for(int[] interval :intervals){
            if(index == -1 || output[index][1]<interval[0]){
                output[++index] = interval;
            }else{
                output[index][1] = Math.min(output[index][1],interval[1]);
            }
        }
        return Arrays.copyOf(output,index+1);
    }

    public static void main(String[] args) {
        LeetCode56_20200430 leetCode56_20200430 = new LeetCode56_20200430();
        int[][] matrix = new int[][]{
                 {2, 6}, {8, 10}, {1, 3} ,{15, 18}

        };
        int[][] merge = leetCode56_20200430.merge(matrix);
        System.out.println(merge);
    }
}
