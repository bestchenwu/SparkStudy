package leetCode.medium;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/3sum/
 *
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class LeetCode15 {

    public List<List<Integer>> threeSum2(int[] nums) {
        if(nums==null||nums.length<3){
            return Arrays.asList(new ArrayList<Integer>());
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int length = nums.length;
        int j = 0;
        int k = 0;
        for(int i=0;i<length;i++){
            if(nums[i]>0){
                return result;
            }else if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            j = i +1;
            k = length-1;
            while(j<k){
                if(nums[i]+nums[k]+nums[j]==0){
                    List<Integer> list = Arrays.asList(nums[i],nums[j],nums[k]);
                    result.add(list);
                    while(j<k && nums[j]==nums[j+1]){
                        j+=1;
                    }
                    while(k>i && nums[k]==nums[k-1]){
                        k-=1;
                    }
                    j+=1;
                    k-=1;
                }else if(nums[i]+nums[k]+nums[j]>0){
                    k-=1;
                }else{
                    j+=1;
                }
            }
        }
        return result;
    }

    private void generate(int[] nums,Map<Integer,Boolean> isVisted,Stack<Integer> path,Set<List<Integer>> result){
        if(path.size()==3){
            AtomicInteger result0 = new AtomicInteger(0);
            //获取path里面的所有元素之和
            path.forEach(t->{result0.addAndGet(t);});
            if(result0.get()==0){
                List<Integer> list = new ArrayList<Integer>();
                list.addAll(path);
                list.sort((t1,t2)->Integer.compare(t1,t2));
                result.add(list);
            }
            return;
        }
        for(int i=0;i<nums.length;i++){
            int current_num = nums[i];
            Boolean flag = isVisted.get(Integer.valueOf(current_num));
            if(flag==null||!flag.booleanValue()){
                path.push(current_num);
                isVisted.put(current_num,true);
                generate(nums,isVisted,path,result);
                isVisted.put(current_num,false);
                path.pop();
            }
        }
    }

    /**
     * 这种方式只能找出所有元素都不重复的元素之和
     *
     * @param nums
     * @return
     */
    @Deprecated
    public List<List<Integer>> threeSum1(int[] nums) {
        if(nums==null||nums.length<3){
            return Arrays.asList(new ArrayList<Integer>());
        }
        Map<Integer,Boolean> isVisted = new HashMap<Integer,Boolean>();
        Stack<Integer> path = new Stack<Integer>();
        Set<List<Integer>> result = new HashSet<>();
        generate(nums,isVisted,path,result);
        return new ArrayList<>(result) ;
    }

    //超出时间限制
    @Deprecated
    public List<List<Integer>> threeSum0(int[] nums) {
        if(nums==null||nums.length<3){
            return Arrays.asList(new ArrayList<Integer>());
        }
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        int a = 0;
        int b = 0;
        int c = 0;
        for(int i = 0;i<nums.length-2;i++){
            a = nums[i];
            for(int j=i+1;j<nums.length-1;j++){
                b = nums[j];
                for(int k = j+1;k<nums.length;k++){
                    c = nums[k];
                    if(a+b+c==0){
                        List<Integer> list = Arrays.asList(a,b,c);
                        list.sort((t1,t2)->{
                            if(t1>t2){
                                return 1;
                            }else if(t1<t2){
                                return -1;
                            }else{
                                return 0;
                            }
                        });
                        set.add(list);
                    }
                }
            }

        }
        //对set进行去重
        return new ArrayList<List<Integer>>(set);
    }


    public List<List<Integer>> threeSum(int[] nums) {
        //假定i 那么从i+1 到 nums.length
        //如果i+1 = i 那么继续遍历
        List<List<Integer>> list = new ArrayList<>();
        if(nums==null || nums.length<3){
            return list;
        }
        Arrays.sort(nums);
        int i = 0;
        int length = nums.length;
        for(;i<length;i++){
            int numsI = nums[i];
            if(numsI>0){
                break;
            }
            if(i>=1 && i<length && nums[i] == nums[i-1]){
                continue;
            }
            int j = i+1;
            int k = length-1;
            while(j<k){
                if(numsI+nums[j]+nums[k] == 0){
                    list.add(Arrays.asList(numsI,nums[j],nums[k]));
                    while(j<k && nums[j] == nums[j+1]){
                        j+=1;
                    }
                    while(k>i && nums[k-1] == nums[k]){
                        k-=1;
                    }
                    j+=1;
                    k-=1;
                }else if(numsI+nums[j]+nums[k] < 0){
                    j+=1;
                }else{
                    k-=1;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        LeetCode15 leetCode15 = new LeetCode15();
        //int nums[] = new int[]{-1, 0, 1, 2, -1, -4};
        int[] nums = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        List<List<Integer>> lists = leetCode15.threeSum(nums);
        System.out.println(lists);
    }
}
