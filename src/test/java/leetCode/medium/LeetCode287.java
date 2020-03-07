package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 */
public class LeetCode287 {

    //假定nums的长度是n,那么nums里面的元素都是从1到n的之间的整数
    //二分查找
    //假定中位数是2/n,那么理论上位于[1,n/2]区间的元素个数一定<=n/2
    public int findDuplicate(int[] nums) {
        int length = nums.length;
        int start = 1;
        int duplicate = findDuplicateHelp(nums,start,length);
        return duplicate;
    }


    public int findDuplicateHelp(int[] nums,int start,int end) {
        if(start == end){
            return start;
        }
        int middle = (end-start)/2;
        int count = count(nums,start,start+middle);
        int expectLength = middle+1;
        if(count<=expectLength){
            //说明重复数位于middle+1,end
            start = start+middle+1;
            return findDuplicateHelp(nums,start,end);
        }else{
            end = start+middle;
            return findDuplicateHelp(nums,start,end);
        }
    }

    private int count(int[] nums,int start,int end){
        int count = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>=start && nums[i]<=end){
                count+=1;
            }
        }
        return count;
    }

    //龟兔赛跑
    public int findDuplicate1(int[] nums) {
        // Find the intersection point of the two runners.
        int num1 = nums[0];
        int num2 = nums[0];
        do{
            num1 = nums[num1];
            num2 = nums[nums[num2]];
        }while (num1!=num2);
        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = num2;
        while(ptr1!=ptr2){
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }

    public int findDuplicate2(int[] nums) {
        int left = 1;
        int right = nums.length-1;
        while(left<right){
            int middle = (right+left)/2;
            int count = 0;
            for(int num:nums){
                if(num<=middle){
                    count+=1;
                }
            }
            if(count>middle){
                //说明重复的数字位于[left,middle]
                right = middle;
            }else{
                //说明重复的数字位于[middle+1,right]
                left = middle+1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        LeetCode287 leetCode287 = new LeetCode287();
        //int[] nums = new int[]{2,5,9,6,9,3,8,9,7,1};
        //int[] nums = new int[]{1,3,4,2,2};
        int[] nums = new int[]{3,1,3,4,2};
        int duplicate = leetCode287.findDuplicate2(nums);
        System.out.println(duplicate);
    }
}
