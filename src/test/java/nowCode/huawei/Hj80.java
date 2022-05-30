package nowCode.huawei;

import java.util.*;

/**
 * 题目标题：
 *
 * 将两个整型数组按照升序合并，并且过滤掉重复数组元素。
 * 输出时相邻两数之间没有空格。
 *
 *
 *
 * 输入描述：
 * 输入说明，按下列顺序输入：
 * 1 输入第一个数组的个数
 * 2 输入第一个数组的数值
 * 3 输入第二个数组的个数
 * 4 输入第二个数组的数值
 *
 * 输出描述：
 * 输出合并之后的数组
 *
 * 输入：
 * 3
 * 1 2 5
 * 4
 * -1 0 3 2
 * 复制
 * 输出：
 * -101235
 */
public class Hj80 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Long> treeSet = new TreeSet<>();
        int n1 = scanner.nextInt();
        for(int i = 0;i<n1;i++){
            treeSet.add(scanner.nextLong());
        }
        int n2 = scanner.nextInt();
        for(int i = 0;i<n2;i++){
            treeSet.add(scanner.nextLong());
        }
        for(Long num : treeSet){
            System.out.print(num);
        }
    }
}
