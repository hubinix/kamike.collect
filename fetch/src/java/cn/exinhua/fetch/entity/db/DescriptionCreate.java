/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

import cn.exinhua.fetch.entity.Description;
import com.kamike.db.generic.GenericCreate;
 

/**
 *
 * @author THiNk
 */
public class DescriptionCreate extends GenericCreate<Description> {

    @Override
    public Description create() {
        return new Description();
    }

}
