package com.tywh.egov.basicinfo.controller;

import com.tywh.egov.bean.User;
import com.tywh.egov.utils.DateUtil;
import com.tywh.egov.utils.DbUtil;
import com.tywh.egov.utils.NumberUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.UUID;


public class InvestAddController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("GB18030");
      //  String invregnum =  UUID.randomUUID().toString().replace("-","");
        String invregnum = String.valueOf(NumberUtil.getCurrentNumber("invregnum") + 1);
        String invname = request.getParameter("invname");
        String cty = request.getParameter("cty");
        String orgcode = request.getParameter("orgcode");
        String contactman = request.getParameter("contactman");
        String contacttel = request.getParameter("contacttel");
        String email = request.getParameter("email");
        String remark = request.getParameter("remark");

        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        String usercode = user.getUsercode();

        String regdate = DateUtil.format(new Date(),"yyyy-MM-dd");

        String sql = "insert into t_invest() values(?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, invregnum);
            ps.setString(2, invname);
            ps.setString(3, cty);
            ps.setString(4, orgcode);
            ps.setString(5, contactman);
            ps.setString(6, contacttel);
            ps.setString(7, email);
            ps.setString(8, remark);
            ps.setString(9, usercode);
            ps.setString(10, regdate);
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, null);
        }

        if (count == 1) {
            response.sendRedirect("/invest/query");
        }

    }
}
