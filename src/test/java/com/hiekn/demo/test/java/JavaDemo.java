package com.hiekn.demo.test.java;

import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.test.java.annotation.Student;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 综合性示例，各种小demo在此
 *
 * @author DingHao
 * @date 2018/12/11 15:41
 */
public class JavaDemo extends TestBase {

    /**
     * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
     * 对于任意一个对象，都能够调用它的任意一个方法和属性；
     * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
     *
     *    1、获取Class对象的三种方式
     *      1.1 Object ——> getClass();
     *      1.2 任何数据类型（包括基本数据类型）都有一个"静态”的class属性
     *      1.3 通过Class类的静态方法：forName（String  className）(常用)
     */
    @Test
    public void reflect() throws Exception{
        //第一种方式获取Class对象
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = Student.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        Class stuClass3 = null;
        try {
            stuClass3 = Class.forName("com.hiekn.demo.bean.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
            Annotation[] annotations = stuClass3.getAnnotations();
            Annotation[] declaredAnnotations = stuClass3.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                System.out.println(declaredAnnotation);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //1.加载Class对象
        Class clazz = Class.forName("com.hiekn.demo.test.java.annotation.Student");

        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }


        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用构造方法
        Object obj = con.newInstance();
        //  System.out.println("obj = " + obj);
        //  Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');



        System.out.println("************获取所有公有的字段********************");
        Field[] fieldArray = stuClass3.getFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = stuClass3.getDeclaredFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }

        //反射方法的其它使用之---通过反射越过泛型检查
        ArrayList<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");

        //  strList.add(100);
        //获取ArrayList的Class对象，反向的调用add()方法，添加数据
        Class listClass = strList.getClass(); //得到 strList 对象的字节码 对象
        //获取add()方法
        Method m = listClass.getMethod("add", Object.class);
        //调用add()方法
        m.invoke(strList, 100);

        //遍历集合
        for(Object o : strList){
            System.out.println(o);
        }

    }

