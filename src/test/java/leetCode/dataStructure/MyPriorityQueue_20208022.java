package leetCode.dataStructure;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * {@link MyPriorityQueue}的20208022重写练习版
 * 当添加一个元素的时候,总是插入到堆末尾，同时为了保持堆的有序性，需要进行堆的上滤
 * 当删除堆顶元素的时候(0号元素),则同时为了保持堆的有序性,需要进行下滤
 * <p>
 * 0
 * 1          2
 * 3  4      5   6
 * 7 8 9 10  11 12 13 14
 *
 * @author chenwu on 2020.8.22
 */
public class MyPriorityQueue_20208022<T> implements MyPriorityQueue<T> {

    private ArrayList<T> dataList;

    private int size = 0;
    private Comparator<T> comparator;

    public MyPriorityQueue_20208022() {
        dataList = new ArrayList<>();
    }

    public MyPriorityQueue_20208022(Comparator<T> comparator) {
        this();
        this.comparator = comparator;
    }

    /**
     * 根据数组构建最大堆
     *
     * @param dataArray
     */
    public MyPriorityQueue_20208022(T[] dataArray) {
        this(dataArray, null);
    }

    public MyPriorityQueue_20208022(T[] dataArray, Comparator<T> comparator) {
        this(comparator);
        if (dataArray == null || dataArray.length == 0) {
            throw new IllegalStateException("dataArray can't be empty");
        }
        for (T data : dataArray) {
            dataList.add(data);
        }
        size = dataArray.length;
        heapify();
    }

    private void heapify() {
        if (isEmpty()) {
            return;
        }
        int halfIndex = getLastNonLeafIndex();
        //从最后一个非叶子节点开始往下滤,保证当前节点是最大的
        for (int i = halfIndex; i >= 0; i--) {
            siftDown(i);
        }
    }

    @Override
    public void insert(T newData) {
        this.dataList.add(newData);
        size += 1;
        int lastIndex = getLastIndex();
        siftUp(lastIndex);
    }

    @Override
    public T peekMax() {
        return isEmpty() ? null : dataList.get(0);
    }

    @Override
    public T popMax() {
        if (isEmpty()) {
            return null;
        }
        T max = dataList.get(0);
        dataList.remove(0);
        size -= 1;
        siftDown(0);
        return max;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getLeftChildrenIndex(int i) {
        return 2 * i + 1;
    }

    private int getRightChildrenIndex(int i) {
        return 2 * (i + 1);
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    /**
     * 获取最后一个非叶子节点的下标
     *
     * @return
     */
    private int getLastNonLeafIndex() {
        return size / 2 - 1;
    }

    private int getLastIndex() {
        return size - 1;
    }

    private void swapValue(int index1, int index2) {
        T data1 = dataList.get(index1);
        T data2 = dataList.get(index2);
        dataList.set(index1, data2);
        dataList.set(index2, data1);
    }


    private int compareValue(int index1, int index2) {
        T data1 = dataList.get(index1);
        T data2 = dataList.get(index2);
        if (comparator == null) {
            if (Comparable.class.isAssignableFrom(data1.getClass())) {
                return ((Comparable) data1).compareTo((Comparable) data2);
            } else {
                throw new IllegalStateException(data1.getClass() + " is not comparable");
            }
        } else {
            return comparator.compare(data1, data2);
        }
    }

    /**
     * 从当前节点开始往上滤,如果发现父节点比自己小,那么就进行交换<br/>
     * 直到找到比当前节点大的节点
     *
     * @param index
     * @author chenwu on 2020.8.22
     */
    private void siftUp(int index) {
        while (index >= 0) {
            int parentIndx = getParentIndex(index);
            if (compareValue(parentIndx, index) < 0) {
                swapValue(index, parentIndx);
                index = parentIndx;
            } else {
                break;
            }
        }
    }

    /**
     * 从当前节点开始往下滤，如果发现子节点比当前节点大，那么就进行交换<br/>
     * 直到子节点都比当前节点小
     *
     * @param index
     * @author chenwu on 2020.8.22
     */
    private void siftDown(int index) {
        int lastNonLeafIndex = getLastNonLeafIndex();
        int lastIndex = getLastIndex();
        while (index <= lastNonLeafIndex) {
            int leftChildIndex = getLeftChildrenIndex(index);
            int rightChildIndex = getRightChildrenIndex(index);
            if (rightChildIndex <= lastIndex) {
                //如果右子节点存在
                if (compareValue(leftChildIndex, rightChildIndex) < 0) {
                    //说明右子节点更大
                    if (compareValue(index, rightChildIndex) < 0) {
                        //说明三者之间右子节点最大
                        swapValue(index, rightChildIndex);
                        index = rightChildIndex;
                    } else {
                        //说明父节点最大
                        break;
                    }
                } else {
                    //说明左子节点更大
                    if (compareValue(index, leftChildIndex) < 0) {
                        //说明三者之间左子节点最大
                        swapValue(index, leftChildIndex);
                        index = leftChildIndex;
                    } else {
                        //说明父节点最大
                        break;
                    }
                }
            } else {
                //说明右子节点不存在
                if (compareValue(index, leftChildIndex) < 0) {
                    swapValue(index, leftChildIndex);
                    index = leftChildIndex;
                } else {
                    break;
                }
            }
        }
    }
}
