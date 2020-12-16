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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String invregnum = request.getParameter("invregnum");
        String invname = request.getParameter("invname");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        PageModel<Invest> pageModel = new PageModel<>(request.getParameter("pageNo"));
        List<String> paramList = new ArrayList<>();
        String sql = "select invregnum,invname,regdate,cty,usercode from t_invest where 1=1";
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(sql);

        if (invregnum != "") {
            strBuilder.append(" and invregnum = ?");
            paramList.add(invregnum);
        }

        if (invname != "") {
            strBuilder.append(" and invname like ?");
            paramList.add("%" + invname + "%");
        }

        if (startdate != "") {
            strBuilder.append(" and startdate >= ?");
            paramList.add(startdate);
        }

        if (enddate != "") {
            strBuilder.append(" and enddate <= ?");
            paramList.add(enddate);
        }

        sql = strBuilder.append(" LIMIT ?,?").toString();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i=0; i<paramList.size(); i++) {
                ps.setString(i+1,paramList.get(i));
            }
            ps.setInt(paramList.size() + 1 ,(pageModel.getPageNo() - 1) * pageModel.getPageSize());
            ps.setInt(paramList.size() + 2,pageModel.getPageSize());
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
