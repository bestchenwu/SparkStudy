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
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。
public class LeetCode28_20210129 {

    private boolean helpSubStr(int startIndex, String haystack, String needle) {
        int needleIndex = 0;
        while (startIndex < haystack.length() && needleIndex < needle.length()) {
            if (haystack.charAt(startIndex) != needle.charAt(needleIndex)) {
                return false;
            }
            startIndex++;
            needleIndex++;
        }
        return needleIndex == needle.length();
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        int startIndex = 0;
        while (startIndex < haystack.length()) {
            if (helpSubStr(startIndex, haystack, needle)) {
                return startIndex;
            }
            startIndex++;
        }
        return -1;
    }

    /**
     * kmp算法
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr0(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        int i = 0, j = 0;
        int len1 = haystack.length();
        int len2 = needle.length();
        while(i<len1 && j<len2){
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
            }else{
                i = i-j+1;
                j = 0;
            }
        }
        if(j == len2){
            return i-j;
        }else{
            return -1;
        }
    }


    public static void main(String[] args) {
        String s1 = "aaaaa";
        String s2 = "aab";
        LeetCode28_20210129 leetCode28_20210129 = new LeetCode28_20210129();
        int result = leetCode28_20210129.strStr(s1, s2);
        System.out.println("result=" + result);
    }
}
