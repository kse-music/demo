package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * 解决了LookupDemo是单例，而TestBean是原型
 *
 * 这个属性可以使Spring克服当一个bean依赖其他不同生命周期的bean的情况，
 * 比如当一个单例bean依赖一个非单例对象的时候
 *
 * @author DingHao
 * @date 2019/1/2 14:04
 */
@Component
public abstract class LookupDemo {

    public void look() {
        System.out.println(this.getTestBean());
    }

    @Lookup
    public abstract TestBean getTestBean();
}
