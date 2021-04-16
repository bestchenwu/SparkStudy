package leetCode.simple;

//字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
//
// 示例1:
//
//  输入：s1 = "waterbottle", s2 = "erbottlewat"
// 输出：True
//
//
// 示例2:
//
//  输入：s1 = "aa", s2 = "aba"
// 输出：False
//
//
//
//
//
// 提示：
//
//
// 字符串长度在[0, 100000]范围内。
//
//
// 说明:
//
//
// 你能只调用一次检查子串的方法吗？
//
// Related Topics 字符串
// 👍 66 👎 0

import org.junit.Test;

public class LeetCode0109 {

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            String s1Before = s1.substring(0, (i + 1 > s1.length() ? s1.length() : (i + 1)));
            int s2LastIndex = s2.lastIndexOf(s1Before);
            if (s2LastIndex != -1) {
                String s1After = s1.substring((i + 1 > s1.length() ? s1.length() : (i + 1)));
                String s2Before = s2.substring(0, s2LastIndex);
                if (s1After.equals(s2Before)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFlipedString2(String s1, String s2) {
        // 长度不相等，肯定不符合要求
        if (s1.length() != s2.length()) {
            return false;
        }

        // 长度相等时，若s2是s1旋转而成的，那么把s2和自身拼接一次，s1就会出现在其中
        // "erbottlewat" + "erbottlewat" = erbottle waterbottle wat
        // 如果s2不是s1旋转而成的，那么那么把s2和自身拼接一次，s1就肯定不会出现在其中
        return (s2 + s2).indexOf(s1) != -1;
    }

    @Test
    public void testIsFlipedString() {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        boolean flipedString = isFlipedString(s1, s2);
        System.out.println("flipedString=" + flipedString);
    }
}
