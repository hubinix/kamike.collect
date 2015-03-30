/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.exinhua.fetch.entity.db;

 
import cn.exinhua.fetch.entity.Link;
import com.kamike.db.GenericWriter;
import com.kamike.db.Transaction;
 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THiNk
 */
public class LinkWriter extends GenericWriter<Link>{
     public LinkWriter(Transaction ts) {
        super(ts);
    }
     public boolean deleteByUrl(String url) {
        if (ts == null) {
            return false;
        }
        // String uuid= UUID.randomUUID().toString();
        
        int success = 0;
        
        PreparedStatement ps = null;
        
        try {
            
            ps = ts.preparedStatement("delete from fetch_content where url=?");
            ps.setString(1, url);
            success = ps.executeUpdate();
            
        } catch (Exception e) {
            
            ts.rollback();
            System.out.println(this.getClass().getName() + e.toString());
            return false;
            
        } finally {
            try {
                
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(GenericWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return true;
    }
}
