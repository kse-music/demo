package com.hiekn.demo.test.java.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 有返回值，get是阻塞操作（可以设置get时间，任务执行超时取消）
 *
 * @author: DingHao
 * @date: 2019/4/21 0:27
 */
public class CallableDemo {

    @Test
    public void call() {
        FutureTask<Integer> future = new FutureTask<>(() -> {
            Thread.sleep(6000);
            return new Random().nextInt();
        });
        new Thread(future).start();
        System.out.println("hello begin");
        System.out.println(future.isDone());
        try {
            System.out.println(future.get(5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("canceled: "+future.isCancelled());
            e.printStackTrace();
        }
        System.out.println(future.isDone());
        System.out.println("hello end");
    }

}
