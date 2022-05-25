package nowCode.huawei;

/**
 * https://www.nowcoder.com/practice/253986e66d114d378ae8de2e6c4577c1?tpId=37&tqId=21232&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D37%26type%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=
 *
 * 描述
 * 输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * 保证输入的整数最后一位不是 0 。
 *
 * 数据范围： 1 \le n \le 10^{8} \1≤n≤10
 * 8
 *
 * 输入描述：
 * 输入一个int型整数
 *
 * 输出描述：
 * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 *
 * 例如输入:9876673,输出:37689
 *
 * @author chenwu on 2022.5.25
 */

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
public class HJ9 {

    public static int getNewInt(String number){
        int x;
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = number.length()-1;i>=0;i--){
            x = number.charAt(i)-'0';
            if(!set.contains(x)){
                sum = sum*10+x;
                set.add(x);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int newInt = getNewInt(str);
        System.out.println("newInt="+newInt);
    }


}
