package com.tywh.egov.system.controller;

import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserUniqueCheckController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usercode = request.getParameter("usercode");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exist = false;
        String sql = "select usercode,username from t_user where usercode = ?";
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usercode);
            rs = ps.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        response.setContentType("text/html;charset=GB18030");
        PrintWriter out =  response.getWriter();
        if (exist) {
            out.write("<font color='red'>用户代码已存在</font>");
        } else {
            out.write("<font color='green'>用户代码可用</font>");
        }

    }
}
