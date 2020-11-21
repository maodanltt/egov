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

public class UserQueryByCodeController extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usercode = request.getParameter("usercode");
        String pageNo = request.getParameter("pageNo");
        String sql = "select usercode, username, userpwd, orgtype from t_user where usercode = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usercode);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsercode(rs.getString("usercode"));
                user.setUsername(rs.getString("username"));
                user.setUserpwd(rs.getString("userpwd"));
                user.setOrgtype(rs.getString("orgtype"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/system/userUpdate.jsp?pageNo=" + pageNo).forward(request, response);
    }
}
