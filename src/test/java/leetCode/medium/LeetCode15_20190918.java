package leetCode.medium;

import org.junit.Test;

import java.util.*;

public class LeetCode15_20190918 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length<2){
            return list;
        }
        Arrays.sort(nums);
        int a,b,c;
        int j,k;
        for(int i = 0;i<nums.length;i++){
            a = nums[i];
            if(a>0){
                return list;
            }else if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            j = i+1;
            k = nums.length-1;
            while(j<k){
                b = nums[j];
                c = nums[k];
                if(a+b+c == 0){
                    List<Integer> list0 = Arrays.asList(a,b,c);
                    list.add(list0);
                    while(j+1<k&&nums[j+1]==b){
                        j+=1;
                    }
                    while(k-1>j && nums[k-1]==c){
                        k-=1;
                    }
                    j+=1;
                    k-=1;
                }else if(a+b+c<0){
                    j+=1;
                }else{
                    k-=1;
                }
            }
        }
        return list;
    }

    @Test
    public void testThreeSum(){
        int[] nums = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println("lists:"+lists);
    }
}
