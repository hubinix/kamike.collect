/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

 
import cn.exinhua.fetch.entity.Fetch;
import com.kamike.db.GenericCreator;
import com.kamike.db.Transaction;
import com.kamike.db.generic.GenericCreate;
 

/**
 *
 * @author THiNk
 */
public class FetchCreator extends GenericCreator<Fetch> {

    public FetchCreator(Transaction ts) {
        super(ts);
    }

    @Override
    public GenericCreate<Fetch> newCreate() {
        return new FetchCreate();
    }

}
