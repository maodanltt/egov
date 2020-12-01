package com.tywh.egov.user.controller;

import com.tywh.egov.bean.User;
import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usercode = request.getParameter("usercode");
        String userpwd = request.getParameter("userpwd");
        String orgtype = request.getParameter("orgtype");

        String sql = "select username from t_user where userpwd = ? and orgtype = ? and usercode = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,userpwd);
            ps.setString(2,orgtype);
            ps.setString(3,usercode);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsercode(usercode);
                user.setUserpwd(userpwd);
                user.setUsername(rs.getString("username"));
                user.setOrgtype(orgtype);
                HttpSession session = request.getSession(true);
                session.setAttribute("user",user);
                loginSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        if (loginSuccess) {
            response.sendRedirect("/main.html");
        } else {
            response.setContentType("text/hmtl;charset=GB18030");
            PrintWriter out = response.getWriter();
            out.print("<font color='red'>用户名或密码错误</font>");
        }
    }
}
