/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

 
 
import cn.exinhua.fetch.entity.Fetch;
import com.kamike.db.DbInst;
import com.kamike.db.generic.BaseReader;
import com.kamike.db.generic.GenericSelect;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THiNk
 */
public class FetchReader extends BaseReader<Fetch> {

    @Override
    public GenericSelect<Fetch> createSelect() {
        return new FetchSelect();
    }

    @Override
    public GenericSelect<Fetch> createSelect(Fetch t) {
        return new FetchSelect(t);
    }

    @Override
    public ArrayList<Fetch> find(Fetch t) {
        GenericSelect<Fetch> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        ArrayList<Fetch> ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where t.url=? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, t.getUrl());
        
            rs = ps.executeQuery();
            ret = select.fetch(rs);

        } catch (Exception e) {
            ret = new ArrayList<>();
            System.out.println(this.getClass().getName() + e.toString());

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
                Logger.getLogger(FetchReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

   
     public Fetch getById(String id) {
        GenericSelect<Fetch> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        Fetch ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where  t.id=? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, id);

            rs = ps.executeQuery();
            ret = select.fetchOnce(rs);

        } catch (Exception e) {
            ret = null;
            System.out.println(this.getClass().getName() + e.toString());

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
                Logger.getLogger(FetchReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
    
     public Fetch getByTitle(String title) {
        GenericSelect<Fetch> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        Fetch ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where  t.title=? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, title);

            rs = ps.executeQuery();
            ret = select.fetchOnce(rs);

        } catch (Exception e) {
            ret = null;
            System.out.println(this.getClass().getName() + e.toString());

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
                Logger.getLogger(FetchReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
     
    

}
