package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 */
public class LeetCode11 {


    /**
     * 暴力解法 时间复杂度是o(n2)
     *
     * @param height
     * @return
     */
    @Deprecated
    public int maxArea0(int[] height) {
        if(height.length==2){
            return 1*Math.min(height[0],height[1]);
        }
        int maxAreaResult =  Integer.MIN_VALUE;
        int currentArea;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                currentArea = (j-i)*(Math.min(height[i],height[j]));
                maxAreaResult = Math.max(maxAreaResult,currentArea);
            }
        }
        return maxAreaResult;
    }

    public int maxArea(int[] height) {
        if(height.length==2){
            return 1*Math.min(height[0],height[1]);
        }
        int i = 0;
        int j = height.length-1;
        int maxAreaResult = 0;
        int heightI,heightJ,currentArea;

        while(j>i){
            heightI = height[i];
            heightJ = height[j];
            currentArea = (j-i)*Math.min(heightI,heightJ);
            maxAreaResult = Math.max(currentArea,maxAreaResult);
            if(heightI>heightJ){
                j=j-1;
            }else{
                i=i+1;
            }
        }
        return maxAreaResult;
    }

    public static void main(String[] args) {
        LeetCode11 leetCode11 = new LeetCode11();
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        int maxArea = leetCode11.maxArea(height);
        System.out.println(maxArea);

    }
}
