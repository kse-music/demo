package com.hiekn.demo.test.java.basic;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;

/**
 * @Author: DingHao
 * @Date: 2018/11/30 10:43
 */
public class SystemDemo extends TestBase {

    @Test
    public void getAll(){

        Properties props = System.getProperties();
        props.list(System.out);

        Map<String, String> envs = System.getenv();
        for (Map.Entry<String, String> env : envs.entrySet()) {
            System.out.println(env.getKey() + " = " + env.getValue());
        }

    }

}
