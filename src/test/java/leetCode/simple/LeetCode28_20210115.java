package leetCode.simple;

//实现 strStr() 函数。
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。
//
// 示例 1:
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
//
//
// 示例 2:
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
//
//
// 说明:
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
public class LeetCode28_20210115 {

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        int i = 0, j = 0;
        int k;
        int hayLength = haystack.length();
        int needleLength = needle.length();
        while (i < hayLength && j < needleLength) {
            if (haystack.charAt(i) != needle.charAt(j)) {
                i++;
                continue;
            }
            k = i;
            while (k < hayLength && j < needleLength && haystack.charAt(k) == needle.charAt(j)) {
                k++;
                j++;
            }
            if (j == needleLength) {
                return i;
            }
            i++;
            j = 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode28_20210115 leetCode28_20210115 = new LeetCode28_20210115();
        String haystack = "mississippi";
        String needle = "issip";
        int i = leetCode28_20210115.strStr(haystack, needle);
        System.out.println("i="+i);
    }
}
