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
 * 抓取任务
 *
 * @author Brin
 */
@TableName("fetch_fetch")
public class Fetch {

    @Id
    @FieldName("id")
    @FieldLength(64)
    protected String id;

    @FieldName("fetched")
    protected boolean fetched;
    @FieldName("expired")
    protected boolean expired;
    @FieldName("create_date")
    protected Date createDate;

    @FieldName("depth")
    protected int depth;
    
    @FieldName("url")
    @FieldLength(255)
    protected String url;
    
     @FieldName("title")
    @FieldLength(255)
    protected String title;

    /**
     * 获取爬取任务的url
     *
     * @return 爬取任务的url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置爬取任务的url
     *
     * @param url 爬取任务的url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the fetched
     */
    public boolean isFetched() {
        return fetched;
    }

    /**
     * @param fetched the fetched to set
     */
    public void setFetched(boolean fetched) {
        this.fetched = fetched;
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
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the expired
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * @param expired the expired to set
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

}
