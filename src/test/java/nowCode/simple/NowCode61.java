package nowCode.simple;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
 *
 * 剑指 Offer 61. 扑克牌中的顺子
 *
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 *
 * 示例 2:
 *
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 *
 * 限制：
 *
 * 数组长度为 5
 *
 * 数组的数取值为 [0, 13] .
 */
public class NowCode61 {

    public boolean isStraight1(int[] nums) {
        Arrays.sort(nums);
        int supplement = 0;
        int gap = 0,begin = -1;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]==0){
                supplement+=1;
            }else if(begin == -1){
                begin = nums[i];
            }else if(nums[i]==begin){
                return false;
            }else{
                gap += nums[i]-begin-1;
                begin = nums[i];
            }
        }
        return gap == 0 || gap <= supplement;
    }

    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
        for(int num : nums){
            if(num == 0){
                continue;
            }
            if(set.contains(num)){
                return false;
            }
            max = Math.max(max,num);
            min = Math.min(min,num);
            set.add(num);
        }
        return max-min < 5;
    }

    public static void main(String[] args) {
        NowCode61 nowCode61 = new NowCode61();
        int[] nums = new int[]{11,8,12,8,10};
        boolean res = nowCode61.isStraight(nums);
        System.out.println("res="+res);
    }
}
