/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity;

import com.kamike.db.generic.FieldLength;
import com.kamike.db.generic.FieldName;
import com.kamike.db.generic.Id;
import com.kamike.db.generic.TableName;
import java.util.Date;

/**
 *
 * @author Brin
 */
@TableName("fetch_snapshot")
public class Snapshot {

    @Id
    @FieldName("id")
    @FieldLength(64)
    protected String id;

    @FieldName("url")
    @FieldLength(1024)
    protected String url;
    
    @FieldName("fid")
    @FieldLength(64)
    protected String fid;
    
    @FieldName("name")
    @FieldLength(255)
    protected String name;
    
    @FieldName("create_date")
    protected Date createDate;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the fid
     */
    public String getFid() {
        return fid;
    }

    /**
     * @param fid the fid to set
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
