package com.tywh.egov.basicinfo.controller;

import com.tywh.egov.bean.Invest;
import com.tywh.egov.utils.DbUtil;
import com.tywh.egov.utils.PageModel;

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

public class InvestQueryController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sql = "select invregnum,invname,regdate,cty,usercode from t_invest limit ?,?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PageModel<Invest> pageModel = new PageModel<>(request.getParameter("pageNo"));

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,(pageModel.getPageNo() - 1) * pageModel.getPageSize());
            ps.setInt(2,pageModel.getPageSize());
            rs = ps.executeQuery();
            while(rs.next()) {
                Invest invest = new Invest();
                invest.setInvregnum(rs.getString("invregnum"));
                invest.setInvname(rs.getString("invname"));
                invest.setRegdate(rs.getString("regdate"));
                invest.setCty(rs.getString("cty"));
                invest.setUsercode(rs.getString("usercode"));
                pageModel.getDataList().add(invest);
            }

            sql = "select count(*) from t_invest";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                pageModel.setTotalRecords(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("/basicinfo/exoticOrgList.jsp").forward(request, response);
    }
}
