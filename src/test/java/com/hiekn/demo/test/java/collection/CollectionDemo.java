package com.hiekn.demo.test.java.collection;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * List Map
 *
 * @author: DingHao
 * @date: 2019/1/23 21:25
 */
public class CollectionDemo extends TestBase {

    /**
     *
     队列	有界性	锁	数据结构
     ArrayBlockingQueue	bounded(有界)	加锁	 arrayList
     LinkedBlockingQueue	optionally-bounded	加锁	 linkedList
     PriorityBlockingQueue	unbounded	加锁	 heap
     DelayQueue	unbounded	加锁 	heap
     SynchronousQueue	bounded	加锁	 无
     LinkedTransferQueue	unbounded	加锁	 heap
     LinkedBlockingDeque	unbounded	无锁	 heap
     */
    @Test
    public void queue(){
        Queue<String> queue = new LinkedBlockingQueue<>(4);
        queue.add("1");//满报异常
        queue.offer("2");//满返回false
        queue.offer("3");
        queue.offer("4");
        queue.offer("5");
        //put：会阻断直到队列变的可用

        String peek = queue.peek();//只读取不移除
        String poll = queue.poll();//读且移除队首
        //take:若队列为空，则阻断直到队列有新的数据加入

        String remove = queue.remove();//元素不存在报异常
    }

    @Test
    public void cow() {
        List<Long> list = new CopyOnWriteArrayList<>();//适用于读多写极少的场景，fail-safe机制
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20 * 10000; i++) {
            list.add(System.nanoTime());
        }

        System.out.println(System.currentTimeMillis()-start);
        List list1 = Collections.synchronizedList(new ArrayList());

        //思考为什么没有ConcurrentArrayList?
        //主要原因是：很难去开发一个通用并且没有并发瓶颈的线程安全的List。
        //像ConcurrentHashMap这样的类的真正价值（The real point / value of classes）并不是它们保证了线程安全。
        // 而在于它们在保证线程安全的同时不存在并发瓶颈。举个例子，ConcurrentHashMap采用了锁分段技术和弱一致性的Map迭代器去规避并发瓶颈。
        //所以问题在于，像“Array List”这样的数据结构，你不知道如何去规避并发的瓶颈。拿contains() 这样一个操作来说，
        // 当你进行搜索的时候如何避免锁住整个list？
        //另一方面，Queue 和Deque (基于Linked List)有并发的实现是因为他们的接口相比List的接口有更多的限制，这些限制使得实现并发成为可能。
        //CopyOnWriteArrayList是一个有趣的例子，它规避了只读操作（如get/contains）并发的瓶颈，但是它为了做到这点，
        // 在修改操作中做了很多工作和修改可见性规则。 此外，修改操作还会锁住整个List，因此这也是一个并发瓶颈。
        // 所以从理论上来说，CopyOnWriteArrayList并不算是一个通用的并发List。

    }

    @Test
    public void list() {
        // Default initial capacity：10,大于则扩容
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list = new LinkedList<>();
    }

}
