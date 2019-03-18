package com.hiekn.demo.test.frame.spring;

import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.test.frame.spring.basic.*;
import com.hiekn.demo.test.frame.spring.hierarchy.ChildContext;
import com.hiekn.demo.test.frame.spring.hierarchy.ParentContext;
import com.hiekn.demo.test.frame.spring.processor.SingleResearchSpring;
import com.hiekn.demo.test.frame.spring.proxy.UserManager;
import com.hiekn.demo.test.frame.spring.proxy.UserManagerImpl;
import com.hiekn.demo.test.frame.spring.proxy.cglib.*;
import com.hiekn.demo.test.frame.spring.proxy.jdk.*;
import com.hiekn.demo.test.java.annotation.BeanDefine;
import com.hiekn.demo.test.java.annotation.TestAnnotation;
import com.hiekn.demo.test.java.annotation.ZxfResource;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringDemo extends TestBase {


    /**
     * 单个看某一个功能点
     */
    @Test
    public void research(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SingleResearchSpring.class);
        SingleResearchSpring bean = context.getBean(SingleResearchSpring.class);
        ExampleBean2 bean2 = context.getBean(ExampleBean2.class);
        System.out.println(bean.test());
    }


    /**
     * 包含  replace-method 示例
     */
    @Test
    public void runFromXml(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");//从spring-context.xml加载
        ReplaceMethodDemo replaceMethodDemo = context.getBean(ReplaceMethodDemo.class);
        System.out.println(replaceMethodDemo.getName());
    }

    /**
     * 包含以下示例：
     *      循环依赖注入、FactoryBean、@Bean、ObjectFactory、ObjectProvider、@ImportResource、@Lookup、@Primary、aop
     */
    @Test
    public void testBasic(){

        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);

        TestBean tb = context.getBean(TestBean.class);
        System.out.println(tb.getName());

        DemoBean demoBean = context.getBean(DemoBean.class);
        demoBean.test();

        LookupDemo look = context.getBean(LookupDemo.class);
        look.look();

    }

    @Test
    public void testHierarchy(){
        ApplicationContext parent = new AnnotationConfigApplicationContext(ParentContext.class);
        ApplicationContext child = new AnnotationConfigApplicationContext(ChildContext.class);
        Object obj = child.getBean("parentContext");
        System.out.println(obj);

        ((AnnotationConfigApplicationContext) child).setParent(parent);
        Object obj2 = child.getBean("parentContext");
        System.out.println(obj2);

    }

    @Test
    public void jdkProxy(){
        UserManager hello = (UserManager)Proxy.newProxyInstance(
                SpringDemo.class.getClassLoader(), // 1. 类加载器
                new Class<?>[] {UserManager.class}, // 2. 代理需要实现的接口，可以有多个
                new LogInvocationHandler(new UserManagerImpl()));// 3. 方法调用的实际处理者
        hello.addUser("1","I love you!");

        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");
    }

    @Test
    public void cglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new MyMethodInterceptor());
        HelloConcrete hello = (HelloConcrete)enhancer.create();
        System.out.println(hello.sayHello("I love you!"));

//        UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
//        userManager.addUser("tom", "root");
    }

    @Test
    public void parseAnnotation() {
        AnnotatedTypeMetadata metadata = new StandardAnnotationMetadata(BeanDefine.class);
        System.out.println(((StandardAnnotationMetadata) metadata).hasAnnotation(ZxfResource.class.getName()));//false
        System.out.println(metadata.isAnnotated(ZxfResource.class.getName()));//true
        Map<String, Object> attributes = metadata.getAnnotationAttributes(TestAnnotation.class.getName());
        MultiValueMap<String, Object> multiValueMap = metadata.getAllAnnotationAttributes(TestAnnotation.class.getName());
        List<AnnotationAttributes> annotationAttributes = annotationAttributesFromMultiValueMap(multiValueMap);
        System.out.println(attributes.get("value"));
    }

    List<AnnotationAttributes> annotationAttributesFromMultiValueMap(MultiValueMap<String, Object> multiValueMap) {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Map.Entry<String, List<Object>> entry : multiValueMap.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                Map<String, Object> map;
                if (i < maps.size()) {
                    map = maps.get(i);
                }else {
                    map = new HashMap<>();
                    maps.add(map);
                }
                map.put(entry.getKey(), entry.getValue().get(i));
            }
        }
        List<AnnotationAttributes> annotationAttributes = new ArrayList<>(maps.size());
        for (Map<String, Object> map : maps) {
            annotationAttributes.add(AnnotationAttributes.fromMap(map));
        }
        return annotationAttributes;
    }

}