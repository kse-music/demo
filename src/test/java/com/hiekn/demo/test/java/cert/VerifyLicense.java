package com.hiekn.demo.test.java.cert;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultKeyStoreParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseParam;
import de.schlichtherle.license.LicenseManager;

/**
 * VerifyLicense
 * @author LL
 */
public class VerifyLicense {
    //common param
    private static String PUBLICALIAS = "";
    private static String STOREPWD = "";
    private static String SUBJECT = "";
    private static String licPath = "";
    private static String pubPath = "";

    public void setParam(String propertiesPath) {
        // 获取参数
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream(propertiesPath);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PUBLICALIAS = prop.getProperty("PUBLICALIAS");
        STOREPWD = prop.getProperty("STOREPWD");
        SUBJECT = prop.getProperty("SUBJECT");
        licPath = prop.getProperty("licPath");
        pubPath = prop.getProperty("pubPath");
    }

    public boolean verify() {
        /************** 证书使用者端执行 ******************/

        LicenseManager licenseManager = LicenseManagerHolder.getLicenseManager(initLicenseParams());
        // 安装证书
        try {
            licenseManager.install(new File(licPath));
            System.out.println("客户端安装证书成功!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端证书安装失败!");
            return false;
        }
        // 验证证书
        try {
            licenseManager.verify();
            System.out.println("客户端验证证书成功!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端证书验证失效!");
            return false;
        }
        return true;
    }

    // 返回验证证书需要的参数
    private static LicenseParam initLicenseParams() {
        Preferences preference = Preferences
                .userNodeForPackage(VerifyLicense.class);
        CipherParam cipherParam = new DefaultCipherParam(STOREPWD);

        KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(
                VerifyLicense.class, pubPath, PUBLICALIAS, STOREPWD, null);
        LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT,
                preference, privateStoreParam, cipherParam);
        return licenseParams;
    }
}
