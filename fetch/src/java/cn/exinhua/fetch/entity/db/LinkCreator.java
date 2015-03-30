/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

 
import cn.exinhua.fetch.entity.Link;
import com.kamike.db.GenericCreator;
import com.kamike.db.Transaction;
import com.kamike.db.generic.GenericCreate;
 

/**
 *
 * @author THiNk
 */
public class LinkCreator extends GenericCreator<Link> {

    public LinkCreator(Transaction ts) {
        super(ts);
    }

    @Override
    public GenericCreate<Link> newCreate() {
        return new LinkCreate();
    }

}
