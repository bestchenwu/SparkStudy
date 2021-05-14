package leetCode.medium;

import org.junit.Test;

import java.util.Stack;

//给定一个编码字符串 S。请你找出 解码字符串 并将其写入磁带。解码时，从编码字符串中 每次读取一个字符 ，并采取以下步骤：
////
////
//// 如果所读的字符是字母，则将该字母写在磁带上。
//// 如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写 d-1 次。
////
////
//// 现在，对于给定的编码字符串 S 和索引 K，查找并返回解码字符串中的第 K 个字母。
////
////
////
//// 示例 1：
////
//// 输入：S = "", K = 10
////输出："o"
////解释：
////解码后的字符串为 "leetleetcodeleetleetcodeleetleetcode"。
////字符串中的第 10 个字母是 "o"。
////
////
//// 示例 2：
////
//// 输入：S = "ha22", K = 5
////输出："h"
////解释：
////解码后的字符串为 "hahahaha"。第 5 个字母是 "h"。
////
////
//// 示例 3：
////
//// 输入：S = "a2345678999999999999999", K = 1
////输出："a"
////解释：
////解码后的字符串为 "a" 重复 8301530446056247680 次。第 1 个字母是 "a"。
public class LeetCode880 {

    public String decodeAtIndex(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                StringBuilder tmp = new StringBuilder();
                int j = s.charAt(i) - '0';
                for (int index = 1; index <= j; index++) {
                    tmp.append(sb);
                    if(tmp.length()>=k){
                        return tmp.substring(k-1,k);
                    }
                }
                sb = tmp;
                if (sb.length() >= k) {
                    return sb.substring(k - 1, k);
                }

            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.substring(k - 1, k);
    }

    @Test
    public void testDecodeAtIndex() {
        String str = "y959q969u3hb22odq595";
        int k = 222280369;
        String result = decodeAtIndex(str, k);
        System.out.println("result=" + result);
    }
}
