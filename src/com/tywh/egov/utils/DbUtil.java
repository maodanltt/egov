package com.tywh.egov.utils;

import java.sql.*;

/*
    封装数据库连接
 */
public class DbUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() throws Exception{
        //com.mysql.jdbc.Driver
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/egov","root","root123");
//        return conn;
        Connection conn = threadLocal.get();
        if (conn == null) {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/egov","root","root123");
            threadLocal.set(conn);
        }
        return conn;
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
                threadLocal.remove();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
