package leetCode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/task-scheduler/
 *
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 *  
 *
 * 示例 ：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 *  
 *
 * 提示：
 *
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 *
 */
public class LeetCode621 {

    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for(char task:tasks){
            map[task-'A']++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int value:map){
            if(value>0){
                queue.add(value);
            }
        }
        int time = 0;
        while(!queue.isEmpty()){
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            while(i<=n){
                if(!queue.isEmpty()){
                    if(queue.peek()>1){
                        temp.add(queue.poll()-1);
                    }else{
                        queue.poll();
                    }
                }
                time++;
                if(queue.isEmpty()&&temp.isEmpty()){
                    break;
                }
                i++;
            }
            for(Integer value:temp){
                queue.add(value);
            }
        }
        return time;
    }

    public static void main(String[] args) {
        LeetCode621 leetCode621 = new LeetCode621();
        //char[] tasks = new char[]{'A','B','C','B','D'};
        char[] tasks = new char[]{'A','A','A','B','B','B'};
        int n = 2;
        int result = leetCode621.leastInterval(tasks, n);
        System.out.println("result="+result);
    }
}
