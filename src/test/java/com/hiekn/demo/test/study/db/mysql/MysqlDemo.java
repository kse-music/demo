package com.hiekn.demo.test.study.db.mysql;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 所谓聚簇索引，就是指主索引文件和数据文件为同一份文件，聚簇索引主要用在Innodb存储引擎中。
 * 在该索引实现方式中B+Tree的叶子节点上的data就是数据本身，key为主键，如果是一般索引的话，data便会指向对应的主索引
 *
 *
 * 非聚簇索引就是指B+Tree的叶子节点上的data，并不是数据本身，而是数据存放的地址。
 * 主索引和辅助索引没啥区别，只是主索引中的key一定得是唯一的。主要用在MyISAM存储引擎中
 *
 * MyisAM支持全文索引（FULLTEXT）、压缩索引，InnoDB不支持；
 * InnoDB支持事务，MyisAM不支持；
 * MyisAM顺序储存数据，索引叶子节点保存对应数据行地址，辅助索引和主键索引相差无几；InnoDB主键节点同时保存数据行，其他辅助索引保存的是主键索引的值；
 * MyisAM键值分离，索引载入内存（key_buffer_size），数据缓存依赖操作系统；InnoDB键值一起保存，索引与数据一起载入InnoDB缓冲池；MyisAM主键（唯一）索引按升序来存储存储，InnoDB则不一定
 * MyisAM索引的基数值（Cardinality，show index 命令可以看见）是精确的，InnoDB则是估计值。这里涉及到信息统计的知识，MyisAM统计信息是保存磁盘中，在alter表或Analyze table操作更新此信息，而InnoDB则是在表第一次打开的时候估计值保存在缓存区内；
 * MyisAM处理字符串索引时用增量保存的方式，如第一个索引是‘preform’，第二个是‘preformence’，则第二个保存是‘7，ance’，这个明显的好处是缩短索引，但是缺陷就是不支持倒序提取索引，必须顺序遍历获取索引
 *
 * @author DingHao
 * @date 2019/2/20 15:21
 */
public class MysqlDemo extends TestBase {

    /**
     * InnoDB：自动增长计数器仅被存储在主内存中，而不是存在磁盘上。
     *
     */

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
