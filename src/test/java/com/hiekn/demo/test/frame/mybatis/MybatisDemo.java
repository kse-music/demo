package com.hiekn.demo.test.frame.mybatis;

import com.hiekn.demo.test.TestBase;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author: DingHao
 * @Date: 2018/11/30 13:21
 */
public class MybatisDemo extends TestBase {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
//        sqlSessionFactory = buildSqlSessionFactoryByXml();
//        sqlSessionFactory.getConfiguration().addMapper(NewsMapper.class);//if use annotation,remove xml mapper

        sqlSessionFactory = buildSqlSessionFactoryByConfig();
    }

    private SqlSessionFactory buildSqlSessionFactoryByXml() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    private SqlSessionFactory buildSqlSessionFactoryByConfig(){
        DataSource dataSource = new PooledDataSource("com.mysql.jdbc.Driver","jdbc:mysql://192.168.1.159:3306/test","root","root@hiekn");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(NewsMapper.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    /**
     * #{} 预编译
     * ${} 字符串拼接
     */
    @Test
    public void testSelect(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);//方式一：Good
        Map<String, Object> map = mapper.selectTitle();
        logger.info("{}",map);

        Map<Object, Object> map2 = sqlSession.selectOne(NewsMapper.class.getName() + ".selectTitle");//方式二
        logger.info("{}",map2);

        sqlSession.close();
    }


}
