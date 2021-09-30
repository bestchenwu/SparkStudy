package leetCode.medium;

import org.junit.Test;

import java.util.Stack;

//给你两个版本号 version1 和 version2 ，请你比较它们。
//
// 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编
//号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
//
// 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。
//如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别
//为 0 和 1 ，0 < 1 。
//
// 返回规则如下：
//
//
// 如果 version1 > version2 返回 1，
// 如果 version1 < version2 返回 -1，
// 除此之外返回 0。
//
//
//
//
// 示例 1：
//
//
//输入：version1 = "1.01", version2 = "1.001"
//输出：0
//解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
//
//
// 示例 2：
//
//
//输入：version1 = "1.0", version2 = "1.0.0"
//输出：0
//解释：version1 没有指定下标为 2 的修订号，即视为 "0"
//
//
// 示例 3：
//
//
//输入：version1 = "0.1", version2 = "1.1"
//输出：-1
//解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 <
//version2
//
//
// 示例 4：
//
//
//输入：version1 = "1.0.1", version2 = "1"
//输出：1
//
//
// 示例 5：
//
//
//输入：version1 = "7.5.2.4", version2 = "7.5.3"
//输出：-1
//
//
//
//
// 提示：
//
//
// 1 <= version1.length, version2.length <= 500
// version1 和 version2 仅包含数字和 '.'
// version1 和 version2 都是 有效版本号
// version1 和 version2 的所有修订号都可以存储在 32 位整数 中
public class LeetCode165 {

    public int compareVersion(String version1, String version2) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        Stack<Integer> numStack1 = new Stack<>();
        Stack<Integer> numStack2 = new Stack<>();
        int i = 0,j = 0;
        int len1 = version1.length(),len2 = version2.length();
        while(i<=len1 || j<=len2){
            if(i<len1 && version1.charAt(i)>='0' && version1.charAt(i)<='9'){
                if(!stack1.isEmpty()){
                    stack1.push(version1.charAt(i));
                }
            }
            if(j<len2 && version2.charAt(j)>='0' && version2.charAt(j)<='9'){
                if(!stack2.isEmpty()){
                    stack2.push(version2.charAt(j));
                }

            }
            if(i==len1 || (i<len1 && version1.charAt(i)=='.')){
                if(stack1.isEmpty()){
                    numStack1.push(0);
                }else{
                    //计算num1 并压入到numStack1
                    int num1 = 0;
                    while(!stack1.isEmpty()){
                        num1 = num1*10+(stack1.pop()-'0');
                    }
                    numStack1.push(num1);
                }

            }
            if(j==len2 || (j<len2 && version2.charAt(j)=='.')){
                if(stack2.isEmpty()){
                    numStack2.push(0);
                }else{
                    //计算num2 并压入到numStack2
                    int num2 = 0;
                    while(!stack2.isEmpty()){
                        num2 = num2*10+(stack2.pop()-'0');
                    }
                    numStack2.push(num2);
                }
            }
            if(!numStack1.isEmpty() && !numStack2.isEmpty()){
                int num1 = numStack1.pop();
                int num2 = numStack2.pop();
                if(num1<num2){
                    return -1;
                }else if(num1>num2){
                    return 1;
                }
            }
            i++;
            j++;
        }
        if(numStack1.isEmpty() && numStack2.isEmpty()){
            return 0;
        }else if(numStack1.isEmpty()){
            for(int num : numStack2){
                if(num>0){
                    return -1;
                }
            }
            return 0;
        }else{
            for(int num : numStack1){
                if(num>0){
                    return 1;
                }
            }
            return 0;
        }
    }

    public int compareVersion1(String version1, String version2){
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        for(int i = 0;i<version1Array.length || i<version2Array.length ;i++){
            int x = 0,y = 0;
            if(i<version1Array.length){
                x = Integer.parseInt(version1Array[i]);
            }
            if(i<version2Array.length){
                y = Integer.parseInt(version2Array[i]);
            }
            if(x<y){
                return -1;
            }
            if(x>y){
                return 1;
            }
        }
        return 0;
    }

    @Test
    public void testCompareVersion(){
        String version1 = "1.1";
        String version2 = "1.10";
        int result = compareVersion(version1,version2);
        System.out.println("result ="+result);
    }
}
