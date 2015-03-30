/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

import cn.exinhua.fetch.entity.Snapshot;
import com.kamike.db.generic.GenericCreate;
 

/**
 *
 * @author THiNk
 */
public class SnapshotCreate extends GenericCreate<Snapshot> {

    @Override
    public Snapshot create() {
        return new Snapshot();
    }

}
