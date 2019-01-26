package com.hiekn.demo.test.java.clsloader;

import java.io.*;
import java.net.URL;

/**
 * 什么情况需要自定义类加载器？
 * 1，隔离加载类
 * 2，修改类加载方式
 * 3，扩展加载资源
 * 4，防止源码泄露
 *
 * @author DingHao
 * @date 2018/12/15 16:20
 */
public class CustomClassLoader extends ClassLoader{

    private String path;

    public CustomClassLoader(ClassLoader parent,String path){
        super(parent);
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassData(name);
        //save to disk
        FileOutputStream fops;
        try {
            fops = new FileOutputStream("D:\\IDEAProject\\demo\\target\\test-classes\\com\\hiekn\\demo\\test\\java8\\Sa.class");
            fops.write(data);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.defineClass(name,data,0,data.length);
    }

    private byte[] loadClassData(String name){
        try {
            InputStream is;
            name = name.replace("." , File.separator);
            if(path.indexOf("http://") == 0){
                name = name.replace(File.separator , "/");
                URL url = new URL(path + "Sa" + ".class");
                is = url.openStream();
            }else{
                is = new FileInputStream(new File(path + name + ".class"));
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1){
                baos.write(b);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
