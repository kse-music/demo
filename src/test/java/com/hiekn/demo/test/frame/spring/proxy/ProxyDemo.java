package com.hiekn.demo.test.frame.spring.proxy;

import com.hiekn.demo.test.frame.spring.proxy.cglib.CGLibProxy;
import com.hiekn.demo.test.frame.spring.proxy.cglib.HelloConcrete;
import com.hiekn.demo.test.frame.spring.proxy.cglib.MyMethodInterceptor;
import com.hiekn.demo.test.frame.spring.proxy.jdk.*;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.cglib.proxy.Enhancer;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

public class ProxyDemo {

    public static void main(String[] args) {
        Hello hello = (Hello)Proxy.newProxyInstance(
                ProxyDemo.class.getClassLoader(), // 1. 类加载器
                new Class<?>[] {Hello.class}, // 2. 代理需要实现的接口，可以有多个
                new LogInvocationHandler(new HelloImpl()));// 3. 方法调用的实际处理者
        System.out.println(hello.sayHello("I love you!"));


        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new MyMethodInterceptor());

        HelloConcrete hello2 = (HelloConcrete)enhancer.create();
        System.out.println(hello2.sayHello("I love you!"));



        System.out.println("-----------CGLibProxy-------------");
        UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
        userManager.addUser("tom", "root");

        System.out.println("-----------JDKProxy-------------");
        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");

    }

    public static void helloWorld() throws Exception {
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
        FileOutputStream output = new FileOutputStream(file);
        output.write(code);
        output.close();
    }

}
