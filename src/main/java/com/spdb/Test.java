package com.spdb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) throws Exception {
        String sql =" select count(*) as num from user ";
        String url="jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
        String user = "root";
        String password = "root";
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据库连接
        Connection  connection= DriverManager.getConnection(url,user,password);
        //3.通过数据库连接操作数据库
        Statement statement = connection.prepareStatement(sql);
        ResultSet resultSet =statement.executeQuery(sql);
        //关闭资源
        statement.close();
        connection.close();
        System.out.println(resultSet);

    }
    public static int queryCount(Connection connection,String sql)throws Exception{
        String url="jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
        String user = "root";
        String password = "tiger";
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据库连接
        connection= DriverManager.getConnection(url,user,password);
        //3.通过数据库连接操作数据库
        Statement statement = connection.createStatement();
        int i =statement.executeUpdate(sql);
        //4.
        //关闭资源
        statement.close();
        connection.close();
        return i;
    }

}

