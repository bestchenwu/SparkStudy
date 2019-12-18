package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/reverse-string-ii/
 * <p>
 * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
 * <p>
 * 示例:
 * <p>
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 * 要求:
 * <p>
 * 该字符串只包含小写的英文字母。
 * 给定字符串的长度和 k 在[1, 10000]范围内。
 */
public class LeetCode541 {

    public String reverseStr(String s, int k) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        boolean isReverse = true;
        int length = s.length();
        String result = "";
        for (int index = 0; index < length; index += k) {
            int j = index;
            int end = (j + k >= length ? length : j + k);
            String tmpResult = "";
            while (j < end) {
                if (isReverse) {
                    tmpResult = s.charAt(j) + tmpResult;
                } else {
                    tmpResult = tmpResult + s.charAt(j);
                }
                j+=1;
            }
            result += tmpResult;
            isReverse = isReverse ? false : true;
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode541 leetCode541 = new LeetCode541();
        String str = "abcdefgh";
        String reverseStr = leetCode541.reverseStr(str, 3);
        System.out.println(reverseStr);
    }
}
