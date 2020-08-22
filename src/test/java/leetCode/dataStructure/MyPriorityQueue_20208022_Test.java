package leetCode.dataStructure;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link MyPriorityQueue_20208022}的测试类
 *
 * @author chenwu on 2020.8.22
 */
public class MyPriorityQueue_20208022_Test {

    /**
     * 测试奇数数组的情况
     */
    @Test
    public void testHeap() {
        Integer[] dataArray = new Integer[]{1,3,4,2,8,5,6,9,7};
        MyPriorityQueue_20208022<Integer> myPriorityQueue_20208022 = new MyPriorityQueue_20208022<Integer>(dataArray);
        Assert.assertEquals(Integer.valueOf(9),myPriorityQueue_20208022.peekMax());
        Integer maxValue = myPriorityQueue_20208022.popMax();
        Assert.assertEquals(Integer.valueOf(9),maxValue);
        Assert.assertEquals(Integer.valueOf(8),myPriorityQueue_20208022.peekMax());
        myPriorityQueue_20208022.insert(10);
        Assert.assertEquals(Integer.valueOf(10),myPriorityQueue_20208022.peekMax());
    }

    /**
     * 测试偶数数组的情况
     */
    @Test
    public void testHeap1() {
        Integer[] dataArray = new Integer[]{1,3,4,2,8,5,6,7};
        MyPriorityQueue_20208022<Integer> myPriorityQueue_20208022 = new MyPriorityQueue_20208022<Integer>(dataArray);
        Assert.assertEquals(Integer.valueOf(8),myPriorityQueue_20208022.peekMax());
        Integer maxValue = myPriorityQueue_20208022.popMax();
        Assert.assertEquals(Integer.valueOf(8),maxValue);
        Assert.assertEquals(Integer.valueOf(7),myPriorityQueue_20208022.peekMax());
        myPriorityQueue_20208022.insert(10);
        Assert.assertEquals(Integer.valueOf(10),myPriorityQueue_20208022.peekMax());
    }
}
