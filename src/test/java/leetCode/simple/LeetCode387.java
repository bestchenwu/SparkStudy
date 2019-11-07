package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 */
public class LeetCode387 {

    public int firstUniqChar(String s) {
        int[] hashArray = new int[26];
        char[] charArray = s.toCharArray();

        for(char item:charArray){
            int hashIndex = item-'a';
            hashArray[hashIndex]+=1;
        }

        int minIndex = -1;
        for(int i = 0;i<hashArray.length;i++){
            if(hashArray[i]==1){
                char originChar = (char)('a'+i);
                if(minIndex==-1){
                    minIndex = s.indexOf(originChar);
                }else{
                    minIndex = Math.min(minIndex,s.indexOf(originChar));
                }
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        LeetCode387 leetCode387 = new LeetCode387();
        int minIndex = leetCode387.firstUniqChar("loveleetcode");
        System.out.println(minIndex);
    }
}
