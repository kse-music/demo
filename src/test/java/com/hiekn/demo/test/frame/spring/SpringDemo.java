package com.hiekn.demo.test.frame.spring;

import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.test.frame.spring.aop.AopConfig;
import com.hiekn.demo.test.frame.spring.basic.DemoBean1;
import com.hiekn.demo.test.frame.spring.basic.TestBean;
import com.hiekn.demo.test.frame.spring.basic.TestBeanConfiguration;
import com.hiekn.demo.test.frame.spring.basic.TestPropertyBean;
import com.hiekn.demo.test.frame.spring.hierarchy.ChildContext;
import com.hiekn.demo.test.frame.spring.hierarchy.ParentContext;
import com.hiekn.demo.test.frame.spring.processor.TestConfiguration;
import com.hiekn.demo.test.frame.spring.proxy.cglib.CGLibProxy;
import com.hiekn.demo.test.frame.spring.proxy.cglib.HelloConcrete;
import com.hiekn.demo.test.frame.spring.proxy.cglib.MyMethodInterceptor;
import com.hiekn.demo.test.frame.spring.proxy.jdk.*;
import com.hiekn.demo.test.java.annotation.BeanDefine;
import com.hiekn.demo.test.java.annotation.TestAnnotation;
import com.hiekn.demo.test.java.annotation.ZxfResource;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.MultiValueMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        TestConfiguration bean = context.getBean(TestConfiguration.class);
        System.out.println(bean.test());
    }

    @Test
    public void runFromXml(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");//从spring-context.xml加载
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        System.out.println(jdbcTemplate);
    }

    /**
     * 查看aop源码
     */
    @Test
    public void testAop(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class,AopConfig.class);
        TestConfiguration bean = context.getBean(TestConfiguration.class);
        System.out.println(bean.test());
    }

    /**
     * 循环依赖注入、FactoryBean、@Bean、ObjectFactory、ObjectProvider、@ImportResource
     */
    @Test
    public void testIoc(){

        ApplicationContext context = new AnnotationConfigApplicationContext(TestBeanConfiguration.class);

        TestBean tb = context.getBean(TestBean.class);
        System.out.println(tb.getName());
        DemoBean1 demoBean1 = context.getBean(DemoBean1.class);
        System.out.println(demoBean1);
        demoBean1.test();

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
    public void testProxy(){
        Hello hello = (Hello)Proxy.newProxyInstance(
                SpringDemo.class.getClassLoader(), // 1. 类加载器
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

    @Test
    public void parseAnnotation() throws InvocationTargetException, IllegalAccessException {
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(TestPropertyBean.class);
        TestPropertyBean testPropertyBean = new TestPropertyBean();
        for (PropertyDescriptor descriptor : descriptors) {
            String name = descriptor.getName();
            if(!"class".equals(name)){
                MethodParameter methodDescriptors = BeanUtils.getWriteMethodParameter(descriptor);
                System.out.println(name);
                Method method = methodDescriptors.getMethod();
                method.invoke(testPropertyBean,1);
            }
        }

        System.out.println(testPropertyBean.getA());

        AnnotatedTypeMetadata metadata = new StandardAnnotationMetadata(BeanDefine.class);
        System.out.println(((StandardAnnotationMetadata) metadata).hasAnnotation(ZxfResource.class.getName()));//false
        System.out.println(metadata.isAnnotated(ZxfResource.class.getName()));//true
        Map<String, Object> attributes = metadata.getAnnotationAttributes(TestAnnotation.class.getName());
        System.out.println(attributes.get("prefix"));
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