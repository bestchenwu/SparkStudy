package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class LeetCode300_20200428 {

    public int lengthOfLIS(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        //假定我们保存了一个dp  Map<int,List<Integer>> 表示在i位置的所有上升子序列
        //在循环到i+1位置的时候，如果子序列的结尾小于num[i+1],则子序列加上num[i+1]
        //但是所有加完后，要做一次过滤
        //对于所有的列表，如果结尾元素的值比num[i+1]大或者等于，则只取最长的长度
        //近一步减缓，这里的dp可以用List<List<Integer>>表示
        List<List<Integer>> dpList = new ArrayList<>();
        List<Integer> dp0 = new ArrayList<>();
        dp0.add(nums[0]);
        dpList.add(dp0);
        for(int i = 1;i<nums.length;i++){
            int currentNum = nums[i];
            List<List<Integer>> increasedList = new ArrayList<>();
            for(List<Integer> list:dpList){
                if(list.get(list.size()-1)<currentNum){
                    list.add(currentNum);
                }else{
                    List<Integer> newDpList = new ArrayList<>();
                    for(int num : list){
                        if(num<currentNum){
                            newDpList.add(num);
                        }else{
                            break;
                        }
                    }
                    newDpList.add(currentNum);
                    increasedList.add(newDpList);
                }
            }
            if(!increasedList.isEmpty()){
                List<Integer> maxList = increasedList.stream().max((list1,list2)->Integer.valueOf(list1.size()).compareTo(list2.size())).get();
                List<List<Integer>> removeList = new ArrayList<>();
                for(List<Integer> list : dpList){
                    if(list.size()<=maxList.size()){
                        removeList.add(list);
                    }
                }
                dpList.removeAll(removeList);
                dpList.add(maxList);
            }

        }
        int result = 0;
        for(List<Integer> list:dpList){
            result = Math.max(result,list.size());
        }
        return result;
    }

    /**
     * dp动态规划 时间复杂度o(n*n) 空间复杂度o(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if(nums==null || nums.length==0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLength = dp[0];
        for(int i = 1;i<nums.length;i++){
            int maxJlength = 0;
            for(int j=i;j>=0;j--){
                if(nums[i] >nums[j]){
                    maxJlength = Math.max(maxJlength,dp[j]);
                }
            }
            dp[i] = maxJlength+1;
            maxLength = Math.max(maxLength,dp[i]);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LeetCode300_20200428 leetCode300_20200428 = new LeetCode300_20200428();
        //int[] nums = new int[]{10,9,2,5,3,7,101,18};
        //int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        int[] nums = new int[]{3,5,6,2,5,4,19,5,6,7,12};
        int result = leetCode300_20200428.lengthOfLIS(nums);
        System.out.println(result);
    }
}
