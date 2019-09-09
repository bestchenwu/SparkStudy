package com.spark.test;

import java.util.Stack;

public class Solution {

    class MyStack<T>{

        Object[] values = new Object[]{};

        int index = 0;

        public void push(T object){
            values[index++] = object;
        }

        public T pop(){
            return (T)values[index--];
        }

        public boolean isEmpty(){
            return index<0;
        }
    }

    class LinkedListNode{
        int val;
        LinkedListNode next = null;

        public LinkedListNode(int val,LinkedListNode next){
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "LinkedListNode[val="+val+"]";
        }
    }

    /**
     * 错误的尝试,整数的反转不应该用链表，而应该用堆栈
     *
     * @param number
     * @param numberIndex
     * @return
     */
    @Deprecated
    public LinkedListNode calculateNumber(int number,int numberIndex){
        if(number<10){
            return new LinkedListNode(number,null);
        }
        LinkedListNode node = new LinkedListNode(0,null);
        int currentNumber = number;
        while(currentNumber>=10){
            currentNumber = currentNumber/10;
            numberIndex++;
        }
        int extractNumber = number-(int)Math.pow(10,numberIndex);
        node.val = currentNumber;
        node.next = calculateNumber(extractNumber,numberIndex+1);
        return node;
    }

    public int calculateList(LinkedListNode list,int index){
        if(list==null){
            return 0;
        }
        int sum = 0;
        sum=list.val*(int)Math.pow(10,index)+calculateList(list.next,index+1);
        return sum;
    }

    /**
     * 反转链表
     * 1,2
     *
     * @param list
     * @return
     */
    public LinkedListNode reverseList(LinkedListNode list){
        if(list==null){
            return null;
        }
        LinkedListNode rootNode = new LinkedListNode(list.val,null);
        LinkedListNode node = rootNode;
        while(list.next!=null){
            list = list.next;
            node = new LinkedListNode(list.val,null);
            node.next = rootNode;
            rootNode = node;
        }
        return node;
    }

    /**
     * 反转整数  12 =>21
     * 132 =>231
     * 1425 =>5241
     * -12 => -21
     *
     * @param number
     * @return
     */
    public int reverseNumber(int number){
        //取index = 0
        //number/10  进行整除  每整除一次 index+1
        //整除完成后，把剩下的数再对10进行整除，整除的结果 *10(index+1)
        //如果是负数，则需要保留符号位
        if(number==0){
            return 0;
        }
        boolean isNegative = false;
        if(number<0){
            isNegative = true;
        }
        int nonIsNegativeNumber = Math.abs(number);
        if(nonIsNegativeNumber==Integer.MAX_VALUE||nonIsNegativeNumber==Integer.MIN_VALUE){
            return 0;
        }
        int sum = 0;
        int index = 0;
       //组装一个链表
        LinkedListNode list = calculateNumber(number,0);
        //反转链表
        LinkedListNode reverseList = reverseList(list);
        //对反转链表求和
        int reverseNumber = calculateList(reverseList,0);
        return isNegative?Math.abs(reverseNumber):reverseNumber;
    }

    public int reverseNumber1(int x){
        if(x==0){
            return 0;
        }
        boolean isNegative = false;
        if(x<0){
            isNegative = true;
        }
        int nonIsNegativeNumber = Math.abs(x);
        if(nonIsNegativeNumber==Integer.MAX_VALUE||nonIsNegativeNumber==Integer.MIN_VALUE){
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        while(nonIsNegativeNumber>0){
            stack.push(nonIsNegativeNumber%10);
            nonIsNegativeNumber=nonIsNegativeNumber/10;
        }
        int sum = 0;
        int index = 0;
        while(!stack.empty()){
            Integer pop = stack.pop();
            if(sum+pop*Math.pow(10,index)>=Integer.MAX_VALUE){
                return 0;
            }else{
                sum+=pop*Math.pow(10,index);
            }
            index = index+1;
        }
        return isNegative?Math.negateExact(sum):sum;
    }

    public int reverseNumber2(int x){
        //pop operation:
        //pop = x % 10;
        //x /= 10;

        //push operation:
        //temp = rev * 10 + pop;
        //rev = temp;
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            //因为2147483647是max_value
            //因为-2147483648是min_vlue
            //if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            //if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            if (rev > Integer.MAX_VALUE/10) return 0;
            if (rev < Integer.MIN_VALUE/10) return 0;
            if(rev*10+pop>=Integer.MAX_VALUE) return 0;
            if(rev*10+pop<=Integer.MIN_VALUE) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int reverseNumber = solution.reverseNumber2(123);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(reverseNumber);
    }
}
