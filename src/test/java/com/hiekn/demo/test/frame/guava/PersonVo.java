package com.hiekn.demo.test.frame.guava;

import com.google.common.base.MoreObjects;

/**
 * @Author: DingHao
 * @Date: 2018/11/28 16:22
 */
public class PersonVo {
    private String name;
    private int age;
    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .add("msg", msg).toString();
    }
}