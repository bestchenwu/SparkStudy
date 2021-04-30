package leetCode.simple;

import org.junit.Test;

//字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没
////有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
////
//// 示例1:
////
////
//// 输入："aabcccccaaa"
//// 输出："a2b1c5a3"
////
////
//// 示例2:
////
////
//// 输入："abbccd"
//// 输出："abbccd"
//// 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
////
////
//// 提示：
////
////
//// 字符串长度在[0, 50000]范围内。
public class LeetCode0106 {

    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        for (; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(j)) {
                continue;
            }
            sb.append(S.charAt(j));
            sb.append(i-j);
            j = i;
        }
        if (i != j) {
            sb.append(S.charAt(j));
            sb.append(i-j);
        }
        return sb.length() > S.length() ? S : sb.toString();
    }

    @Test
    public void testCompressString() {
        String str = "abbccd";
        String result = compressString(str);
        System.out.println("result=" + result);
    }
}