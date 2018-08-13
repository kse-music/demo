package com.hiekn.demo.test.java.cert;

public class licenseCreateTest {
    public static void main(String[] args){
        CreateLicense cLicense = new CreateLicense();
        //获取参数
        cLicense.setParam("param.properties");
        //生成证书
        cLicense.create();
    }
}
