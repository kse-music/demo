package com.hiekn.demo.test.study.spring.basic;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * PrototypeBean
 *
 * @author DingHao
 * @date 2019/1/10 16:51
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean {

}