    /**
     * 通过class内省获取bean属性 方法等等
     *
     * 反射可以操作各种类的属性，而内省只是通过反射来操作JavaBean的属性
     * 内省设置属性值肯定会调用setter方法，反射可以不用
     */
    @Test
    public void introspector() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        //1 获得 java Bean的描述信息
        BeanInfo info = Introspector.getBeanInfo(UserBean.class);
        //2 获得 UserBean中的属性信息
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        //3 遍历属性信息
        UserBean userBean = new UserBean();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            if(!"class".equals(name) && "name".equals(name)){
                Method method = pd.getWriteMethod();
                method.invoke(userBean,"nn");
            }
        }
        System.out.println(userBean.getName());

    }


    /**
     * 一、使用clazz.getClassLoader().getResourceAsStream(fileName)方式
     * 1.它只会解析一个配置文件
     * 2.顺序是：本体项目 -> maven引入jar中的第一个
     * <p>
     * 二、使用spring工具类，Properties globalProperties = PropertiesLoaderUtils.loadAllProperties("console.properties");
     * 1.它会解析classpath*下所有的文件
     * 2.顺序是：本体项目 -> maven中引入顺序（从上到下）
     * 3. 相同key后面的覆盖前面的
     */
    @Test
    public void loadResource() {
        Properties prop = new Properties();
        try {
            //下面这两方式,如果不是 / 开头会拼接当前class的路径查找,是则再到class路径根目录查找
//            InputStream in = getClass().getResource("demo.properties").openStream();
//            InputStream in = getClass().getResourceAsStream("demo.properties");

            //下面这两方式不管有没有 / 都是从class的根目录查找
            InputStream in = getClass().getClassLoader().getResource("demo.properties").openStream();
//            InputStream in = getClass().getClassLoader().getResourceAsStream("demo.properties");
            prop.load(in);
        } catch (Exception e) {
            logger.error("{}", e);
        }
        System.out.println(prop);

        System.out.println(getClass().getResource("").getPath());
        System.out.println(getClass().getClassLoader().getResource("").getPath());

    }

    @Test
    public void mac() throws UnknownHostException, SocketException {
        //得到IP，输出PC-201309011313/122.206.73.83

        InetAddress ia = InetAddress.getLocalHost();

        System.out.println(ia);

        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

        // 下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }

        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        System.out.println(sb.toString().toUpperCase());
    }


    /**
     * java执行js
     */
    @Test
    public void js() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(engine.getClass().getName());
        try {
            System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    // 搭建客户端
    @Test
    public void client() {
        try {
            // 1、创建客户端Socket，指定服务器地址和端口
            // Socket socket=new Socket("127.0.0.1",5200);
            Socket socket = new Socket("192.168.1.4", 5209);
            System.out.println("客户端启动成功");
            // 2、获取输出流，向服务器端发送信息
            // 向本机的52000端口发出客户请求
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // 由系统标准输入设备构造BufferedReader对象
            PrintWriter write = new PrintWriter(socket.getOutputStream());
            // 由Socket对象得到输出流，并构造PrintWriter对象
            //3、获取输入流，并读取服务器端的响应信息
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            String readline;
            readline = br.readLine(); // 从系统标准输入读入一字符串
            while (!readline.equals("end")) {
                // 若从标准输入读入的字符串为 "end"则停止循环
                write.println(readline);
                // 将从系统标准输入读入的字符串输出到Server
                write.flush();
                // 刷新输出流，使Server马上收到该字符串
                System.out.println("AsmDemo:" + readline);
                // 在系统标准输出上打印读入的字符串
                System.out.println("Server:" + in.readLine());
                // 从Server读入一字符串，并打印到标准输出上
                readline = br.readLine(); // 从系统标准输入读入一字符串
            } // 继续循环
            //4、关闭资源
            write.close(); // 关闭Socket输出流
            in.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket
        } catch (Exception e) {
            System.out.println("can not listen to:" + e);// 出错，打印出错信息
        }
    }


    //搭建服务器端
    @Test
    public void server() {
        try {
            ServerSocket server = null;
            try {
                server = new ServerSocket(5209);
                //b)指定绑定的端口，并监听此端口。
                System.out.println("服务器启动成功");
                //创建一个ServerSocket在端口5209监听客户请求
            } catch (Exception e) {
                System.out.println("没有启动监听：" + e);
                //出错，打印出错信息
            }
            Socket socket = null;
            try {
                socket = server.accept();
                //2、调用accept()方法开始监听，等待客户端的连接
                //使用accept()阻塞等待客户请求，有客户
                //请求到来则产生一个Socket对象，并继续执行
            } catch (Exception e) {
                System.out.println("Error." + e);
                //出错，打印出错信息
            }
            //3、获取输入流，并读取客户端信息
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //由Socket对象得到输入流，并构造相应的BufferedReader对象
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //由Socket对象得到输出流，并构造PrintWriter对象
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //由系统标准输入设备构造BufferedReader对象
            System.out.println("AsmDemo:" + in.readLine());
            //在标准输出上打印从客户端读入的字符串
            line = br.readLine();
            //从标准输入读入一字符串
            //4、获取输出流，响应客户端的请求
            while (!line.equals("end")) {
                //如果该字符串为 "bye"，则停止循环
                writer.println(line);
                //向客户端输出该字符串
                writer.flush();
                //刷新输出流，使Client马上收到该字符串
                System.out.println("Server:" + line);
                //在系统标准输出上打印读入的字符串
                System.out.println("AsmDemo:" + in.readLine());
                //从Client读入一字符串，并打印到标准输出上
                line = br.readLine();
                //从系统标准输入读入一字符串
            } //继续循环

            //5、关闭资源
            writer.close(); //关闭Socket输出流
            in.close(); //关闭Socket输入流
            socket.close(); //关闭Socket
            server.close(); //关闭ServerSocket
        } catch (Exception e) {//出错，打印出错信息
            System.out.println("Error." + e);
        }
    }



}
