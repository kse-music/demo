package com.hiekn.demo.test.java.senior;

import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.test.java.annotation.Student;
import org.junit.Test;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意一个方法和属性；
 * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 *
 *    1、获取Class对象的三种方式
 *      1.1 Object ——> getClass();
 *      1.2 任何数据类型（包括基本数据类型）都有一个"静态”的class属性
 *      1.3 通过Class类的静态方法：forName（String  className）(常用)
 *
 * @author: DingHao
 * @date: 2019/4/3 17:34
 */
public class ReflectDemo extends TestBase {


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
     * @throws IntrospectionException
     */
    @Test
    public void introspector() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        //1 获得 java Bean的描述信息
        BeanInfo info = Introspector.getBeanInfo(UserBean.class);
        //2 获得 UserBean中的属性信息
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        //3 遍历属性信息
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getName());
        }

        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(UserBean.class);
        UserBean userBean = new UserBean();
        for (PropertyDescriptor descriptor : descriptors) {
            String name = descriptor.getName();
            if(!"class".equals(name) && "name".equals(name)){
                MethodParameter methodDescriptors = BeanUtils.getWriteMethodParameter(descriptor);
                Method method = methodDescriptors.getMethod();
                method.invoke(userBean,"nn");
            }
        }
        System.out.println(userBean.getName());

    }

}
