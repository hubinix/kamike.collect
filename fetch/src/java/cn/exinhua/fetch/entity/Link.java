/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.kamike.db.generic.FieldLength;
import com.kamike.db.generic.FieldName;
import com.kamike.db.generic.Id;
import com.kamike.db.generic.TableName;
import java.util.Date;

/**
 *
 * @author Brin
 */
@TableName("fetch_link")
public class Link {

    @Id
    @FieldName("id")
    @FieldLength(64)
    protected String id;
    /**
     * 链接的锚文本
     */

    @FieldName("anchor")
    @FieldLength(255)
    protected String anchor;

    /**
     * 链接的url
     */
    @FieldName("url")
    @FieldLength(1024)
    protected String url;

    @FieldName("create_date")
    protected Date createDate;

    @FieldName("fetched")
    protected boolean fetched;

    @FieldName("depth")
    protected int depth;
    
    @FieldName("expired")
    protected boolean expired;

    @FieldName("root")
    @FieldLength(1024)
    protected String root;

    @FieldName("hash")
    @FieldLength(128)
    protected String hash;

    public Link() {

    }

    public Link(String anchor, String url) {
        this.anchor = anchor;
        this.url = url;
        this.hash = Hashing.md5().newHasher().putString(url, Charsets.UTF_8).hash().toString();
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.hash = Hashing.md5().newHasher().putString(url, Charsets.UTF_8).hash().toString();
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
     * @return the root
     */
    public String getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
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
