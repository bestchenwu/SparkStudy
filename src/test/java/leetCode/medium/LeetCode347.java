package leetCode.medium;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * <p>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class LeetCode347 {

    /**
     * 构造一个最小堆int[k] topK,堆顶元素(topK[0])是最小的.
     * 对于任意一个索引满足topK[i]<topK[2i] 和topK[2i+1],同时我们定义topK[2i]<topK[2i+1]
     * 如果新来了一个元素,如果它比topK[0]大,将它与topK[0]->topK[2i]->topK[2*(2i)],topK[2*(2i)+1]进行比较
     * 如果新来了一个元素,那么它比topK[0]小,则将topK[0]与后面进行依次换位
     * //粗暴点，每次来一个元素newK,都与一个List<Integer> list进行比较,list(0)表示最小元素
     * Map<Integer,Integer> map,key表示nums的下标,value表示nums的个数，同时维护一个最小堆的索引List<Integer> topK topK的值是下标
     * 如果newK<list(0)，并且list.size()=k,则不做比较
     * 如果newK>list(0),则将list和newK组成的新数组进行一次堆排序,并取前K个元素
     * 同时构造一个hashMap<Integer,元素出现的次数> map
     *
     * @param nums
     * @param k
     * @return
     */
    //todo:超出时间限制
    public List<Integer> topKFrequent0(int[] nums, int k) {
        //key为nums,value为nums的重复个数
        Map<Integer, Integer> numCountMap = new HashMap<>();
        //有序单调递增链表,里面保存是num的值,topKList的第一个元素代表的重复数最小
        LinkedList<Integer> topKList = new LinkedList<>();
        for (int num : nums) {
            Integer count = numCountMap.get(num);
            if (count != null) {
                count = count + 1;
                numCountMap.put(num, count);
            } else {
                count = 1;
                numCountMap.put(num, count);
            }
            //说明该元素是个新元素
            if (!topKList.contains(num)) {
                if (topKList.size() == k) {
                    Integer top0Count = numCountMap.get(topKList.get(0));
                    //说明当前topK已经计算完成，并且新进来的元素比topK[0]还小,那么不重复堆排序
                    if (count <= top0Count) {
                        continue;
                    }
                }
                //将当前topKList与num组成的新数组,并进行排序
                topKList = sortTopList(topKList, num, numCountMap, k);
            } else {
                //则判断num所在的链表是否需要进行交换元素
                for (int i = 0; i < topKList.size(); i++) {
                    if (topKList.get(i) == num && i + 1 < topKList.size()) {
                        int nextCount = numCountMap.get(topKList.get(i + 1));
                        if (count > nextCount) {
                            //说明两者需要进行交换
                            int tmp = num;
                            topKList.set(i, topKList.get(i + 1));
                            topKList.set(i + 1, num);
                        }
                    }
                }
            }

        }
        //需要对topKList进行一次排序,按照count进行排序
        Collections.sort(topKList, (num1, num2) -> {
            Integer numCount1 = numCountMap.get(num1);
            Integer numCount2 = numCountMap.get(num2);
            return numCount1.compareTo(numCount2);
        });
        Collections.reverse(topKList);
        return topKList;
    }

    private LinkedList<Integer> sortTopList(LinkedList<Integer> topKList, Integer num, Map<Integer, Integer> numCountMap, int k) {
        if (topKList.isEmpty()) {
            topKList.add(num);
            return topKList;
        }
        int length = topKList.size();
        int numCount = numCountMap.get(num);
        for (int i = 0; i < length; i++) {
            int currentCount = numCountMap.get(topKList.get(i));
            if (numCount > currentCount) {
                if (i == length - 1) {
                    //说明到达链表末尾了
                    topKList.addLast(num);
                } else {
                    int nextCount = numCountMap.get(topKList.get(i + 1));
                    if (numCount > nextCount) {
                        continue;
                    } else {
                        //说明currentValue<num<nextValue
                        //将num插入在i+1之前
                        topKList.add(i + 1, num);
                        break;
                    }
                }
            } else {
                //说明num<currentValue
                topKList.add(i, num);
                break;
            }
        }
        if (topKList.size() > k) {
            topKList.removeFirst();
        }
        return topKList;
    }

    /**
     * 1、构造一个hashMap numCountMap
     *     key为nums的元素值,value为num出现的次数
     * 2、构造一个优先级队列priorityQueue,队列里面保存的是nums数组的元素值,队列的排序法则按照
     *      (num1,num2)->numCountMap.get(num1).compareTo(numCountMap.get(num2))
     * 3、循环numCountMap的keySet,将key向priorityQueue里面添加,如果队列的大小>k
     *       则将第一个元素弹出
     * 4、将priorityQueue导出为list,注意priorityQueue是从小到大排序,所以在最终结果输出的时候需要反转。
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> numCountMap = new HashMap<>();
        for(int num:nums){
            numCountMap.put(num,numCountMap.getOrDefault(num,0)+1);
        }
        //由小到大排序的优先级队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((num1,num2)->numCountMap.get(num1).compareTo(numCountMap.get(num2)));
        for(Integer num : numCountMap.keySet()){
            priorityQueue.add(num);
            if(priorityQueue.size()>k){
                priorityQueue.poll();
            }
        }
        List<Integer> resultList = new ArrayList<>();
        while(!priorityQueue.isEmpty()){
            resultList.add(priorityQueue.poll());
        }
        Collections.reverse(resultList);
        return resultList;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,1,2,2,3};
//        int k = 2;
//        int[] nums = new int[]{1};
//        int k = 1;
//        int[] nums = new int[]{1,3,4,9,5,3,2,3,4,1,1,3,8};
////        int k = 3;
//        int[] nums = new int[]{4,1,-1,2,-1,2,3};
//        int k = 2;
//        int[] nums = new int[]{3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
//        int k = 10;
        int[] nums = new int[]{-1, 1, 4, -4, 3, 5, 4, -2, 3, -1};
        int k = 3;
        LeetCode347 leetCode347 = new LeetCode347();
        List<Integer> resultList = leetCode347.topKFrequent(nums, k);
        System.out.println(resultList);
    }
}
