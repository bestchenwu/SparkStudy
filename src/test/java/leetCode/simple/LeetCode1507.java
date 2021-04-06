package leetCode.simple;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给你一个字符串 date ，它的格式为 Day Month Year ，其中：
//
//
// Day 是集合 {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"} 中的一个元素。
// Month 是集合 {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oc
//t", "Nov", "Dec"} 中的一个元素。
// Year 的范围在 [1900, 2100] 之间。
//
//
// 请你将字符串转变为 YYYY-MM-DD 的格式，其中：
//
//
// YYYY 表示 4 位的年份。
// MM 表示 2 位的月份。
// DD 表示 2 位的天数。
//
//
//
//
// 示例 1：
//
// 输入：date = "20th Oct 2052"
//输出："2052-10-20"
//
//
// 示例 2：
//
// 输入：date = "6th Jun 1933"
//输出："1933-06-06"
//
//
// 示例 3：
//
// 输入：date = "26th May 1960"
//输出："1960-05-26"
public class LeetCode1507 {

    public String reformatDate(String date) {
        String[] monthArray = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", " Nov", "Dec" };
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < monthArray.length; i++) {
            map.put(monthArray[i], (i < 9 ? ("0" + (i + 1)) : ("" + (i + 1))));
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int i = date.length() - 1, j = date.length();
        for (; i >= 0; i--) {
            if (date.charAt(i) == ' ') {
                if (count == 0) {
                    //说明是年份
                    sb.append(date.substring(i + 1, j));
                } else if (count == 1) {
                    //说明是月份
                    sb.append(map.get(date.substring(i + 1, j)));
                }
                sb.append("-");
                j = i;
                if(count == 1){
                    break;
                }
                count += 1;
            }
        }
        //说明是日期
        String dayStr = date.substring(0, j);
        StringBuilder dayNumber = new StringBuilder();
        for(int k = 0;k<dayStr.length();k++){
            if(dayStr.charAt(k)>='a' && dayStr.charAt(k)<='z'){
                break;
            }else{
                dayNumber.append(dayStr.charAt(k));
            }
        }
        if (dayNumber.length() == 1) {
            dayNumber.insert(0,'0');
        }
        sb.append(dayNumber);
        return sb.toString();
    }

    @Test
    public void testReformatDate() {
        String date = "3th Apr 2023";
        String result = reformatDate(date);
        System.out.println("result=" + result);
    }
}
