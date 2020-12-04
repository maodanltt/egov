package com.tywh.egov.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NumberUtil {

    private NumberUtil () {

    }

    public static Long getCurrentNumber(String name) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long currentNubmer = 0L;

        String sql = "select number from t_number where name = ? ";
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            if (rs.next()) {
                currentNubmer = rs.getLong("number");
            }

            updateCuurentNumber(conn, name, currentNubmer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        return currentNubmer;
    }

    private static void updateCuurentNumber(Connection conn, String name, Long currentNumber) throws Exception{

        PreparedStatement ps = null;

        String sql = "update t_number set number = ? where name = ?";
        ps = conn.prepareStatement(sql);
        ps.setLong(1, currentNumber + 1);
        ps.setString(2,name);
        ps.executeUpdate();
    }
}
