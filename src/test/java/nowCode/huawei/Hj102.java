package nowCode.huawei;

/**
 * 描述
 * 输入一个只包含小写英文字母和数字的字符串，按照不同字符统计个数由多到少输出统计结果，如果统计的个数相同，则按照ASCII码由小到大排序输出。
 * 数据范围：字符串长度满足 1 \le len(str) \le 1000 \1≤len(str)≤1000
 *
 * 输入描述：
 * 一个只包含小写英文字母和数字的字符串。
 *
 * 输出描述：
 * 一个字符串，为不同字母出现次数的降序表示。若出现次数相同，则按ASCII码的升序输出。
 *
 * 输入：
 * aaddccdc
 * 复制
 * 输出：
 * cda
 */

import java.util.*;
import java.util.stream.Collectors;

class Pair {

    public Character item;
    public int count;

    public Pair(Character item, int count) {
        this.item = item;
        this.count = count;
    }

}

public class Hj102 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Pair> map = new HashMap<>();
        String str = scanner.nextLine();
        for (int i = 0; i < str.length(); i++) {
            Character item = str.charAt(i);
            Pair pair = map.get(item);
            if (pair == null) {
                pair = new Pair(item,1);
                map.put(item,pair);
            }else{
                pair.count+=1;
            }
        }
        Collection<Pair> allPairs = map.values();
        List<Pair> res = allPairs.stream().sorted(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.count < o2.count) {
                    return -1;
                } else if (o1.count > o2.count) {
                    return 1;
                } else {
                    return -o1.item.compareTo(o2.item);
                }
            }
        }).collect(Collectors.toList());
        Collections.reverse(res);
        for(Pair pair:res){
            System.out.print(pair.item);
        }
    }
}
