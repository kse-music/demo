package com.hiekn.demo.test.frame.db.mysql;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * describe about this class
 *
 * @author DingHao
 * @date 2019/2/20 15:21
 */
public class MysqlDemo extends TestBase {

    @Test
    public void lock() throws Exception {
        final int THREAD_COUNT = 10;
        final int RUN_TIME = 100;

        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        //用CountDownLatch保证主线程等待所有任务完成
        CountDownLatch count = new CountDownLatch(RUN_TIME);

        for (int i = 0; i < RUN_TIME; i++)
            threadPool.execute(new LostUpdate(count));

        threadPool.shutdown();
        count.await();
        //提示所有任务执行完
        System.out.println("finish");
    }

    static class LostUpdate implements Runnable {
        private CountDownLatch countDown;

        public LostUpdate(CountDownLatch countDown) {
            this.countDown = countDown;
        }

        @Override
        public void run() {
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://192.168.1.159:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                        "root", "root@hiekn");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            try {
                conn.setAutoCommit(false);
                //不加锁的情况
                PreparedStatement ps = conn.prepareStatement("select * from LostUpdate where id =1");
                //加锁的情况
//                PreparedStatement ps =conn.prepareStatement("select * from LostUpdate where id =1 for update");
                ResultSet rs = ps.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count = rs.getInt("count");
                }

                count++;
                ps = conn.prepareStatement("update LostUpdate set count=? where id =1");
                ps.setInt(1, count);
                ps.executeUpdate();

                conn.commit();
            } catch (Exception e) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
            //表示一次任务完成
            countDown.countDown();
        }
    }
}
