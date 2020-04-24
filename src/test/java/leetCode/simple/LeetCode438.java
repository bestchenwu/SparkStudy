package leetCode.simple;

import leetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 * 438. 找到字符串中所有字母异位词
 * <p>
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 * <p>
 * 输入:
 * s: "abab" p: "ab"
 * <p>
 * 输出:
 * [0, 1, 2]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * @author chenwu on 2019.10.17
 */
public class LeetCode438 {

    private String getHashCode(int[] hashArray) {
        StringBuilder hashCode = new StringBuilder();
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] > 0) {
                hashCode.append(i + 1);
                for (int k = 1; k <= hashArray[i]; k++) {
                    hashCode.append("#");
                }
            }
        }
        return hashCode.toString();
    }

    private int[] getHashCodeArray(String s1) {
        int[] s1HashArray = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            int index = s1.charAt(i) - 'a';
            s1HashArray[index] += 1;
        }
        return s1HashArray;
    }

    /**
     * 超出时间限制
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams0(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return list;
        }
        int[] phashCodeArray = getHashCodeArray(p);
        String phashCode = getHashCode(phashCodeArray);
        int plength = p.length();
        int slength = s.length();
        for (int i = 0; i < slength; i++) {
            if (i + plength > slength) {
                continue;
            }
            String subString = s.substring(i, i + plength);
            int[] hashCodeArray = getHashCodeArray(subString);
            String hashCode = getHashCode(hashCodeArray);
            if (hashCode.equals(phashCode)) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 双指针解决滑动窗口问题
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return list;
        }
        int left = 0;
        int right = 0;
        int[] window = new int[26];
        int[] pwindow = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pwindow[p.charAt(i) - 'a'] += 1;
        }
        while (right < s.length()) {
            int index = s.charAt(right) - 'a';
            window[index] += 1;
            right += 1;
            while (window[index] > pwindow[index]) {
                int leftIndex = s.charAt(left) - 'a';
                window[leftIndex] -= 1;
                left += 1;
            }
            if (right - left == p.length()) {
                list.add(left);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        LeetCode438 leetCode438 = new LeetCode438();
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> anagrams = leetCode438.findAnagrams(s, p);
        System.out.println(anagrams);
    }
}
