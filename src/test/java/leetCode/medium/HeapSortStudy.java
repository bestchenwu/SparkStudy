package leetCode.medium;

/**
 * 堆排序
 *
 * 参考:https://www.jianshu.com/p/0d383d294a80
 */
public class HeapSortStudy {

    private void buildHeap(int[] nums,int len){
        int half = (int)Math.floor(nums.length/2);
        for(int i=0;i<=half;i++){
            heapVerify(nums,i,len);
        }
    }

    private void heapVerify(int[] nums,int rootIndex,int size){
        int leftIndex = rootIndex*2+1;
        int rightIndex = rootIndex*2+2;
        int large = rootIndex;
        if(leftIndex<size){
            if(nums[leftIndex]>nums[rootIndex]){
                large = leftIndex;
            }
        }
        if(rightIndex<size){
            if(nums[rightIndex]>nums[rootIndex]){
                large = rightIndex;
            }
        }
        if(large!=rootIndex){
            swap(large,rootIndex,nums);
            heapVerify(nums,large,size);
        }
    }

    private void swap(int i,int j,int[] nums){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args){
        HeapSortStudy heapSortStudy = new HeapSortStudy();
        int[] nums = new int[]{3,1,6,7,4,8,5};
        heapSortStudy.buildHeap(nums,nums.length);
        System.out.println(nums[nums.length-1]);
    }
}
