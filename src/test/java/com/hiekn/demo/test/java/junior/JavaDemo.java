package com.hiekn.demo.test.java.junior;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 综合性示例，各种小demo在此
 *
 * @author DingHao
 * @date 2018/12/11 15:41
 */
public class JavaDemo extends TestBase {


    /**
     * 一、使用clazz.getClassLoader().getResourceAsStream(fileName)方式
     * 1.它只会解析一个配置文件
     * 2.顺序是：本体项目 -> maven引入jar中的第一个
     * <p>
     * 二、使用spring工具类，Properties globalProperties = PropertiesLoaderUtils.loadAllProperties("console.properties");
     * 1.它会解析classpath*下所有的文件
     * 2.顺序是：本体项目 -> maven中引入顺序（从上到下）
     * 3. 相同key后面的覆盖前面的
     */
    @Test
    public void loadResource() {
        Properties prop = new Properties();
        try {
            //下面这两方式,如果不是 / 开头会拼接当前class的路径查找,是则再到class路径根目录查找
//            InputStream in = getClass().getResource("demo.properties").openStream();
//            InputStream in = getClass().getResourceAsStream("demo.properties");

            //下面这两方式不管有没有 / 都是从class的根目录查找
            InputStream in = getClass().getClassLoader().getResource("demo.properties").openStream();
//            InputStream in = getClass().getClassLoader().getResourceAsStream("demo.properties");
            prop.load(in);
        } catch (Exception e) {
            logger.error("{}", e);
        }
        System.out.println(prop);

        System.out.println(getClass().getResource("").getPath());
        System.out.println(getClass().getClassLoader().getResource("").getPath());

    }

    @Test
    public void mac() throws UnknownHostException, SocketException {
        //得到IP，输出PC-201309011313/122.206.73.83

        InetAddress ia = InetAddress.getLocalHost();

        System.out.println(ia);

        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

        // 下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }

        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        System.out.println(sb.toString().toUpperCase());
    }


    /**
     * java执行js
     */
    @Test
    public void js() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(engine.getClass().getName());
        try {
            System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }


    /**
     * 利用hutool封装的加密工具
     */
    @Test
    public void crypto() {
        String content = "test中文";

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();

        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.DESede, key);

        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);

        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);

    }

    @Test
    public void jwtToken() throws UnsupportedEncodingException {
        //签发时间
        Date iaDate = new Date();

        //过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 1);
        Date expireDate = nowTime.getTime();

        Map<String, Object> map = Maps.newHashMap();
        String token = JWT.create()
                .withHeader(map)
                .withClaim("userId", 1)
                .withExpiresAt(expireDate)
                .withIssuedAt(iaDate)
                .withIssuer("hiekn")
                .sign(Algorithm.HMAC384("SECRET"));

        JWTVerifier verifier = JWT.require(Algorithm.HMAC384("SECRET")).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();

        claims.forEach((k, v) -> System.out.println(k + "  " + v.asString()));
    }

}
