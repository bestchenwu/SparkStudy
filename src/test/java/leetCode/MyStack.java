package leetCode;

public class MyStack {

    int capacity = 5;
    int[] values = new int[capacity];
    int index = -1;
    /** initialize your data structure here. */
    public MyStack() {
        super();
    }

    public void push(int x) {
        if(index==capacity-1){
            //进行扩容
            capacity = capacity*2;
            int[] newValues = new int[capacity];
            for(int i = 0;i<values.length;i++){
                newValues[i]=values[i];
            }
            values = newValues;
        }
        values[++index]=x;

    }

    public void pop() {
        if(index>=0){
            values[index]=0;
            index-=1;
        }
    }

    public int top() {
        if(index>=0){
            return values[index];
        }else{
            return 0;
        }
    }

    public int getMin() {
        if(index<0){
            return 0;
        }
        int min = values[0];
        for(int i=0;i<=index;i++){
            min = Math.min(min,values[i]);
        }
        return min;
    }
}
