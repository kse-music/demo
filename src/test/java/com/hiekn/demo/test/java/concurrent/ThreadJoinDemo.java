package com.hiekn.demo.test.java.concurrent;

import org.junit.Test;

/**
 * 让父线程等待子线程结束之后才能继续运行
 *
 * @author: DingHao
 * @date: 2019/4/22 0:28
 */
public class ThreadJoinDemo {


    @Test
    public void join(){

        Thread join = new Thread(new Join());
        join.start();

        try {
            join.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main over");
    }

    class Join implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Join");

        }
    }
}
