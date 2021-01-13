package nowCode.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只
//能放在更大的盘子上面)。移动圆盘时受到以下限制:
//(1) 每次只能移动一个盘子;
//(2) 盘子只能从柱子顶端滑出移到下一根柱子;
//(3) 盘子只能叠在比它大的盘子上。
//
// 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
//
// 你需要原地修改栈。
//
// 示例1:
//
//  输入：A = [2, 1, 0], B = [], C = []
// 输出：C = [2, 1, 0]
//
//
// 示例2:
//
//  输入：A = [1, 0], B = [], C = []
// 输出：C = [1, 0]
public class LeetCode0806 {

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = A.size();
        helpHanota(n,A,B,C);
    }

    private void helpHanota(int n,List<Integer> A, List<Integer> B, List<Integer> C){
        if(n<=0){
            return;
        }
        //将0到n-1的盘子从A经由C挪到B
        helpHanota(n-1,A,C,B);
        //将A的编号为n的盘子挪到C
        System.out.println("before A:"+A);
        System.out.println("before B:"+B);
        System.out.println("before C:"+C);
        C.add(A.remove(A.size()-1));
        System.out.println("after A:"+A);
        System.out.println("after B:"+B);
        System.out.println("after C:"+C);
        //将0到n-1的盘子从B经由A挪到C
        helpHanota(n-1,B,A,C);
    }

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        A.add(2);
        A.add(1);
        A.add(0);
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        LeetCode0806 leetCode0806 = new LeetCode0806();
        leetCode0806.hanota(A,B,C);
    }
}
