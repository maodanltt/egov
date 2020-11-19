package com.tywh.egov.system.controller;

import com.tywh.egov.bean.User;
import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserQueryController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = 3;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        int totcalRecods = 0;
        int totalPages = 0;
        try {

            conn = DbUtil.getConnection();
            String sql = "select usercode,username,userpwd,orgtype,regdate from t_user ORDER BY regdate desc limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pageNo - 1);
            ps.setInt(2,pageSize);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUsercode(rs.getString("usercode"));
                user.setUsername(rs.getString("username"));
                user.setUserpwd(rs.getString("userpwd"));
                user.setOrgtype(rs.getString("orgtype"));
                user.setRegdate(rs.getString("regdate"));

                userList.add(user);
            }

            sql = "select count(*) from t_user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                totcalRecods = rs.getInt(1);
            }
            totalPages = totcalRecods % pageSize == 0 ? totcalRecods / pageSize : totcalRecods / pageSize + 1;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        request.setAttribute("pageNo",pageNo);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("totcalRecods",totcalRecods);
        request.setAttribute("userList",userList);
        request.setAttribute("totalPages",totalPages);
        request.getRequestDispatcher("/system/user.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
