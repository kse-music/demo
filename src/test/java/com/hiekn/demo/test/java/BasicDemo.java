package com.hiekn.demo.test.java;

import com.hiekn.demo.test.java.annotation.BeanDefine;
import com.hiekn.demo.test.java.annotation.TestAnnotation;
import com.hiekn.demo.test.java.annotation.ZxfResource;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicDemo {
    public static void main(String[] args) throws Exception{
        Integer a = 128;
        Integer b = 128;
        Integer c = Integer.valueOf(127);
        Integer d = Integer.valueOf(127);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(c == d);
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(P.class);
        P p = new P();
        for (PropertyDescriptor descriptor : descriptors) {
            String name = descriptor.getName();
            if(!"class".equals(name)){
                MethodParameter methodDescriptors = BeanUtils.getWriteMethodParameter(descriptor);
                System.out.println(name);
                Method method = methodDescriptors.getMethod();
                method.invoke(p,1);
            }
        }
        System.out.println(p.getA());

        AnnotatedTypeMetadata metadata = new StandardAnnotationMetadata(BeanDefine.class);
        Map<String, Object> attributes = metadata.getAnnotationAttributes(TestAnnotation.class.getName());
        System.out.println(attributes.get("prefix"));
    }

    private static List<AnnotationAttributes> annotationAttributesFromMultiValueMap(MultiValueMap<String, Object> multiValueMap) {
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

class P{
    private Integer a;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

}
