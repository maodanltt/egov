package com.tywh.egov.basicinfo.controller;

import com.tywh.egov.bean.Invest;
import com.tywh.egov.utils.ConfigUtil;
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
       // request.setCharacterEncoding("GB18030");
        String invregnum = request.getParameter("invregnum");
        String invname = request.getParameter("invname");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        PageModel<Invest> pageModel = new PageModel<>(request.getParameter("pageNo"));
        List<String> paramList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select invregnum,invname,regdate,cty,usercode from t_invest where 1=1");
        StringBuilder totalRecordsSql = new StringBuilder("select count(*) from t_invest where 1=1");

        if (ConfigUtil.isNotEmpty(invregnum)) {
            sql.append(" and invregnum = ?");
            totalRecordsSql.append(" and invregnum = ?");
            paramList.add(invregnum);
        }

        if (ConfigUtil.isNotEmpty(invname)) {
            sql.append(" and invname like ?");
            totalRecordsSql.append(" and invname like ?");
            paramList.add("%" + invname + "%");
        }

        if (ConfigUtil.isNotEmpty(startdate)) {
            sql.append(" and regdate >= ?");
            totalRecordsSql.append(" and regdate >= ?");
            paramList.add(startdate);
        }

        if (ConfigUtil.isNotEmpty(enddate)) {
            sql.append(" and regdate <= ?");
            totalRecordsSql.append(" and regdate <= ?");
            paramList.add(enddate);
        }
        sql.append(" LIMIT ?,?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
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


            ps = conn.prepareStatement(totalRecordsSql.toString());
            for (int i=0; i<paramList.size(); i++) {
                ps.setString(i+1,paramList.get(i));
            }
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
        request.getRequestDispatcher(request.getParameter("forward")).forward(request, response);
    }


//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }
}
