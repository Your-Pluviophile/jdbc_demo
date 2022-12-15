package com.mumu;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        // 一. 创建 DataSource 对象
        // DataSource 是官方的接口
        // 所以，我们真实中创建的是 MySQL 厂商写的 DataSource 的实现类 MysqlDataSource 对象
        MysqlDataSource dataSource = new MysqlDataSource();
        // 进行必要的配置
        // 1. 服务器在哪台主机（网络的哪台电脑）上
        //     127.0.0.1  或者   localhost
        //      ip                   域名（domain/host）
        // 代表都是本机：客户端在哪台电脑上，服务器就在哪台电脑上
        dataSource.setServerName("");

        // 2. 服务器在这台主机监听哪个端口
        // 默认就是 3306 端口
        dataSource.setPort(3306);

        // 3. 连接服务器的时候使用哪个用户名
        // 使用权限最大的 root 用户
        dataSource.setUser("");

        // 4. 连接服务器的时候，密码是什么
        // 这里的密码不保证一样
        dataSource.setPassword("");

        // 5. 连接上去之后，默认库是哪个
        // 这个大家根据自己的情况来改
        dataSource.setDatabaseName("");

        // 6. 一些额外的配置
        // 配置客户端和服务器通信时使用的字符集编码
        dataSource.setCharacterEncoding("utf-8");
//        dataSource.setCharacterEncoding("utf8");
        // 配置客户端和服务器通信时是否要进行数据的加密
        dataSource.setUseSSL(false);
        // 配置客户端和服务器通信时，服务器的时区 ：北京时间 —— 东 8 区 —— 亚洲/上海
        // Asia/Shanghai
        dataSource.setServerTimezone("Asia/Shanghai");


        // 二、利用构建好的 DataSource 对象，创建 Connection 对象
        // Connection 是需要 关闭（close）的
        Connection c = null;
        c = dataSource.getConnection();
        // bla bla

        // 执行一条 select 语句
        String sql = "select * from books";

        // 三、利用 Connection 对象 + String 类型的 SQL 语句，创建 PreparedStatement 对象
        // PreparedStatement 对象同样需要考虑关闭的逻辑
        PreparedStatement ps = null;
        ps = c.prepareStatement(sql);

        // 四、发送这个 SQL 语句 ： 执行这个 SQL 语句对象
        // 执行：execute 得到 ResultSet 对象

        ResultSet rs = null;
        rs = ps.executeQuery(); // executeQuery 用于 select 语句

        // 五、遍历得到的结果
        // 一会儿再去解释这里的逻辑
        while (rs.next()) {
            int bid = rs.getInt("id");
            String name = rs.getString("name");
            int total = rs.getInt("total");
            int current = rs.getInt("current");
            System.out.printf("%4d | %10s | %4d | %4d\n", bid, name, total, current);
        }

        rs.close();
        ps.close();
        c.close();  // 关闭掉
    }
}

