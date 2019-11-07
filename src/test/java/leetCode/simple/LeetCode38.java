package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/count-and-say/solution/xun-huan-huo-di-gui-jie-jue-by-mjclown/
 *
 * 报数
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 */
public class LeetCode38 {

    public String countAndSay(int n) {
        if(n==1){
            return "1";
        }else{
            return countAndSayLine(countAndSay(n-1));
        }

    }

    public String countAndSayLine(String str){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int i = 0;
        while(i<str.length()){
            if(i==0||str.charAt(i)==str.charAt(i-1)){
                count++;
            }else{
                sb.append(count).append(str.charAt(i-1));
                count = 1;
            }
            if(i==str.length()-1){
                sb.append(count).append(str.charAt(i));
            }
            i+=1;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode38 leetCode38 = new LeetCode38();
       System.out.println(leetCode38.countAndSay(5)) ;
    }
}
