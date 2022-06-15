package nowCode.huawei;
/**
 * 描述
 * 密码要求:
 *
 * 1.长度超过8位
 *
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 *
 * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
 *
 * 数据范围：输入的字符串长度满足 1 \le n \le 100 \1≤n≤100
 * 输入描述：
 * 一组字符串。
 *
 * 输出描述：
 * 如果符合要求输出：OK，否则输出NG
 *
 * 输入：
 * 021Abc9000
 * 021Abc9Abc1
 * 021ABC9000
 * 021$bc9000
 * 复制
 * 输出：
 * OK
 * NG
 * NG
 * OK
 */
import java.util.*;
import java.util.regex.*;
public class Hj20_20220531 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String res = "OK";
            String str = scanner.nextLine();
            if(str.length()<=8){
                res = "NG";
            }else if(!isMatch(str)){
                res="NG";
            }else if(!isNotDuplicate(str,0,3)){
                res = "NG";
            }
            System.out.println(res);
        }
    }

    private static boolean isMatch(String str){
        int count = 0;
        Pattern pattern = Pattern.compile("[a-z]");
        if(pattern.matcher(str).find()){
            count+=1;
        }
        pattern = Pattern.compile("[A-Z]");
        if(pattern.matcher(str).find()){
            count+=1;
        }
        pattern = Pattern.compile("[0-9]");
        if(pattern.matcher(str).find()){
            count+=1;
        }
        pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pattern.matcher(str).find()){
            count+=1;
        }
        if(count>=3){
            return true;
        }else{
            return false;
        }
    }

    private static boolean isNotDuplicate(String str,int start,int end){
        if(str.length()<=end){
            return true;
        }
        if(str.substring(end).contains(str.substring(start,end))){
            return false;
        }
        return isNotDuplicate(str,start+1,end+1);
    }
}
