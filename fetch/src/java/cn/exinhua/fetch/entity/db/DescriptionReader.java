/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

 
 
import cn.exinhua.fetch.entity.Description;
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
public class DescriptionReader extends BaseReader<Description> {

    @Override
    public GenericSelect<Description> createSelect() {
        return new DescriptionSelect();
    }

    @Override
    public GenericSelect<Description> createSelect(Description t) {
        return new DescriptionSelect(t);
    }

    @Override
    public ArrayList<Description> find(Description t) {
        GenericSelect<Description> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        ArrayList<Description> ret = null;
        try {
            conn = DbInst.getInstance().getDatabase().getSingleConnection();
            ps = conn.prepareStatement(select.rawSql() + "where t.content_id=? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, t.getContentId());
        
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
                Logger.getLogger(DescriptionReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

   
     public Description getById(String id) {
        GenericSelect<Description> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        Description ret = null;
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
                Logger.getLogger(DescriptionReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
    
     public Description getByTitle(String title) {
        GenericSelect<Description> select = createSelect();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;

        Description ret = null;
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
                Logger.getLogger(DescriptionReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
     
    

}
