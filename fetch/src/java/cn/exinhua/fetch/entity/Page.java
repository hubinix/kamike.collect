/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.exinhua.fetch.entity;

import cn.exinhua.fetch.utils.HttpResponse;
import java.util.Date;
import org.jsoup.nodes.Document;

 
/**
 * Page是爬取过程中，内存中保存网页爬取信息的一个容器，与CrawlDatum不同，Page只在内存中存
 * 放，用于保存一些网页信息，方便用户进行自定义网页解析之类的操作。在广度遍历器中，用户覆盖
 * 的visit(Page page)方法，就是通过Page将网页爬取/解析信息传递给用户的。经过http请求、解
 * 析这些流程之后，page内保存的内容会越来越多。
 * @author hu
 */
public class Page{
    
    private String url=null;  
    private String html=null;
    private Document doc=null;
    protected int depth;
    protected Date createDate;
   
    
   

    /**
     * 返回网页的url
     * @return 网页的url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置网页的url
     * @param url 网页的url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 返回网页的源码字符串
     * @return 网页的源码字符串
     */
    public String getHtml() {
        return html;
    }

    /**
     * 设置网页的源码字符串
     * @param html 网页的源码字符串
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * 返回网页解析后的DOM树(Jsoup的Document对象)
     * @return 网页解析后的DOM树
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * 设置网页解析后的DOM树(Jsoup的Document对象)
     * @param doc 网页解析后的DOM树
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    

    /**
     * @return the step
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the step to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
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
