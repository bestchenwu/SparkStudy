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
        if (haystack == null || haystack.length() == 0) {
            return 0;
        }
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return 0;
        }
        int length1 = haystack.length();
        int length2 = needle.length();
        int i = 0, j = 0;
        int k = 0;
        while (i < length1) {
            while (i<length1 && haystack.charAt(i) != needle.charAt(j)) {
                i += 1;
            }
            k = i;
            while (k<length1 && j < length2 && haystack.charAt(k) == needle.charAt(j)){
                k+=1;
                j+=1;
            };
            if (j == length2) {
                return i;
            }
            j = 0;
            i += 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        LeetCode28_20210115 leetCode28_20210115 = new LeetCode28_20210115();
        String haystack = "mississippi";
        String needle = "issip";
        int i = leetCode28_20210115.strStr(haystack, needle);
        System.out.println("i="+i);
    }
}
