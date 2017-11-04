package com.hiekn.demo.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadTest {  
    public static void main(String[] args) throws InterruptedException   {  
//        Runnable tr = new TestRunnable();  
//        Thread thread = new Thread(tr);  
//        thread.setDaemon(true); //设置守护线程  
//        thread.start(); //开始执行分进程  
    	
        System.out.println(Thread.currentThread().getName()+" 是守护线程？"+Thread.currentThread().isDaemon());
        
        Thread t1 = new Thread(new Runnable() {//在Daemon线程中产生的新线程也是Daemon的
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				  Thread t2 = new Thread(new Runnable() {//在Daemon线程中产生的新线程也是Daemon的
						@Override
						public void run() {
							try {
								Thread.sleep(6000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(Thread.currentThread().getName()+" 是守护线程？孙"+Thread.currentThread().isDaemon());
						}
					});
			        t2.start();
			        Thread.currentThread().stop();
			        Thread.yield();
			        System.out.println(Thread.currentThread().getName()+" 是守护线程？子"+Thread.currentThread().isDaemon());
			}
		});
        t1.setDaemon(true);
        t1.start();
        Thread.sleep(13000);
        System.out.println(Thread.currentThread().getName()+"结束");
    }  
    
}  
class TestRunnable implements Runnable{  
	public void run(){  
		try{  
			Thread.sleep(1000);//守护线程阻塞1秒后运行  
			File f=new File("daemon.txt");  
			FileOutputStream os=new FileOutputStream(f,true);  
			os.write("daemon".getBytes());  
			os.close();
		}  catch(IOException e1){  
			e1.printStackTrace();  
		}  catch(InterruptedException e2){  
			e2.printStackTrace();  
		}  
		System.out.println(Thread.currentThread().getName()+" 是守护线程？"+Thread.currentThread().isDaemon());
	}  
} 
