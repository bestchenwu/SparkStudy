package leetCode.dataStructure;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link MyPriorityQueueImpl}的单元测试
 */
public class MyPriorityQueueImplTest {

    private MyPriorityQueueImpl<Integer> priorityQueue;

    public MyPriorityQueueImplTest(){
        Integer[] number = new Integer[]{1,5,8,2,9,10,4};
        //对应的索引值  0  1 2 3 4 5 6
        //最后得到     10 9 8 2 5 1 4
        /**
         * 构造的二叉树如下所示:
         *            10
         *          9     8
         *        2   5  1  4
         *
         */
        priorityQueue = new MyPriorityQueueImpl<Integer>(number);
    }

    @Test
    public void testGetMax(){
        Integer max = priorityQueue.peekMax();
        Assert.assertEquals(max,Integer.valueOf(10));
    }

    @Test
    public void testAdd(){
        priorityQueue.insert(18);
        Integer max = priorityQueue.peekMax();
        Assert.assertEquals(max,Integer.valueOf(18));
    }

    @Test
    public void testPop(){
        Integer max = priorityQueue.popMax();
        Assert.assertEquals(max,Integer.valueOf(10));
        int nowMaxValue = priorityQueue.peekMax().intValue();
        Assert.assertEquals(nowMaxValue,9);
    }

    @Test
    public void testOutput(){
        Integer[] sortedArray = new Integer[7];
        priorityQueue.getSortedArray(sortedArray);
        Assert.assertArrayEquals(sortedArray,new Integer[]{10,9,8,2,5,1,4});
    }
}
