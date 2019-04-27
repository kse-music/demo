package com.hiekn.demo.test.spring.basic;

import com.hiekn.demo.test.spring.annotation.TestAnnotation;

@TestAnnotation("abc")
public class BeanDefine {

    private String id;
    private String className;

    public BeanDefine() {
    }

    public BeanDefine(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
