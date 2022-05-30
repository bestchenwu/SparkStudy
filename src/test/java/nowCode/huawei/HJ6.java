package nowCode.huawei;

import java.util.List;
import java.util.Scanner;

/**
 * 质数因子
 * <p>
 * https://www.nowcoder.com/practice/196534628ca6490ebce2e336b47b3607?tpId=37&tqId=21229&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D37%26type%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=
 * <p>
 * 描述
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
 * <p>
 * <p>
 * 数据范围： 1 \le n \le 2 \times 10^{9} + 14 \1≤n≤2×10
 * 9
 * +14
 * 输入描述：
 * 输入一个整数
 * <p>
 * 输出描述：
 * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。
 * <p>
 * 输入：
 * 180
 * 复制
 * 输出：
 * 2 2 3 3 5
 */
public class HJ6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();
        long end = (long) Math.sqrt(num);
        for (long i = 2; i <= end; i++) {
            while (num % i == 0) {
                System.out.print(i+" ");
                num = num / i;
            }
        }
        System.out.println(num == 1 ? "" : num + " ");
    }
}
