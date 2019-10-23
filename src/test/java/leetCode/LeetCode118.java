package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pascals-triangle/submissions/
 *
 * 杨辉三角
 */
public class LeetCode118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> last = new ArrayList<Integer>(1);
        last.add(1);
        list.add(last);
        for(int i=2;i<=numRows;i++){
            List<Integer> newList = generateList(last);
            list.add(newList);
            Integer[] ints = new Integer[newList.size()];
            newList.toArray(ints);
            last = Arrays.asList(ints);
        }
        return list;
    }

    public List<Integer> generateList(List<Integer> list){
        List<Integer> result = new ArrayList<Integer>();
        result.add(list.get(0));
        for(int i=1;i<=list.size()-1;i++){
            result.add(list.get(i-1)+list.get(i));
        }
        result.add(list.get(list.size()-1));
        return result;

    }

    public static void main(String[] args) {
        LeetCode118 leetCode118 = new LeetCode118();
        List<List<Integer>> list = leetCode118.generate(3);
        System.out.println(list);
    }
}
