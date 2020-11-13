package com.tywh.egov.system.controller;
/*
插入用户的controller
 */

import com.tywh.egov.utils.Const;
import com.tywh.egov.utils.DateUtil;
import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAddController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usercode = request.getParameter("usercode");
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        String orgtype = request.getParameter("orgtype");

//        String regdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String regdate = DateUtil.format(new Date(), Const.DATE_PATTERN);
        String sql = "insert into t_user(usercode,username,userpwd,orgtype,regdate) values(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,usercode);
            ps.setString(2,username);
            ps.setString(3,userpwd);
            ps.setString(4,orgtype);
            ps.setString(5,regdate);
            count = ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps,null);
        }

        if (count == 1) {
            response.sendRedirect("/system/user.html");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
