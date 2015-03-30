/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.exinhua.fetch.entity;

import com.google.gson.Gson;
import java.util.Date;

/**
 *
 * @author Brin
 */

public class Word {
    
  
    
    protected String id;
    protected String meta;//词性
    protected String tag;//意义
    protected String name;//本体词义
    protected String text;//当前字符
    protected Date createDate;
    protected boolean subject;//是否是本体
    protected boolean target;//是否是目标

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
     * @return the meta
     */
    public String getMeta() {
        return meta;
    }

    /**
     * @param meta the meta to set
     */
    public void setMeta(String meta) {
        this.meta = meta;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
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
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
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
     * @return the subject
     */
    public boolean isSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(boolean subject) {
        this.subject = subject;
    }

    /**
     * @return the target
     */
    public boolean isTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(boolean target) {
        this.target = target;
    }
    
}
