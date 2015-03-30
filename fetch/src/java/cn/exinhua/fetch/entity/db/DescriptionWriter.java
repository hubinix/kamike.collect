/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.exinhua.fetch.entity.db;

 
import cn.exinhua.fetch.entity.Description;
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
public class DescriptionWriter extends GenericWriter<Description>{
     public DescriptionWriter(Transaction ts) {
        super(ts);
    }
     public boolean deleteByContentId(String contentId) {
        if (ts == null) {
            return false;
        }
        // String uuid= UUID.randomUUID().toString();
        
        int success = 0;
        
        PreparedStatement ps = null;
        
        try {
            
            ps = ts.preparedStatement("delete from fetch_content where content_id=?");
            ps.setString(1, contentId);
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
