package nowCode.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 *
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 */
public class NowCode57_2 {

    public int[][] findContinuousSequence(int target) {
        List<Integer[]> list = new ArrayList<>();
        int left =1,right = left,max = target/2 +1 ,sum = 0;
        while(right<=max){
            sum+=right;
            if(sum<target){
                right+=1;
                continue;
            }else if(sum == target){
                Integer[] res = new Integer[right-left+1];
                int column = 0;
                //将从left到right都输出到res中
                for(int num = left;num<=right;num++){
                    res[column] = num;
                    column+=1;
                }
                left = left+1;
                right = left;
                sum = 0;
                list.add(res);
            }else{
                sum = 0;
                left = left+1;
                right = left;
            }
        }
        int row = list.size();
        int index = 0;
        int[][] res = new int[row][];
        for(;index<row;index++){
            Integer[] integers = list.get(index);
            res[index]=new int[integers.length];
            for(int i = 0;i<integers.length;i++){
                res[index][i] = integers[i];
            }
        }
        return res;
    }

    public int[][] findContinuousSequence2(int target){
        List<int[]> list = new ArrayList<>();
        int l = 1,r=l+1,sum;
        while(l<r){
            sum = (l+r)*(r-l+1)/2;
            if(sum==target){
                int[] targetColumns = new int[r-l+1];
                for(int i = l;i<=r;i++){
                    targetColumns[i-l] = i;
                }
                list.add(targetColumns);
                l+=1;
            }else if(sum<target){
                r+=1;
            }else{
                l+=1;
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        int target = 15;
        NowCode57_2 test = new NowCode57_2();
        int[][] res = test.findContinuousSequence2(target);
        for(int j =0;j<res.length;j++){
            System.out.println(Arrays.toString(res[j]));
        }

    }
}
