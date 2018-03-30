package com.hiekn.demo.test.java;

import com.hiekn.demo.bean.UserBean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class IntrospectorDemo {

    public static void main(String[] args) throws IntrospectionException {
            //1 获得 java Bean的描述信息
            BeanInfo info =Introspector.getBeanInfo(UserBean.class);
            //2 获得 UserBean中的属性信息
            PropertyDescriptor[] pds =info.getPropertyDescriptors();
            //3 遍历属性信息
            for (PropertyDescriptor pd : pds) {
                System.out.println(pd.getName());
            }
    }

}
