package com.tywh.egov.system.controller;

import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserUpdateController extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("GB18030");
        String usercode = request.getParameter("usercode");
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpswd");
        String orgtype = request.getParameter("orgtype");
        String pageNo = request.getParameter("pageNo");

        String sql = "update t_user set username = ?, userpwd = ?, orgtype = ? where usercode = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,userpwd);
            ps.setString(3,orgtype);
            ps.setString(4,usercode);
            count = ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (count == 1) {
            response.sendRedirect("/controller/user/query?pageNo=" + pageNo);
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
