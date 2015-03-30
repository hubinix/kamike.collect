/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

import cn.exinhua.fetch.entity.Content;
import com.kamike.db.generic.GenericSelect;

/**
 *
 * @author THiNk
 */
public class ContentSelect extends GenericSelect<Content> {

    public ContentSelect() {
        super();
    }

    public ContentSelect(Content user) {
        super(user);
    }

    @Override
    public Content create() {
        return new Content();
    }

}
