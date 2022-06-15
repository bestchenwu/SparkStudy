package nowCode.huawei;
/**
 * 描述
 * IPV4地址可以用一个32位无符号整数来表示，一般用点分方式来显示，点将IP地址分成4个部分，每个部分为8位，表示成一个无符号整数（因此正号不需要出现），如10.137.17.1，是我们非常熟悉的IP地址，一个IP地址串中没有空格出现（因为要表示成一个32数字）。
 *
 * 现在需要你用程序来判断IP是否合法。
 *
 * 数据范围：数据组数：1\le t\le 18\1≤t≤18
 * 进阶：时间复杂度：O(n)\O(n) ，空间复杂度：O(n)\O(n)
 *
 * 输入描述：
 * 输入一个ip地址，保证不包含空格
 *
 * 输出描述：
 * 返回判断的结果YES or NO
 *
 * 输入：
 * 255.255.255.1000
 * 复制
 * 输出：
 * NO
 */
import java.util.Scanner;
public class Hj90_20220531 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String res = "YES";
        String[] array = str.split("\\.");
        if(array.length!=4){
            res = "NO";
        }else{
            for(String item : array){
                if(item.length()==0 ||item.length()>3){
                    res = "NO";
                    break;
                }
                for(Character char0 : item.toCharArray()){
                    if(!Character.isDigit(char0)){
                        res = "NO";
                        break;
                    }
                }
                if(item.charAt(0)=='0' && item.length()!=1){
                    res = "NO";
                    break;
                }
                if(Integer.parseInt(item)>255){
                    res = "NO";
                    break;
                }
            }

        }
        System.out.println(res);
    }
}
