/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity.db;

import cn.exinhua.fetch.entity.Snapshot;
import com.kamike.db.generic.GenericSelect;

/**
 *
 * @author THiNk
 */
public class SnapshotSelect extends GenericSelect<Snapshot> {

    public SnapshotSelect() {
        super();
    }

    public SnapshotSelect(Snapshot user) {
        super(user);
    }

    @Override
    public Snapshot create() {
        return new Snapshot();
    }

}
