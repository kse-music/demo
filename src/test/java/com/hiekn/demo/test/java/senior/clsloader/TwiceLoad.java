package com.hiekn.demo.test.java.senior.clsloader;

/**
 * 静态块是否可以执行两次？
 *
 * @author: DingHao
 * @date: 2019/1/26 22:01
 */
public class TwiceLoad {

    //类加载期间执行，该类必须被使用，不单仅是加载
    static {
        System.out.println("static block init");
    }

}
