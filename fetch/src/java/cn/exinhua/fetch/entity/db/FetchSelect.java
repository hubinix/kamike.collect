/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

import cn.exinhua.fetch.entity.Fetch;
import com.kamike.db.generic.GenericSelect;

/**
 *
 * @author THiNk
 */
public class FetchSelect extends GenericSelect<Fetch> {

    public FetchSelect() {
        super();
    }

    public FetchSelect(Fetch user) {
        super(user);
    }

    @Override
    public Fetch create() {
        return new Fetch();
    }

}
