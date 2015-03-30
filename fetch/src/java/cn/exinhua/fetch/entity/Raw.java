/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.exinhua.fetch.entity;

import java.util.List;

/**
 *
 * @author Brin
 */
public class Raw {
    protected Content content;
    protected Description description;
    protected List<Link> links;

    /**
     * @return the content
     */
    public Content getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * @return the description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * @return the links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
