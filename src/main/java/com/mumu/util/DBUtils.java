package com.mumu.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private static final DataSource dataSource;

    static {
        //获取连接
        try{
            MysqlDataSource MyDataSource = new MysqlDataSource();
            MyDataSource.setServerName("49.235.68.71");
            MyDataSource.setPort(3306);
            MyDataSource.setUser("root");
            MyDataSource.setPassword("root123456!");
            MyDataSource.setDatabaseName("ccb_1211");
            MyDataSource.setCharacterEncoding("utf-8");
            MyDataSource.setServerTimezone("Asia/Shanghai");
            System.out.println("DEBUG:创建了一个数据源对象");

            dataSource = MyDataSource;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Connection connection() throws SQLException {
        return dataSource.getConnection();
    }
}
