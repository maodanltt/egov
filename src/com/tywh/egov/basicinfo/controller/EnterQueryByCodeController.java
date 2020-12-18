package com.tywh.egov.basicinfo.controller;

import com.tywh.egov.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnterQueryByCodeController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orgcode = request.getParameter("orgcode");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exist = false;
        String sql = "select orgcode,regno,cnname,enname,contactman,contacttel,regcap,outregcap,regcry from t_enterprise where orgcode = ?";

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, orgcode);
            rs = ps.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        if (exist) {
            String msg = "组织机构代码已存在";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/foreignExchange/newInputOrg.jsp").forward(request, response);
        } else {
            request.setAttribute("orgcode",orgcode);
            request.getRequestDispatcher("/foreignExchange/inputOrgInfo.jsp").forward(request, response);
        }
    }
}
