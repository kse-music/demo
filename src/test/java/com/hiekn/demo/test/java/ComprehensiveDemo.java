package com.hiekn.demo.test.java;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.test.java.annotation.Student;
import com.hiekn.demo.util.HttpRequest;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;



/**
 * 综合性示例，各种小demo在此
 *
 * @author DingHao
 * @date 2018/12/11 15:41
 */
public class ComprehensiveDemo extends TestBase {

    private static final int[] arr = {77, 99, 44, 55, 22, 88, 11, 0, 66, 33};

    private Object instance;
    private static final int _1MB = 1024 *1024;
    private byte[] bigSize = new byte[2 * _1MB];

    @Before
    public void init() {
    }

    @After
    public void out() {
        System.out.println(Arrays.toString(arr));
    }


    /**
     * gc
     */
    @Test
    public void gc(){
        ComprehensiveDemo objA = new ComprehensiveDemo();
        ComprehensiveDemo objB = new ComprehensiveDemo();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();
    }


    /**
     *强引用
     * 软引用
     * 弱引用
     * 平台引用
     */
    @Test
    public void javaReference(){

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
     * 找到最小数就交换位置,  稳定
     */
    @Test
    public void bubbleSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    /**
     * 找到最小数的小标，最后交换一次位置,   不稳定
     */
    @Test
    public void selectSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }

    }


    /**
     * 稳定
     */
    @Test
    public void insertSort() {
        int i, j, insertNote;// 要插入的数据
        for (i = 1; i < arr.length; i++) {// 从数组的第二个元素开始循环将数组中的元素插入
            insertNote = arr[i];// 设置数组中的第2个元素为第一次循环要插入的数据
            j = i - 1;
            while (j >= 0 && insertNote < arr[j]) {
                arr[j + 1] = arr[j];// 如果要插入的元素小于第j个元素,就将第j个元素向后移动
                j--;
            }
            arr[j + 1] = insertNote;// 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
        }
    }

    /**
     * 不稳定
     */
    @Test
    public void quickSort() {
        quick(arr, 0, arr.length - 1);
    }

    private void quick(int[] arr, int low, int high) {// 传入low=0，high=array.length-1;
        int pivot, p_pos, i, t;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            pivot = arr[p_pos];
            for (i = low + 1; i <= high; i++) {
                if (arr[i] < pivot) {
                    p_pos++;
                    swap(arr, i, p_pos);
                }
            }
            swap(arr, low, p_pos);
            // 分而治之
            quick(arr, low, p_pos - 1);// 排序左半部分
            quick(arr, p_pos + 1, high);// 排序右半部分
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
     * 对于任意一个对象，都能够调用它的任意一个方法和属性；
     * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
     *
     *    1、获取Class对象的三种方式
     *      1.1 Object ——> getClass();
     *      1.2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
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
     * @throws IntrospectionException
     */
    @Test
    public void introspector() throws IntrospectionException {
        //1 获得 java Bean的描述信息
        BeanInfo info = Introspector.getBeanInfo(UserBean.class);
        //2 获得 UserBean中的属性信息
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        //3 遍历属性信息
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getName());
        }
    }


    /**
     * java执行js
     */
    @Test
    public void js(){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println( engine.getClass().getName() );
        try {
            System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取系统属性和系统环境
     */
    @Test
    public void getSystemProperties(){
        Properties props = System.getProperties();
        props.list(System.out);

        System.getenv().forEach((k,v) -> System.out.println(k + " = " + v));
    }


    /**
     * asm 操作字节码
     */
    @Test
    public void asm(){
        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/hiekn/asm/HelloWorld";
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, className, null,
                "java/lang/Object", null);

        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                "()V", null, null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>",
                "V()");
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();

        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello",
                "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();

        classWriter.visitEnd();

        byte[] code = classWriter.toByteArray();

        File file = new File("D:\\HelloWorld.class");
        try(FileOutputStream output = new FileOutputStream(file)){
            output.write(code);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 各种第三方http工具试用
     */
    @Test
    public void httpInvoke() throws IOException {
        //1、HttpRequest基于原生URLConnection封装
        String s = HttpRequest.sendGet("http://www.hiboot.cn/");
        System.out.println(s);

        //2、okHttp
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://raw.github.com/square/okhttp/master/README.md")
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

    }


    /**
     * Swagger2Markup 生成adhoc格式文档，可输出为pdf或者html
     * @throws MalformedURLException
     */
    @Test
    public void adhoc() throws MalformedURLException {

        /*Swagger2MarkupConverter.from("json" + "/swagger.json")
                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
                .build()
                .intoFolder("doc");*/
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withPathsGroupedBy(GroupBy.TAGS)
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withBasePathPrefix()
                .withGeneratedExamples()
                .build();

        Swagger2MarkupConverter.from(new URL("http://192.168.1.119:8888/api/swagger.json"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("src/docs/generated"));
    }

    /**
     * 利用hutool封装的加密工具
     */
    @Test
    public void crypto() {
        String content = "test中文";

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();

        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.DESede, key);

        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);

        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);

    }

    //	private static WebDriver driver;
//
//	private static final String URL = "http://news.sohu.com/20170320/n483936679.shtml";
////	private static final String URL = "https://www.crunchbase.com/organization/microsoft/people";
//
//	public static void main(String[] args) throws Exception {
//      String URL = "http://news.sohu.com/20170320/n483936679.shtml";
//      String URL = "https://www.crunchbase.com/organization/microsoft/people";
//		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
//
//		WebDriver driver = new FirefoxDriver();
////		driver.manage().window().maximize();
//		driver.get(URL);
//
//		Thread.sleep(2000);
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		Thread.sleep(1000);
////		List<WebElement> eles = driver.findElements(By.cssSelector(".section-list li .info-block h4 a"));
//		System.out.println("find");
//		List<WebElement> eles = driver.findElements(By.cssSelector("div.content-box h1"));
//		System.out.println(eles.size()+"条数据");
//		for (WebElement e : eles) {
//			System.out.println(e.getText());
//		}
//
//		System.out.println("finish");
//	}


}
