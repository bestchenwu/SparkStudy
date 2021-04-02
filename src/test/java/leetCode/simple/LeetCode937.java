package leetCode.simple;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
//
// 有两种不同类型的日志：
//
//
// 字母日志：除标识符之外，所有字均由小写字母组成
// 数字日志：除标识符之外，所有字均由数字组成
//
//
// 请按下述规则将日志重新排序：
//
//
// 所有 字母日志 都排在 数字日志 之前。
// 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
// 数字日志 应该保留原来的相对顺序。
//
//
// 返回日志的最终顺序。
//
//
//
// 示例 1：
//
//
//输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 a
//rt zero"]
//输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6
//"]
//解释：
//字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
//数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
//
//
// 示例 2：
//
//
//输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
//
//输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
public class LeetCode937 {

    public String[] reorderLogFiles(String[] logs) {
        List<String> digitList = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        for (String item : logs) {
            if (item.split("\\s")[1].charAt(0) >= '0' && item.split("\\s")[1].charAt(0) <= '9') {
                digitList.add(item);
            } else {
                strList.add(item);
            }
        }
        strList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = o1.substring(o1.indexOf(" "), o1.length()).compareTo(o2.substring(o2.indexOf(" "),
                        o2.length()));
                if(result != 0){
                    return result;
                }else{
                    return o1.substring(0,o1.indexOf(" ")).compareTo(o2.substring(0,o2.indexOf(" ")));
                }

            }
        });
        strList.addAll(digitList);
        String[] result = new String[strList.size()];
        return strList.toArray(result);
    }

    @Test
    public void testReorderLogFiles(){
        //String[] items = new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String[] items = new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"};
        String[] logs = reorderLogFiles(items);
        System.out.println(Arrays.toString(logs));
    }
}
