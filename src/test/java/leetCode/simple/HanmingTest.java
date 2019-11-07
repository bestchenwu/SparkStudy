package leetCode.simple;

import java.util.ArrayList;
import java.util.List;

public class HanmingTest {

    public static void main(String[] args){
        int x = 4;
        List<Integer> list = new ArrayList<Integer>();
        while(x!=0){
            list.add(x%2);
            x = x/2;
        }
        System.out.println(list);
    }

}
