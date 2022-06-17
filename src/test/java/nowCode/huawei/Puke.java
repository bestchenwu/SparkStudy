package nowCode.huawei;

import org.junit.Test;

import java.util.*;

public class Puke {

    private String[] puke_array = new String[]{"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    public boolean check(String[] array){
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i<puke_array.length;i++){
            map.put(puke_array[i],i);
        }
        int hasZeroCount = 0;
        List<Integer> list = new ArrayList<>();
        for(String item:array){
            if(item.equals("0")){
                hasZeroCount+=1;
            }else{
                list.add(map.get(item));
            }
        }
        Collections.sort(list);
        int size = list.size();
        int i = 0;
        for(;i<size;i++){
            int gap = list.get(i)-list.get(i-1)-1;
            if(gap < 0 || gap>hasZeroCount){
                return false;
            }else{
                hasZeroCount-=gap;
            }
        }
        return true;
    }

    @Test
    public void testCheck(){
        String[] array = new String[]{"2","3","0","0","0"};
        boolean check = check(array);
        System.out.println("check="+check);
    }
}
