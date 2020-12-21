package com.tywh.egov.basicinfo.controller;

import com.tywh.egov.bean.User;
import com.tywh.egov.utils.DateUtil;
import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class EnterAddController extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orgcode = request.getParameter("orgcode");
        String regno = request.getParameter("regno");
        String cnname = request.getParameter("cnname");
        String enname = request.getParameter("enname");
        String contactman = request.getParameter("contactman");
        String contacttel = request.getParameter("contacttel");
        double regcap = Double.parseDouble(request.getParameter("regcap"))   ;
        double outregcap = Double.parseDouble(request.getParameter("outregcap"));
        String regcry = request.getParameter("regcry");
        String usercode = ((User)request.getSession(false).getAttribute("user")).getUsercode();
        String regdate = DateUtil.format(new Date(),"yyyy-MM-dd");
        String[] invregnums = request.getParameterValues("invregnum");
        String[] regcap_details = request.getParameterValues("regcap_detail");
        String[] scales = request.getParameterValues("scale");

        String sql = "insert into t_enterprise (orgcode,regno,cnname,enname,contactman,contacttel,regcap,outregcap,regcry,usercode,regdate) values(?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        int count_detail = 0;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,orgcode);
            ps.setString(2,regno);
            ps.setString(3,cnname);
            ps.setString(4,enname);
            ps.setString(5,contactman);
            ps.setString(6,contacttel);
            ps.setDouble(7,regcap);
            ps.setDouble(8,outregcap);
            ps.setString(9,regcry);
            ps.setString(10,usercode);
            ps.setString(11,regdate);
            count = ps.executeUpdate();

            sql = "insert into t_en_inv(orgcode,invregnum,regcap,scale) values (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            for(int i=0; i<invregnums.length; i++) {
                String invregnum = invregnums[i];
                regcap = Double.parseDouble(regcap_details[i]);
                double scale = Double.parseDouble(scales[i]);
                ps.setString(1,orgcode);
                ps.setString(2,invregnum);
                ps.setDouble(3,regcap);
                ps.setDouble(4,scale);
                count_detail = count_detail + ps.executeUpdate();
            }
            conn.commit();
        } catch(Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbUtil.close(conn, ps, null);
        }

        if (count ==1 && count_detail == invregnums.length) {
            response.sendRedirect("/foreignExchange/newInputOrg.jsp");
        }
    }
}
