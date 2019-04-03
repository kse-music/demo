package com.hiekn.demo.test.java.senior.concurrent;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 无论从性能还是安全性上考虑，我们尽量使用并发包中提供的信号同步类，避免使用对象的wait()和notify()方式来进行同步。
 *
 * @author: DingHao
 * @date: 2019/1/26 22:51
 */
public class ConcurrentDemo extends TestBase {

    public int simpleMethod(){
        int x = 13;
        int y = 14;
        int z = x + y;
        return z;
    }

    public void testSynchronized(){

        synchronized (this){

        }

    }

    @Test
    public void semaphore(){

        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 5; i++) {
            new SecurityCheckThread(i,semaphore).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countDown() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(3);
        Thread thread1 = new TranslateThread("1st content",count);
        Thread thread2 = new TranslateThread("2nd content",count);
        Thread thread3 = new TranslateThread("3rd content",count);
        thread1.start();
        thread2.start();
        thread3.start();

        count.await(10,TimeUnit.SECONDS);
        System.out.println("所有线程执行完成");
    }

    class TranslateThread extends Thread{
        private String content;
        private final CountDownLatch count;
        TranslateThread(String content,CountDownLatch count){
            this.content = content;
            this.count = count;
        }

        @Override
        public void run() {
            if(Math.random() > 0.5){
                throw new RuntimeException("原文存在非法字符");
            }
            System.out.println(content + "的翻译已经完成，译文是...");
            count.countDown();

        }
    }


    class SecurityCheckThread extends Thread{
        private int seq;
        private final Semaphore semaphore;
        SecurityCheckThread(int seq,Semaphore semaphore){
            this.seq = seq;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("No." + seq+ "乘客，正在检验中");

                if(seq % 2 == 0){
                    Thread.sleep(1000);
                    System.out.println("No." + seq+ "乘客，身份可疑，不能出国！");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
                System.out.println("No." + seq+ "乘客已完成服务。");
            }

        }
    }

}
