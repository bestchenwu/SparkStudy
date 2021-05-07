package leetCode.simple;

import java.util.ArrayList;
import java.util.List;

//二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
//
// 每个 LED 代表一个 0 或 1，最低位在右侧。
//
//
//
// （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0
//Unported (CC BY-SA 3.0) ）
//
// 例如，上面的二进制手表读取 “3:25”。
//
// 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
//
//
//
// 示例：
//
//
//输入: n = 1
//返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "
//0:32"]
//
//
//
// 提示：
//
//
// 输出的顺序没有要求。
// 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
// 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
// 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
//参考https://blog.csdn.net/weixin_43675940/article/details/106793212
public class LeetCode401 {

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> list = new ArrayList<>();
        for(int i = 0;i<12;i++){
            for(int j = 0;j<60;j++){
                if(count(i)+count(j)==turnedOn){
                    list.add(i+":"+(j<10?"0"+j:j));
                }
            }
        }
        return list;
    }

    /**
     * 计算数字n有几个1
     *
     * @param n
     * @return
     */
    private int count(int n){
        int count = 0;
        while(n>0){
            if(n%2==1){
                count+=1;
            }
            n = n/2;
        }
        return count;
    }
}
