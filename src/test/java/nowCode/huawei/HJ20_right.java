package nowCode.huawei;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class HJ20_right {

    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String str = scanner.nextLine();
            //检查长度是否超过8位
            if(str.length()<=8){
                System.out.println("NG");
                continue;
            }
            //正则校验
            if(!match(str)){
                System.out.println("NG");
                continue;
            }
            //重复校验
            if(!checkRepeat(str,0,3)){
                System.out.println("NG");
                continue;
            }

            System.out.println("OK");
        }
    }

    //正则表达式检查字符串中是否包括大小写字母.数字.其它符号,以上四种至少三种
    private static boolean match(String str){
        int count = 0;
        //定义 Pattern 对字符串进行是否包含大写字符验证
        Pattern p1 = Pattern.compile("[A-Z]");
        if(p1.matcher(str).find()){
            count++;
        }
        //定义 Pattern 对字符串进行是否包含小写字符验证
        Pattern p2 = Pattern.compile("[a-z]");
        if(p2.matcher(str).find()){
            count++;
        }

        //定义 Pattern 对字符串进行是否包含数字验证
        Pattern p3 = Pattern.compile("[0-9]");
        if(p3.matcher(str).find()){
            count++;
        }

        //定义 Pattern 对字符串进行是否包含特殊字符验证
        Pattern p4 = Pattern.compile("[^a-zA-Z0-9]");
        if(p4.matcher(str).find()){
            count++;
        }

        if(count>=3)
            return true;
        else
            return false;
    }

    //不能有长度大于2的不含公共元素的子串重复 （注：其他符号不含空格或换行）
    private static boolean checkRepeat(String str,int start,int end){
        //题解长度不超2的字符串则采用长度为3的字符，若等于str则表示当前str不包含重复的
        if(end>=str.length()){
            return true;
        }
        //头尾校验，从头部开始 依次 按照3位长度截取出字符串与剩与长度的字符串进行校验，如果剩余中包含截取的3位字符串则表示出现了重复
        if(str.substring(end).contains(str.substring(start,end)))
            return false;
        else
            return checkRepeat(str,start+1,end+1);

    }
}
