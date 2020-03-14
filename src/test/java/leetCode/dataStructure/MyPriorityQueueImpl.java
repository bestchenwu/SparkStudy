package leetCode.dataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * {@link MyPriorityQueue}的实现
 *
 * @param <E>
 * @author chenwu on 2020.3.14
 */
public class MyPriorityQueueImpl<E> implements MyPriorityQueue<E>{

    private ArrayList<E> dataList;

    private Comparator<E> comparator;

    private int queueSize = 0;

    public MyPriorityQueueImpl(E[] dataArray){
        if(dataArray==null){
            throw new IllegalStateException("dataArray can't be null");
        }
        dataList = new ArrayList<>(dataArray.length);
        for(E e:dataArray){
            dataList.add(e);
        }
        queueSize = dataArray.length;
        buildPriorityQueue();
    }

    public MyPriorityQueueImpl(E[] dataArray,Comparator<E> comparator){
        this(dataArray);
        comparator = comparator;
    }

    /**
     * 获取父节点
     *
     * @param index
     * @return
     */
    private int getParentIndex(int index){
        return index>=0?(index-1)/2:-1;
    }

    /**
     * 获取左孩子节点
     *
     * @param index
     * @return
     */
    private int getLeftChildIndex(int index){
        return 2*index+1;
    }


    private int getRightChildIndex(int index){
        return 2*(index+1);
    }

    /**
     * 获取最后一个非叶子节点下标 返回的是2元素的节点
     *    1
     *   2  3
     *  4 5
     *
     * @return
     */
    private int getLastInternalIndex(){
       return size()/2-1;
    }

    /**
     * 获取最后一个节点的索引
     *
     * @return
     */
    private int getLastIndex(){
        return size()-1;
    }

    private void swapValue(int index1,int index2){
        E data1 = dataList.get(index1);
        E data2 = dataList.get(index2);
        dataList.set(index1,data2);
        dataList.set(index2,data1);
    }

    /**
     * 比较index1与index2对应的元素
     *
     * @param index1
     * @param index2
     * @return
     */
    private int compare(int index1,int index2){
        E data1 = dataList.get(index1);
        E data2 = dataList.get(index2);
        if(comparator!=null){
            return comparator.compare(data1,data2);
        }else{
            if(Comparable.class.isAssignableFrom(data1.getClass())){
                Comparable<E> comparable1 = (Comparable<E>)data1;
                Comparable<E> comparable2 = (Comparable<E>)data2;
                return comparable1.compareTo(data2);
            }else{
                throw new IllegalStateException("E is not comparable");
            }
        }
    }

    /**
     * 上滤<br/>
     * 如果当前元素比父节点大，则进行交换，并继续向上寻找
     *
     * @param index
     */
    private void swiftUp(int index){
        while(index>=0){
            int parentIndex = getParentIndex(index);
            if(parentIndex<0){
                break;
            }
            int compareResult = compare(index,parentIndex);
            if(compareResult<=0){
                break;
            }
            swapValue(index,parentIndex);
            index = parentIndex;
        }
    }

    /**
     * 下滤<br/>
     * 从当前节点往下比较。
     * 1、先比较左边子节点和右边子节点的大小，如果左边子节点较大，则将左子节点与父节点进行比较，如果父节点较大，停止比较
     *    否则将父节点与左节点进行交换，继续向下比较
     * 2、如果右边子节点较大，则将右子节点与父节点进行比较，进行同理的操作
     * 操作上只要保证父节点比左右两个子节点都大即可，左右子节点之间的有序性不用保证
     *
     * @param index
     */
    private void swiftDown(int index){
        int lastInternalIndex = getLastInternalIndex();
        //只需要到最后一个非叶子节点即可
        while(index<=lastInternalIndex){
            int leftChildIndex = getLeftChildIndex(index);
            int rightChildIndex = getRightChildIndex(index);
            if(rightChildIndex>=queueSize){
                //说明右边节点不存在
                if(compare(index,leftChildIndex)>0){
                    break;
                }else{
                    swapValue(index,leftChildIndex);
                    index = leftChildIndex;
                }
            }else{
                if(compare(leftChildIndex,rightChildIndex)>0){
                    //说明左边孩子节点大
                    if(compare(index,leftChildIndex)>0){
                        //三者中双亲最大
                        break;
                    }else{
                        swapValue(index,leftChildIndex);
                        index = leftChildIndex;
                    }
                }else{
                    //说明右边孩子节点更大
                    if(compare(index,rightChildIndex)>0){
                        //三者中双亲最大
                        break;
                    }else{
                        swapValue(index,rightChildIndex);
                        index = rightChildIndex;
                    }
                }
            }
        }
    }

    /**
     * 插入一个新的节点
     *
     * @param newData 新数据
     */
    @Override
    public void insert(E newData) {
        //先将newData插入在dataList的末尾
        dataList.add(newData);
        queueSize++;
        //从最后一个节点上滤，直到根节点
        swiftUp(getLastIndex());
    }

    @Override
    public E peekMax() {
        if(dataList==null){
            return null;
        }
        return dataList.get(0);
    }

    @Override
    public E popMax() {
        int lastIndex = getLastIndex();
        if(lastIndex<0){
            return null;
        }
        //将顶部元素和最后一个节点交换
        swapValue(0,lastIndex);
        E top = dataList.get(lastIndex);
        //将最后一个节点删除,获得的就是最大的元素
        //E top = dataList.remove(lastIndex);
        //这里将总size-1,其实就是将最后一个节点逻辑删除
        queueSize--;
        //然后从头部节点下滤,保证有序性
        swiftDown(0);
        return top;
    }

    @Override
    public int size() {
        return queueSize;
    }

    /**
     * 重置队列大小<br/>
     * 因为队列有可能删除头部元素，而导致队列大小减小(非真实数组大小)
     *
     * @param resetSize
     */
    public void resetSize(int resetSize){
        this.queueSize = resetSize;
    }

    @Override
    public boolean isEmpty() {
        return queueSize>0;
    }

    /**
     * 构建有序优先级队列
     *
     * @author chenwu on 2020.3.14
     */
    private void buildPriorityQueue(){
        int lastInternalIndex = getLastInternalIndex();
        for(int i = lastInternalIndex;i>=0;i--){
            swiftDown(i);
        }
    }

    /**
     * 输出为数组
     *
     * @return E[]
     * @author chenwu on 2020.3.14
     */
    public void outputAsArray(E[] array){
        int index = 0;
        for(E data:dataList){
            array[index++] = data;
        }
    }

    /**
     * 完全排序 从高到低排序
     *
     * @author chenwu on 2020.3.14
     */
    public void completeSort(E[] array){
        int index = 0;
        int initQueueSize = size();
        E top = popMax();
        array[index++]=top;
        while(true){
            top = popMax();
            if(top==null){
                break;
            }
            array[index++]=top;
        }
        resetSize(initQueueSize);
        //仍然保持从高到低的有序
        Collections.reverse(dataList);
    }
}
