package leetCode.medium;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 *
 * 56. 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 *
 * @author chenwu on 2020.8.29
 */
public class LeetCode56_20200829 {


    public int[][] merge(int[][] intervals) {
        if(intervals==null || intervals.length ==0){
            return intervals;
        }
        Arrays.sort(intervals,(v1, v2)->v1[0]-v2[0]);
        int index = -1;
        int[][] output = new int[intervals.length][2];
        for(int i = 0;i<intervals.length;i++){
            if(index == -1 || output[index][1]<intervals[i][0]){
                output[++index] = intervals[i];
            }else{
                output[index][1] = Math.max(output[index][1],intervals[i][1]);
            }
        }
        //因为output的实际长度小于自身长度length,所以要拷贝需要的长度(index+1)
        return Arrays.copyOf(output,index+1);
    }

    public static void main(String[] args) {
        LeetCode56_20200829 leetCode56_20200829 = new LeetCode56_20200829();
        int[][] intervals = new int[][]{
                {1,4},
                {2,5}
        };
        int[][] result = leetCode56_20200829.merge(intervals);
        System.out.println(result);
    }
}
