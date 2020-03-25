package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 */
public class LeetCode152 {


    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int imin = 1;
        int imax = 1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<0){
                int tmp = imin;
                imin = imax;
                imax = tmp;
            }
            imin = Math.min(imin*nums[i],nums[i]);
            imax = Math.max(imax*nums[i],nums[i]);
            max = Math.max(imin,imax);
        }
        return max;
    }

    public int maxProduct1(int[] nums) {
        if(nums==null){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        List<int[]> dpList = new ArrayList<>();
        int lastIndex = nums.length-1;
        //从左至右依次为最大子乘积开始的左坐标，右边坐标，最大子乘积,当前的总乘积
        int[] dp0 = new int[]{0,0,nums[0],nums[0]};
        dpList.add(dp0);
        int[] currentDp = new int[]{};
        int[] lastDp;
        int currentNum = 0;
        int lastDpNum = 0;
        int currentTotalNum = 0;
        for(int i=1;i<=lastIndex;i++){
            lastDp = dpList.get(i-1);
            currentNum = nums[i];
            lastDpNum = lastDp[2];
            //当前的总乘积
            currentTotalNum = lastDp[3]*currentNum;
            //从lastDP的子乘积后到i的乘积
            int lastNotProductNum = 1;
            int lastLeftIndex = 0;
            int maxLastNotProductNum = -1;
            for(int k=i;k>=lastDp[1];k--){
                lastNotProductNum = lastNotProductNum*nums[k];
                maxLastNotProductNum = Math.max(lastNotProductNum,maxLastNotProductNum);
                if(maxLastNotProductNum == lastNotProductNum){
                    lastLeftIndex = k;
                }
            }
            int max = Math.max(currentNum,Math.max(lastDpNum,Math.max(currentTotalNum,maxLastNotProductNum)));
            if(max == lastDpNum){
                currentDp = new int[]{lastDp[0],lastDp[1],lastDpNum,currentTotalNum};
                dpList.add(currentDp);
                continue;
            }
            if(max==currentNum){
                currentDp = new int[]{i,i,currentNum,currentTotalNum};
                dpList.add(currentDp);
                continue;
            }
            if(max==currentTotalNum){
                currentDp = new int[]{0,i,currentTotalNum,currentTotalNum};
                dpList.add(currentDp);
                continue;
            }
            if(max == maxLastNotProductNum){
                currentDp = new int[]{lastLeftIndex,i,maxLastNotProductNum,currentTotalNum};
                dpList.add(currentDp);
            }
        }
        int[] finalDp = dpList.get(lastIndex);
        return finalDp[2];
    }

    /**
     * 假设dp[i] = new int[]{k1,k2,num} 表示从k1到k2的最大子序列乘积,开始坐标是k1,结束坐标是k2
     * 那么dp[i+1] = if (k2 == i ) num>0?dp[i]*nums[i+1]:dp[i]
     *               else 那么说明从k2到i的乘积肯定是一个负数,这时候 if nums[i+1]<0  dp[i+1] = dp[i] * (k2到i+1) else dp[i]
     *
     *
     * @param nums
     * @return
     */
    public int maxProduct0(int[] nums) {
        if(nums==null){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        List<int[]> dpList = new ArrayList<>();
        int[] dp0 = new int[]{0,0,nums[0]};
        dpList.add(dp0);
        int lastIndex = nums.length-1;
        for(int i = 1;i<=lastIndex;i++){
            int[] currentDp;
            int[] lastDp = dpList.get(i-1);
            int currentNum = nums[i];
            int lastDpNum = lastDp[2];
            if(lastDp[1] == i-1){
                if(currentNum==0){
                    if(lastDpNum<=0){
                        currentDp = new int[]{i,i,currentNum};
                    }else{
                        currentDp = new int[]{lastDp[0],lastDp[1],lastDpNum};
                    }
                }else if(lastDpNum==0){
                    if(currentNum>0){
                        currentDp = new int[]{i,i,currentNum};
                    }else{
                        currentDp = new int[]{lastDp[0],lastDp[1],lastDpNum};
                    }

                }else if(lastDpNum<0 && currentNum<0 || lastDpNum>0 && currentNum>0){
                    currentDp = new int[]{lastDp[0],i,lastDpNum*currentNum};
                }else{
                    if(currentNum>=lastDpNum){
                        currentDp = new int[]{i,i,currentNum};
                    }else{
                        currentDp = new int[]{lastDp[0],lastDp[1],lastDpNum};
                    }
                }
            }else{
                if(lastDpNum>0 && currentNum>0 || lastDpNum<0 && currentNum<0){
                    if(currentNum>=lastDpNum){
                        currentDp = new int[]{i,i,currentNum};
                    }else{
                        currentDp = new int[]{lastDp[0],lastDp[1],lastDpNum};
                    }
                }else{
                    int num = 1;
                    for(int j=lastDp[1]+1;j<=i;j++){
                        num*=nums[j];
                    }
                    currentDp = new int[]{lastDp[0],i,lastDpNum*num};
                }
            }
            dpList.add(currentDp);
        }
        int[] lastDp = dpList.get(lastIndex);
        return  lastDp[2];
    }

    public static void main(String[] args) {
        LeetCode152 leetCode152 = new LeetCode152();
        int[] nums = new int[]{2,3,-2,4};
        //int[] nums = new int[]{-2,0,-1};
        //int[] nums = new int[]{3,-1,4};
        //int[] nums = new int[]{-2,0,-1};
        //int[] nums = new int[]{-2,3,-4};
        //int[] nums = new int[]{2,-5,-2,-4,3};
        int maxProduct = leetCode152.maxProduct(nums);
        System.out.println("maxProduct="+maxProduct);
    }
}
