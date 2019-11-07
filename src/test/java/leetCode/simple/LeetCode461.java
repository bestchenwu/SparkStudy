package leetCode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/hamming-distance/
 */
public class LeetCode461 {

    public List<Integer> changeToList(int x){
        List<Integer> list = new ArrayList<Integer>();
        int value;
        while(x!=0){
            value = x%2;
            list.add(value);
            x = x/2;
        }
        return list;
    }

    public int hammingDistance(int x, int y) {
        int result = x^y;
        List<Integer> list = changeToList(result);
        int count = 0;
        for(Integer value:list){
            if(value==1){
                count+=1;
            }
        }
        return count;
    }

    public int hammingDistance1(int x, int y) {
        int result = 0;
        while(x!=0||y!=0){
            if((x&1)!=(y&1)){
                result+=1;
            }
            x=x>>1;
            y=y>>1;
        }
        return result;
    }

    public static void main(String[] args){
        LeetCode461 leetCode461 = new LeetCode461();
        int distance = leetCode461.hammingDistance1(1, 4);
        System.out.println(distance);
    }
}
