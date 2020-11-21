package com.tywh.egov.system.controller;

import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDeleteController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] usercodes = request.getParameterValues("checkbox");
        String sql = "delete from t_user where usercode = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i=0; i<usercodes.length; i++) {
                ps.setString(1,usercodes[i]);
                ps.executeUpdate();
                count++;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, null);
        }

        if (count == usercodes.length) {
            response.sendRedirect("/user/query");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
