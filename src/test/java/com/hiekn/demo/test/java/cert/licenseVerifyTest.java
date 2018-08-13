package com.hiekn.demo.test.java.cert;

public class licenseVerifyTest {
    public static void main(String[] args){
        VerifyLicense vLicense = new VerifyLicense();
        //获取参数
        vLicense.setParam("verify.properties");
        //验证证书
        vLicense.verify();
    }
}
