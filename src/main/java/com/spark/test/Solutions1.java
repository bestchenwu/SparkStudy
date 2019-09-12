
package com.spark.test;

public class Solutions1 {

    public int singleNumber(int[] nums) {
        if(nums==null||nums.length<2){
            if(nums.length==1){
                return nums[0];
            }else{
                return 0;
            }
        }

        int sum = nums[0];
        int temp = 1;
        for(int index=1;index<nums.length;index++){
            int sum0 = sum;
            if(temp<=0){
                sum+=nums[index];
                temp+=nums[index];
            }else{
                sum = sum-nums[index];
            }
            if(sum<=0 && index+1<nums.length){
                temp = sum;
                sum=(sum==0)?0:Math.abs(sum0)+nums[index];
            }


        }
        return Math.abs(sum);
    }

    public int maxProfit1(int[] prices) {
        if(prices==null||prices.length<=1){
            return 0;
        }
        int min = prices[0];
        int max = 0;
        for(int index=1;index<prices.length;index++){
            min = Math.min(prices[index],min);
            max = Math.max(prices[index]-min,max);
        }
        return max;
    }

    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1){
            return 0;
        }
        int maxIndex = prices.length-1;
        int minIndex = 0;
        int min = prices[minIndex];
        int max = prices[maxIndex];
        int i=0;
        int j=maxIndex;
        while(minIndex<maxIndex){
            if(i<prices.length&&prices[i]<min){
                if(i<maxIndex){
                    min = prices[i];
                    minIndex = i;
                }
            }
            if(j>=0&&prices[j]>max){
                if(j>minIndex){
                    max= prices[j];
                    maxIndex = j;
                }
            }
            i=i+1;
            j=j-1;
            if(i==prices.length||j<=0){
                break;
            }
        }

        return Math.max(max>min?max-min:0,max-prices[0]);
    }

    public static void main(String[] args){
        int[] prices = new int[]{4,1,2,1,2};

        Solutions1 solutions1 = new Solutions1();
        //System.out.println(solutions1.maxProfit1(prices));
        int a = 2&3;
        //10  11
        //10 & 11 = 10
        // 2和4  10 && 100 = 100
        //2^3
        //10 11 = 01
        //1和2
        //01 10 = 11
        //1和3
        //01 11 = 10
        //所以a^b^a = b
        //0^2 = 00 10 = 10
        //2 和 5
        //010 101 = 111
        //7和2
        //111 010 = 1 0 1
        System.out.println(2^5);
        System.out.println(a&4);
    }
}
