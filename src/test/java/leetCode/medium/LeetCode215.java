package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 */
public class LeetCode215 {

    private void findLargeOnes(int[] nums,int end){
        int tmp;
        for(int i=0;i<end;i++){
            if(nums[i]>nums[end]){
                tmp = nums[end];
                nums[end] = nums[i];
                nums[i]=tmp;
            }
        }
    }

    /**
     * 使用分治算法
     *
     * @param nums
     * @param k
     * @return
     */
    @Deprecated
    public int findKthLargest0(int[] nums, int k) {
        if(nums.length==1){
            return nums[0];
        }
        int end = nums.length-1;
        for(int i=1;i<=k;i++){
            findLargeOnes(nums,end);
            end = end-1;
        }
        return nums[end+1];
    }

    /**
     * 使用堆排序算法
     *
     * @param nums
     * @param k
     * @return int 第K大的数字
     * @author chenwu on 2019.11.15
     */
    public int findKthLargest(int[] nums, int k) {
        if(nums==null||nums.length==0||k<=0){
            throw new IllegalStateException("nums or k is not valid");
        }
        if(nums.length==1){
            return nums[0];
        }
        for(int length = nums.length;length>0;length--){
            buildHeapByCylcle(nums,length);
        }
        int end = nums.length-k;
        return nums[end];
    }

    /**
     * size是这一次要堆排序的数组长度,每次排序完成后都将第一个元素与最后一个元素交换,然后数组长度-1
     *
     * @param nums
     * @param size
     * @author chenwu on 2019.11.15
     */
    private void buildHeapByCylcle(int[] nums,int size){
        int half = (int)Math.floor((float)size/2);
        for(int i=half;i>=0;i--){
            buildHeap(nums,i,size);
        }
        swap(size-1,0,nums);
    }

    private void buildHeap(int[] nums,int rootIndex,int size){
        int leftIndex = rootIndex*2+1;
        int rightIndex = rootIndex*2+2;
        int largestIndex = rootIndex;
        if(leftIndex<size){
            if(nums[leftIndex]>nums[largestIndex]){
                largestIndex=leftIndex;
            }
        }
        if(rightIndex<size){
            if(nums[rightIndex]>nums[largestIndex]){
                largestIndex = rightIndex;
            }
        }
        if(largestIndex!=rootIndex){
            swap(largestIndex,rootIndex,nums);
            buildHeap(nums,largestIndex,size);
        }
    }

    private void swap(int i,int j,int[] nums){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args) {
        LeetCode215 leetCode215 = new LeetCode215();
        int[] nums = new int[]{3,2,1,5,6,4};
        int result = leetCode215.findKthLargest(nums,2);
        System.out.println(result);
    }
}
