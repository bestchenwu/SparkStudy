package nowCode.medium;

import org.junit.Test;

//二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印
//“ERROR”。
//
// 示例1:
//
//
// 输入：0.625
// 输出："0.101"
//
//
// 示例2:
//
//
// 输入：0.1
// 输出："ERROR"
// 提示：0.1无法被二进制准确表示
//
//
// 提示：
//
//
// 32位包括输出中的"0."这两位。
public class LeetCode0502 {

    //任何小数 num = a1*2-1  + a2*2-2 + ...
    public String printBin(double num) {
        StringBuffer sb = new StringBuffer("0.");
        double factor = 1;
        int i = 0;
        while (num != 0) {
            i++;
            factor*=0.5;
            int n = (int) (num / factor);
            num = num - n * factor;
            sb.append(n);
            if (i > 32) {
                return "ERROR";
            }
        }
        return sb.toString();
    }

    @Test
    public void testPrintBin() {
        double num = 0.625;
        String res = printBin(num);
        System.out.println("res=" + res);
    }

}
