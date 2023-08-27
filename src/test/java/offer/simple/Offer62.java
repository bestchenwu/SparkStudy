package offer.simple;

/**
 * https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 *
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * 示例 1：
 *
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 *
 * 输入: n = 10, m = 17
 * 输出: 2
 *
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 */
public class Offer62 {


    public int lastRemaining2(int n, int m) {
        return helpDelete(n,m);
    }

    public int helpDelete(int n,int m){
        if(n==1){
            return 0;
        }
        int x = helpDelete(n-1,m);
        return (m+x)%n;
    }

    public int lastRemaining(int n, int m) {
        boolean[] flags = new boolean[n];
        int step = m;
        int moveCount = n;
        int start = 0;
        helpDelete(flags,step,start,n,moveCount);
        for(int i = 0;i<n;i++){
            if(!flags[i]){
                return i;
            }
        }
        return -1;
    }

    private void helpDelete(boolean[] flags,int step,int start,int n,int moveCount){
        int trueStep = 1,last = start;
        while(trueStep<=step){
            if(!flags[start]){
                trueStep+=1;
            }
            last = start;
            start = (start+1)%n;
        }
        flags[last] = true;
        moveCount-=1;
        if(moveCount==1){
            return;
        }else{
            helpDelete(flags,step,start,n,moveCount);
        }
    }

    public static void main(String[] args) {
        Offer62 offer62 = new Offer62();
        int n = 70866,m = 116922;
        int last = offer62.lastRemaining(n, m);
        System.out.println(last);
    }
}
