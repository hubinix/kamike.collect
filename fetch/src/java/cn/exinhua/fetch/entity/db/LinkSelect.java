/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

import cn.exinhua.fetch.entity.Link;
import com.kamike.db.generic.GenericSelect;

/**
 *
 * @author THiNk
 */
public class LinkSelect extends GenericSelect<Link> {

    public LinkSelect() {
        super();
    }

    public LinkSelect(Link user) {
        super(user);
    }

    @Override
    public Link create() {
        return new Link();
    }

}
