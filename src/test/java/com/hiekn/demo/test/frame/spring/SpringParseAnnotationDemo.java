package com.hiekn.demo.test.frame.spring;

import com.hiekn.demo.test.java.annotation.BeanDefine;
import com.hiekn.demo.test.java.annotation.TestAnnotation;
import com.hiekn.demo.test.frame.spring.bean.P;
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

/**
 * @Author: DingHao
 * @Date: 2018/11/28 18:34
 */
public class SpringParseAnnotationDemo {
    public static void main(String[] args) throws Exception {
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
