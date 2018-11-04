package com.hiekn.demo.test.java.clsloader;

import java.io.*;
import java.net.URL;

/**
 * https://www.cnblogs.com/doit8791/p/5820037.html
 * 1.隐式装载， 程序在运行过程中当碰到通过new 等方式生成对象时，隐式调用类装载器加载对应的类到jvm中。
 * 2.显式装载， 通过class.forname()等方法，显式加载需要的类
 */
public class ClassLoaderTest {
    /*
    体现：
    一个应用程序总是由n多个类组成，Java程序启动时，并不是一次把所有的类全部加载后再运行，
    它总是先把保证程序运行的基础类一次性加载到jvm中，其它类等到jvm用到的时候再加载，
    这样的好处是节省了内存的开销，因为java最早就是为嵌入式系统而设计的，内存宝贵，
    这是一种可以理解的机制，而用到时再加载这也是java动态性的一种体现
     */

    /*
    1.Bootstrp loader
    Bootstrp加载器是用C++语言写的，它是在Java虚拟机启动后初始化的，它主要负责加载%JAVA_HOME%/jre/lib,
    -Xbootclasspath参数指定的路径以及%JAVA_HOME%/jre/classes中的类。

    2.ExtClassLoader
    Bootstrp loader加载ExtClassLoader,并且将ExtClassLoader的父加载器设置为Bootstrp loader.ExtClassLoader是用Java写的
    ，具体来说就是 sun.misc.Launcher$ExtClassLoader，ExtClassLoader主要加载%JAVA_HOME%/jre/lib/ext，此路径下的所有classes目录以及java.ext.dirs系统变量指定的路径中类库。

    3.AppClassLoader
    Bootstrp loader加载完ExtClassLoader后，就会加载AppClassLoader,并且将AppClassLoader的父加载器指定为 ExtClassLoader。
    AppClassLoader也是用Java写成的，它的实现类是 sun.misc.Launcher$AppClassLoader，另外我们知道ClassLoader中有个getSystemClassLoader方法,
    此方法返回的正是AppclassLoader.AppClassLoader主要负责加载classpath所指定的位置的类或者是jar文档，它也是Java程序默认的类加载器。
     */

    public static void main(String[] args) {
        /*
        1、装载：查找和导入Class文件
        2、链接：其中解析步骤是可以选择的
         （a）检查：检查载入的class文件数据的正确性
         （b）准备：给类的静态变量分配存储空间
         （c）解析：将符号引用转成直接引用
        3、初始化：对静态变量，静态代码块执行初始化工作
         */

        //Class类没有public的构造方法，Class对象是在装载类时由JVM通过调用类装载器中的defineClass()方法自动构造的

        ClassLoader c  = ClassLoaderTest.class.getClassLoader();  //获取ClassLoaderTest类的类加载器

        System.out.println(c);

        ClassLoader c1 = c.getParent();  //获取c这个类加载器的父类加载器

        System.out.println(c1);

        ClassLoader c2 = c1.getParent();//获取c1这个类加载器的父类加载器

        System.out.println(c2);

        //线程上下文类加载器
        System.out.println(Thread.currentThread().getContextClassLoader());

        String path = "D:\\IDEAProject\\util\\";
        path = "http://www.hiekn.top:1699/";
        CustomClassLoader loader = new CustomClassLoader(Thread.currentThread().getContextClassLoader() , path);
        try {
            Class<?> clazz = loader.findClass("com.hiekn.demo.test.java8.Sa");
            Object newInstance = clazz.newInstance();
            clazz.getMethod("t").invoke(newInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CustomClassLoader extends ClassLoader{

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