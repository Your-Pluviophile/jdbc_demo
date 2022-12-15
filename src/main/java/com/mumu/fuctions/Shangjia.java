package com.mumu.fuctions;

import com.mumu.util.DBUtils;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 图书管理员的上架操作
 */
public class Shangjia {

    public static void main(String[] args) throws SQLException {
        //读取书籍名称和上架数量
        Scanner sc = new Scanner(System.in);
        System.out.println("上架功能");
        System.out.println("请输入书籍名称");

        if (!sc.hasNextLine()) {
            return;
        }

        String name = sc.nextLine();
        System.out.println("请输入书籍数量");
        int number = Integer.parseInt(sc.nextLine());

        Connection connection = DBUtils.connection();
        //sql语句 动态sql
        String selectSql = "select * from books where name = ? ";
        String insertSql = "insert into books(name,current,total) values(?,?,?)";
        String updateSql = "update books set current = current + ?,total = total + ? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();

        Integer bid;
        if (resultSet.next()) {
            bid = resultSet.getInt("id");
            String bookName = resultSet.getString("name");
            int total = resultSet.getInt("total");
            int current = resultSet.getInt("current");
            System.out.println(bid + " " + bookName + " " + current + " " + total);
        }else{
            bid = null;
        }

        if(bid != null){
            //查询得到则更新
            PreparedStatement preparedStatement1 = connection.prepareStatement(updateSql);

            preparedStatement1.setInt(1, number);
            preparedStatement1.setInt(2, number);
            preparedStatement1.setInt(3, bid);
            preparedStatement1.executeUpdate();
            System.out.println("释放更新资源");
            preparedStatement1.close();
        }else{
            //如果查询不到记录 则插入

            PreparedStatement preparedStatement1 = connection.prepareStatement(insertSql);

            preparedStatement1.setString(1, name);
            preparedStatement1.setInt(2, number);
            preparedStatement1.setInt(3, number);
            preparedStatement1.executeUpdate();
            System.out.println("释放插入资源");
            preparedStatement1.close();
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        System.out.println("释放资源");

    }
}
