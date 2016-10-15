package com.hello.library;

import java.sql.*;

/**
 * Created by Linty on 9/25/2016.
 * 数据库功能类
 */
public class DbUtility {
    /**
     * 只能返回MySQL数据库的查询结果
     *
     * @param connectionString 数据库的服务器地址，数据库的名字，数据库的用户名和密码
     * @param sql              String型，简单的Select查询语句
     * @return 查询结果
     */
    public ResultSet getResultSetForMySQL(String connectionString, String sql) {
        // 存放SQL语句的变量
        Statement stmt = null;

        // 存放查询结果的变量
        ResultSet rs = null;
        try {
            // 第一步：注册mysql的jdbc驱动的实例
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = null;
            try {
                // 创建连接字符串，并获取数据库连接
                // jdbc java数据库驱动
                // mysql 驱动mysql
                // localhost mysql的主机
                // test 数据库名
                // user password 用户名 密码
                conn = DriverManager.getConnection(connectionString);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...

            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                System.out.println(rs.next());
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rs;
    }
}
