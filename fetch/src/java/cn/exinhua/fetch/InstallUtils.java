/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch;

import com.kamike.config.SystemConfig;
import com.kamike.db.DbInst;
import com.kamike.db.GenericCreator;
import com.kamike.db.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THiNk
 */
public class InstallUtils {

    public static boolean createDatabase(Transaction ts,String dbName) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("CREATE DATABASE  IF  NOT EXISTS `");
        buffer.append(dbName);
        buffer.append("` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;");

        if (ts == null) {
            return false;
        }
       
        int success = 0;

        PreparedStatement ps = null;

        try {

            ps = ts.preparedStatement(buffer.toString());

            success = ps.executeUpdate();

        } catch (Exception ex) {

            ts.rollback();
            Logger.getLogger(GenericCreator.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            try {

                if (ps != null) {
                    ps.close();
                    ps = null;
                }

            } catch (SQLException ex) {
                Logger.getLogger(GenericCreator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return true;

    }

    public static boolean existTable(String tableName) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        boolean ret = false;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement("SHOW TABLES LIKE '%" + tableName + "%';", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = ps.executeQuery();

            if (rs.next()) {
                ret = true;
            }

        } catch (Exception ex) {
            ret = false;
            Logger.getLogger(InstallUtils.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(InstallUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;
    }

    public static boolean existDatabase(String dbName) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        boolean ret = false;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement("show databases LIKE '%" + dbName + "%';", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = ps.executeQuery();

            if (rs.next()) {
                ret = true;
            }

        } catch (Exception ex) {
            ret = false;
            Logger.getLogger(InstallUtils.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(InstallUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;
    }
}
