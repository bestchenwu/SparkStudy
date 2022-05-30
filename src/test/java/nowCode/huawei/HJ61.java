package nowCode.huawei;

import java.util.*;

/**
 * 描述
 * 把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？
 * 注意：如果有7个苹果和3个盘子，（5，1，1）和（1，5，1）被视为是同一种分法。
 *
 * 数据范围：0 \le m \le 10 \0≤m≤10 ，1 \le n \le 10 \1≤n≤10 。
 *
 * 输入：
 * 7 3
 * 复制
 * 输出：
 * 8
 */
public class HJ61 {

    private static int put(int applets,int plates){
        if(applets<0 || plates<0){
            return 0;
        }
        if(applets ==1 || plates ==1){
            return 1;
        }
        return put(applets,plates-1)+put(applets-plates,plates);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int applets = scanner.nextInt();
        int plates = scanner.nextInt();
        int sum = put(applets,plates);
        System.out.println(sum);
    }
}
