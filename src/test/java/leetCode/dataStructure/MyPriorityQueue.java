package leetCode.dataStructure;

/**
 * 自己实现优先级队列,原型是{@link java.util.PriorityQueue}<br/>
 * 参考文章:https://www.cnblogs.com/xiaoxiongcanguan/p/10421560.html<br/>
 * 优先级队列表示一个队列，始终保持第一个元素是最大元素,内部实现的数据结构有很多种,例如链表、完全二叉树等等<br/>
 * 而完全二叉树的效率是最高的,因为删除和插入的时间复杂度都是O(NLog2N) N表示队列的大小<br/>
 * 基于完全二叉树的实现，只能保证父节点比子节点大，但不能保证左右节点之间的有序性
 *
 * @author chenwu on 2020.3.14
 */
public interface MyPriorityQueue<E> {

    /**
     * 插入新数据
     * @param newData 新数据
     * */
    void insert(E newData);

    /**
     * 获得优先级最大值（窥视） 不删数据
     * @return  当前优先级最大的数据
     * */
    E peekMax();

    /**
     * 获得并且删除当前优先级最大值
     * @return  被删除的 当前优先级最大的数据
     */
    E popMax();

    /**
     * 获得当前优先级队列 元素个数
     * @return 当前优先级队列 元素个数
     * */
    int size();

    /**
     * 是否为空
     * @return true  队列为空
     *         false 队列不为空
     * */
    boolean isEmpty();
}
