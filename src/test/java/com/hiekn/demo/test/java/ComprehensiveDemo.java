package com.hiekn.demo.test.java;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.test.frame.crawler.CrawlerDemo;
import com.hiekn.demo.test.java.annotation.BeanDefine;
import com.hiekn.demo.test.java.annotation.Student;
import com.hiekn.demo.test.java.annotation.TestAnnotation;
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
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.lang.reflect.Proxy;
import java.net.*;
import java.nio.file.Paths;
import java.util.*;

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


    @Before
    public void init() {}

    /**
     *
     * 一、使用clazz.getClassLoader().getResourceAsStream(fileName)方式
     * 1.它只会解析一个配置文件
     * 2.顺序是：本体项目 -> maven引入jar中的第一个
     *
     * 二、使用spring工具类，Properties globalProperties = PropertiesLoaderUtils.loadAllProperties("console.properties");
     * 1.它会解析classpath*下所有的文件
     * 2.顺序是：本体项目 -> maven中引入顺序（从上到下）
     * 3. 相同key后面的覆盖前面的
     */
    @Test
    public void loadResource(){
        Properties prop  = new Properties();
        try {
            //下面这两方式,如果不是 / 开头会拼接当前class的路径查找,是则再到class路径根目录查找
//            InputStream in = getClass().getResource("demo.properties").openStream();
//            InputStream in = getClass().getResourceAsStream("demo.properties");

            //下面这两方式不管有没有 / 都是从class的根目录查找
            InputStream in = getClass().getClassLoader().getResource("demo.properties").openStream();
//            InputStream in = getClass().getClassLoader().getResourceAsStream("demo.properties");
            prop.load(in);
        } catch (Exception e) {
            logger.error("{}",e);
        }
        System.out.println(prop);

        System.out.println(getClass().getResource("").getPath());
        System.out.println(getClass().getClassLoader().getResource("").getPath());

    }


    @Test
    public void list(){
        List<String> list = new ArrayList<>();// Default initial capacity：10
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
    }


    @Test
    public void shutdownHook(){
        Thread thread1 = new Thread(() -> System.out.println("thread1..."));
        Thread thread2 = new Thread(() -> System.out.println("thread2..."));
        Thread shutdownThread = new Thread(() -> System.out.println("shutdownThread..."));
        Runtime.getRuntime().addShutdownHook(shutdownThread);
        thread1.start();
        thread2.start();
    }


    @Test
    public void map(){
        TreeMap<Integer,String> map = new TreeMap();
        map.put(55,"fifty-five");
        map.put(56,"fifty-six");
        map.put(57,"fifty-seven");
        map.put(58,"fifty-eight");
        map.put(83,"eighty-three");
        map.remove(57);
        map.put(59,"fifty-nine");
        map.forEach((k,v) -> System.out.println(k+" = "+v));

    }


    @Test
    public void modifyAnnotationValue() throws Exception{
        BeanDefine bar = new BeanDefine();

        Annotation[] annotations = bar.getClass().getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation.annotationType().getName().equals(TestAnnotation.class.getName())){
                TestAnnotation t = (TestAnnotation)annotation;
                String value =  t.prefix();
                System.out.println("修改之前的注解值：" + value);

                InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
                declaredField.setAccessible(true);
                Map memberValues = (Map) declaredField.get(invocationHandler);
                memberValues.put("prefix", "test.annotation.new.value");

                String newValue = t.prefix();
                System.out.println("修改之后的注解值：" + newValue);
            }
        }

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
     *
     * 使用WebCollector 一次性抓取
     */
    @Test
    public void crawler() throws Exception {
        CrawlerDemo crawlerDemo = new CrawlerDemo("G:\\data\\crawler",true);
        crawlerDemo.start(1);
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

    @Test
    public void jwtToken() throws UnsupportedEncodingException {
        //签发时间
        Date iaDate = new Date();

        //过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,1);
        Date expireDate = nowTime.getTime();

        Map<String,Object> map = Maps.newHashMap();
        String token = JWT.create()
                .withHeader(map)
                .withClaim("userId",1)
                .withExpiresAt(expireDate)
                .withIssuedAt(iaDate)
                .withIssuer("hiekn")
                .sign(Algorithm.HMAC384("SECRET"));

        JWTVerifier verifier = JWT.require(Algorithm.HMAC384("SECRET")).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();

        claims.forEach((k,v) -> System.out.println(k+"  "+v.asString()));
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
