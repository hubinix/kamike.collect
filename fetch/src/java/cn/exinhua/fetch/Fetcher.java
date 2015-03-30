/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch;

import cn.exinhua.fetch.entity.Fetch;
import cn.exinhua.fetch.entity.Link;
import cn.exinhua.fetch.entity.db.LinkReader;
import cn.exinhua.fetch.entity.db.LinkWriter;
import cn.exinhua.fetch.utils.HttpRequest;
import cn.exinhua.fetch.utils.RegexRule;
import com.kamike.db.Transaction;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 *
 * @author Brin
 */
public class Fetcher {

    protected int status;

    protected boolean resumable = false;

    protected RegexRule regexRule = new RegexRule();
    protected ArrayList<String> seeds = new ArrayList<String>();
    protected Fetch fetch;
    
    protected String proxyUrl="127.0.0.1";
    protected int proxyPort=4444;
    protected String proxyUsername="hkg";
    protected String proxyPassword="dennis";
    protected boolean proxyAuth=false;
    

    private static final ReentrantLock lock = new ReentrantLock();

    public Fetcher(Fetch fetch) {
        this.fetch = fetch;
    }

    /**
     * 开始深度为depth的爬取
     *
     * @param depth 深度
     * @throws Exception
     */
    public void start() throws Exception {

        if (regexRule.isEmpty()) {
            Logger.getLogger(Fetcher.class.getName()).info("error:Please add at least one positive regex rule");

            return;
        }
        //先抓好第一次

        List<Link> links = null;
        LinkReader linkReader = new LinkReader();
        //然后再开始抓
        HttpRequest request = createRequest();
        long left = linkReader.countUnfetchedByRoot(fetch.getUrl());
        long max = 10000000;//最多找1000万个网页
        long count = 0;
        while (left > 0 && count < max) {
            count++;
            lock.lock();

            try {

                links = linkReader.findByRoot(fetch.getUrl(), 0, 100);
                Transaction ts = new Transaction();
                LinkWriter writer = new LinkWriter(ts);
                for (Link link : links) {
                    link.setFetched(true);
                    writer.edit(link);
                }

                ts.save();

            } finally {
                lock.unlock();
            }

            if (links != null) {
                for (Link link : links) {
                    FetchUtils.fetch(request, regexRule, link);
                    
                }
            }
            if( !FetchInst.getInstance().running)
            {
                break;
            }
        }

    }

    /**
     * 添加一个正则过滤规则
     *
     * @param regex 正则过滤规则
     */
    public void addRegex(String regex) {
        regexRule.addRule(regex);
    }

    public RegexRule getRegexRule() {
        return regexRule;
    }

    public void setRegexRule(RegexRule regexRule) {
        this.regexRule = regexRule;
    }

    private String cookie = null;
    private String useragent = "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0";

    private boolean isContentStored = true;
    private Proxy proxy = null;

    /**
     * 根据url生成Request(http请求)的方法，可以通过Override这个方法来自定义Request
     *
     * @param
     * @return 实现Request接口的对象
     * @throws Exception
     */
    public HttpRequest createRequest() throws Exception {
        HttpRequest request = new HttpRequest();

        request.setProxy(proxy);
        request.setUserAgent(useragent);
        request.setCookie(cookie);
        request.setDepth(fetch.getDepth());
        return request;
    }

    /**
     * 返回User-Agent
     *
     * @return User-Agent
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * 设置User-Agent
     *
     * @param useragent
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    /**
     * 返回是否存储网页/文件的内容
     *
     * @return 是否存储网页/文件的内容
     */
    public boolean getIsContentStored() {
        return isContentStored;
    }

    /**
     * 设置是否存储网页／文件的内容
     *
     * @param isContentStored 是否存储网页/文件的内容
     */
    public void setIsContentStored(boolean isContentStored) {
        this.isContentStored = isContentStored;
    }

    /**
     * 返回代理
     *
     * @return 代理
     */
    public Proxy getProxy() {
        return proxy;
    }

    /**
     * 设置代理
     *
     * @param proxy 代理
     */
    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    /**
     * 返回Cookie
     *
     * @return Cookie
     */
    public String getCookie() {
        return cookie;
    }

    /**
     * 设置http请求的cookie
     *
     * @param cookie Cookie
     */
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     * @return the fetch
     */
    public Fetch getFetch() {
        return fetch;
    }

    /**
     * @param fetch the fetch to set
     */
    public void setFetch(Fetch fetch) {
        this.fetch = fetch;
    }

    /**
     * @return the proxyUrl
     */
    public String getProxyUrl() {
        return proxyUrl;
    }

    /**
     * @param proxyUrl the proxyUrl to set
     */
    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    /**
     * @return the proxyPort
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * @param proxyPort the proxyPort to set
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * @return the proxyUsername
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    /**
     * @param proxyUsername the proxyUsername to set
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    /**
     * @return the proxyPassword
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * @param proxyPassword the proxyPassword to set
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    /**
     * @return the proxyAuth
     */
    public boolean isProxyAuth() {
        return proxyAuth;
    }

    /**
     * @param proxyAuth the proxyAuth to set
     */
    public void setProxyAuth(boolean proxyAuth) {
        this.proxyAuth = proxyAuth;
    }

}
