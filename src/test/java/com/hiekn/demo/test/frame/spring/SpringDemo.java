package com.hiekn.demo.test.frame.spring;

import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.test.frame.spring.aop.AopConfig;
import com.hiekn.demo.test.frame.spring.basic.*;
import com.hiekn.demo.test.frame.spring.processor.TestConfiguration;
import com.hiekn.demo.test.java.annotation.BeanDefine;
import com.hiekn.demo.test.java.annotation.TestAnnotation;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringDemo extends TestBase {

    @Test
    public void justOne(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class,AopConfig.class);
        TestConfiguration bean = context.getBean(TestConfiguration.class);
        System.out.println(bean.test());
    }

    @Test
    public void testBasic(){
        //如果加载spring-context.xml文件：
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(TestBeanConfiguration.class);

        TestBean tb = context.getBean(TestBean.class);
        System.out.println(tb.getName());
        DemoBean1 demoBean1 = context.getBean(DemoBean1.class);
        System.out.println(demoBean1);
        demoBean1.test();
        System.out.println(context.getBean(DemoBean2.class));
        DemoBean3 demoBean3 = context.getBean(DemoBean3.class);
        System.out.println(demoBean3);

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