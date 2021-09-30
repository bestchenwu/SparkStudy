package nowCode.medium;
//字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
//
//
//
// 示例 1:
//
// 输入:
//first = "pale"
//second = "ple"
//输出: True
//
//
//
// 示例 2:
//
// 输入:
//first = "pales"
//second = "pal"
//输出: False

import org.junit.Test;

public class LeetCode0105 {

    private boolean checkIsEqual(String s1, String s2, int start1, int start2) {
        if (s1.length() - start1 != s2.length() - start2) {
            return false;
        }
        while(start1<s1.length() && start2<s2.length()){
            if(s1.charAt(start1)!=s2.charAt(start2)){
                return false;
            }
            start1++;
            start2++;
        }
        return true;
    }

    public boolean oneEditAway(String first, String second) {
        if (first == null && second == null) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        int i = 0, l1 = first.length();
        int j = 0, l2 = second.length();
        if (Math.abs(l1 - l2) > 1) {
            return false;
        }
        while (i < l1 && j < l2) {
            if (first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            } else {
                //比较i+1 和 j
                boolean flag1 = checkIsEqual(first, second, i + 1, j);
                if (flag1) {
                    return true;
                }
                //比较i和j+1
                flag1 = checkIsEqual(first, second, i, j + 1);
                if (flag1) {
                    return true;
                }
                //比较i+1和j+1
                flag1 = checkIsEqual(first, second, i + 1, j + 1);
                return flag1;
            }
        }
        return true;
    }

    @Test
    public void testOneEditAway() {
        String first = "islander";
        String second = "slander";
        boolean result = oneEditAway(first, second);
        System.out.println("result=" + result);
    }
}
