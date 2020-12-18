package com.tywh.egov.system.controller;

import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDeleteController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] usercodes = request.getParameterValues("checkbox");
        String sql = "delete from t_user where usercode = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flag = true;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (int i=0; i<usercodes.length; i++) {
                ps.setString(1,usercodes[i]);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (Exception e){
            flag = false;
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {

            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            DbUtil.close(conn, ps, null);
        }

        if (flag) {
            response.sendRedirect("/controller/user/query");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
