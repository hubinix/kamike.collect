/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

 
 
import cn.exinhua.fetch.entity.Snapshot;
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
public class SnapshotReader extends BaseReader<Snapshot> {

    @Override
    public GenericSelect<Snapshot> createSelect() {
        return new SnapshotSelect();
    }

    @Override
    public GenericSelect<Snapshot> createSelect(Snapshot t) {
        return new SnapshotSelect(t);
    }

    @Override
    public ArrayList<Snapshot> find(Snapshot t) {
        GenericSelect<Snapshot> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        ArrayList<Snapshot> ret = null;
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
                Logger.getLogger(SnapshotReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

   
     public Snapshot getByUrl(String url) {
        GenericSelect<Snapshot> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        Snapshot ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where  t.url=? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, url);

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
                Logger.getLogger(SnapshotReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
    
     public Snapshot getById(String id) {
        GenericSelect<Snapshot> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        Snapshot ret = null;
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
                Logger.getLogger(SnapshotReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
     
    

}
