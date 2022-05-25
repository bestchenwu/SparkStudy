package nowCode.huawei;

/**
 * 描述
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在 ASCII 码范围内( 0~127 ，包括 0 和 127 )，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
 * 例如，对于字符串 abaca 而言，有 a、b、c 三种不同的字符，因此输出 3 。
 *
 * 数据范围： 1 \le n \le 500 \1≤n≤500
 * 输入描述：
 * 输入一行没有空格的字符串。
 *
 * 输出描述：
 * 输出 输入字符串 中范围在(0~127，包括0和127)字符的种数。
 *
 * 示例1
 * 输入：
 * abc
 * 输出：
 * 3
 * 示例2
 * 输入：
 * aaa
 * 输出：
 * 1
 *
 * 链接可以参考:https://www.nowcoder.com/practice/eb94f6a5b2ba49c6ac72d40b5ce95f50?tpId=37&tqId=21233&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D37%26type%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=
 *
 * @author chenwu on 2022.5.25
 */

public class HJ10 {

    public int getStringLength(String str){
        int[] countArray = new int[128];
        for(int i = 0;i<str.length();i++){
            char tmp = str.charAt(i);
            if(tmp=='\n'){
                break;
            }
            countArray[tmp-'0']+=1;
        }
        int count = 0;
        for(int i = 0;i<128;i++){
            if(countArray[i]>0){
                count+=1;
            }
        }
        return count;
    }

}
