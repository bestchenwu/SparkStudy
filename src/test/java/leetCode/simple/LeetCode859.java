package leetCode.simple;

import org.junit.Test;

//给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
//
// 交换字母的定义是取两个下标 i 和 j （下标从 0 开始），只要 i!=j 就交换 A[i] 和 A[j] 处的字符。例如，在 "abcd" 中交换下标
// 0 和下标 2 的元素可以生成 "cbad" 。
//
//
//
// 示例 1：
//
//
//输入： A = "ab", B = "ba"
//输出： true
//解释： 你可以交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 相等。
//
// 示例 2：
//
//
//输入： A = "ab", B = "ab"
//输出： false
//解释： 你只能交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 不相等。
//
//
// 示例 3:
//
//
//输入： A = "aa", B = "aa"
//输出： true
//解释： 你可以交换 A[0] = 'a' 和 A[1] = 'a' 生成 "aa"，此时 A 和 B 相等。
//
// 示例 4：
//
//
//输入： A = "aaaaaaabc", B = "aaaaaaacb"
//输出： true
//
//
// 示例 5：
//
//
//输入： A = "", B = "aa"
//输出： false
//
//
//
//
// 提示：
//
//
// 0 <= A.length <= 20000
// 0 <= B.length <= 20000
// A 和 B 仅由小写字母构成。
public class LeetCode859 {

    //从左往右遍历,如果有不相等的字符,那么记下不相等的字符，假定记成a1 b1,在往后如果找到了不相等的地方,就看下a2 == b1  ba == a1
    //并标记可交换flag为false
    public boolean buddyStrings(String a, String b) {
        char a1 = '0', b1 = '0';
        if (a.length() != b.length()) {
            return false;
        }
        //如果两个字符串相等,则找出字符串a中是否存在相同元素
        int length = a.length();
        if(a.equals(b)){
            for(int i = 0;i<length-1;i++){
                if(a.indexOf(a.charAt(i),i+1)!=-1){
                    return true;
                }
            }
            return false;
        }
        boolean isSwap = false;
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (a1 == '0') {
                    a1 = a.charAt(i);
                    b1 = b.charAt(i);
                } else {
                    if(isSwap){
                        return false;
                    }
                    if(a1 == b.charAt(i) && b1 == a.charAt(i)){
                        isSwap = true;
                    }
                }
            }
        }
        return isSwap;
    }

    @Test
    public void testBuddyStrings() {
        String a = "aaaaaaabc";
        String b = "aaaaaaacb";
        boolean result = buddyStrings(a, b);
        System.out.println("result=" + result);
    }
}
