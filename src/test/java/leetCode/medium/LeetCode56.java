package leetCode.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 * <p>
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class LeetCode56 {

    private void sort(int[][] intervals){
        int rowLength = intervals.length;
        int tmp0 = 0,tmp1 = 0;
        for(int i = 0;i<rowLength;i++){
            for(int j=i+1;j<rowLength;j++){
                if(intervals[i][0]>intervals[j][0]){
                    tmp0 = intervals[i][0];
                    tmp1 = intervals[i][1];
                    intervals[i][0] = intervals[j][0];
                    intervals[i][1] = intervals[j][1];
                    intervals[j][0] = tmp0;
                    intervals[j][1] = tmp1;
                }
            }
        }
    }

    /**
     * 先对数组进行排序 按照第一个元素优先，第二个元素其次的方法<br/>
     * 然后从左往右遍历，当第二项的左边元素比第一项的右边元素大的时候，就判断两者的上界
     * 并进行合并，形成一个新的元素,重新压入堆栈
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length<=1){
            return intervals;
        }
        sort(intervals);
        //每个元素都是一个数组，数组有两个值
        Stack<int[]> path = new Stack<>();
        for(int i = 0;i<intervals.length;i++){
            if(path.isEmpty()){
                path.push(new int[]{intervals[i][0],intervals[i][1]});
            }else{
                int[] peekArray = path.peek();
                if(intervals[i][0]>peekArray[1]){
                    //说明第二项的开始元素比第一项的结尾元素还要大,那么说明两者没有交集
                    path.push(new int[]{intervals[i][0],intervals[i][1]});
                }else{
                    //说明两者有交集,比较第一个元素的最后一项和第二个元素的最后一项
                    int maxValue = Math.max(peekArray[1],intervals[i][1]);
                    int[] pop = path.pop();
                    path.push(new int[]{pop[0],maxValue});
                }
            }
        }
        int[][] result = new int[path.size()][2];
        for(int i = 0;i<path.size();i++){
            int[] integers = path.get(i);
            result[i][0] = integers[0];
            result[i][1] = integers[1];
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode56 leetCode56 = new LeetCode56();
//        int[][] intervals = new int[][]{
//                {1,3},{2,6},{8,10},{15,18}
//
//        };
//        int[][] intervals = new int[][]{
//                {1,4},{4,5}
//
//        };
//        int[][] intervals = new int[][]{
//                {15,18},{1,3},{8,10},{2,6}
//
//        };
                int[][] intervals = new int[][]{
                {1,3},{1,2},{8,17},{15,18}

        };
        int[][] mergeArray = leetCode56.merge(intervals);
        System.out.println(Arrays.toString(mergeArray));
    }
}
