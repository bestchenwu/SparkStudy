package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * 实现string.indexOf()
 */
public class Leetcode28 {

    public int strStr(String haystack, String needle) {
        if(haystack==null||needle==null){
            return -1;
        }
        if(needle.length()==0){
            return 0;
        }
        if(haystack.length()<needle.length()){
            return -1;
        }
        char[] haystackArray = haystack.toCharArray();
        char[] needCharArray = needle.toCharArray();
        char firstChar = needCharArray[0];

        for(int i=0;i<haystackArray.length;i++){
            if(haystackArray[i]==firstChar){
                if(haystackArray.length-i<needCharArray.length){
                    return -1;
                }
                int j= 1;
                int i1 = i+1;
                while(j<needCharArray.length&&haystackArray[i1]==needCharArray[j]){
                    i1=i1+1;
                    j=j+1;
                }
                if(j==needCharArray.length){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Leetcode28 leetcode28 = new Leetcode28();
        int result = leetcode28.strStr("mississippi","sippia");
        System.out.println(result);
    }
}
