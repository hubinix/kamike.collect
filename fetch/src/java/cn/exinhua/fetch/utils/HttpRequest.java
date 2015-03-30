/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.utils;

import cn.exinhua.fetch.entity.Link;
import com.kamike.config.SystemConfig;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Base64;

/**
 *
 * @author Brin
 */
public class HttpRequest {

    private Proxy proxy = null;

    private String userAgent = null;
    private String cookie = null;
    protected int depth;
    protected boolean auth = false;
    protected String username;
    protected String password;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public HttpResponse getResponse(Link link) throws Exception {
        URL url = new URL(link.getUrl());
        HttpResponse response = new HttpResponse(url);
        HttpURLConnection con;

        if (proxy == null) {
            con = (HttpURLConnection) url.openConnection();
        } else {

            con = (HttpURLConnection) url.openConnection(proxy);
            if (auth) {
                //格式如：  "Proxy-Authorization"= "Basic Base64.encode(user:password)" 
                String headerKey = "Proxy-Authorization";
                String authString = "username" + ":" + "password";
                String headerValue = "Basic " + Base64.getEncoder().encode(authString.getBytes());
                con.setRequestProperty(headerKey, headerValue);
            }
        }

        con.setDoInput(true);
        con.setDoOutput(true);

        if (userAgent != null) {
            con.setRequestProperty("User-Agent", userAgent);
        }
        if (cookie != null) {
            con.setRequestProperty("Cookie", cookie);
        }

        InputStream is;

        response.setCode(con.getResponseCode());

        is = con.getInputStream();

        byte[] buf = new byte[2048];
        int read;
        int sum = 0;
        int maxsize = SystemConfig.FETCH_MAX_SIZE;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((read = is.read(buf)) != -1) {
            if (maxsize > 0) {
                sum = sum + read;
                if (sum > maxsize) {
                    read = maxsize - (sum - read);
                    bos.write(buf, 0, read);
                    break;
                }
            }
            bos.write(buf, 0, read);
        }

        is.close();

        response.setContent(bos.toByteArray());
        response.setHeaders(con.getHeaderFields());
        bos.close();
        return response;
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
     * 返回代理
     *
     * @return 代理
     */
    public Proxy getProxy() {
        return proxy;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
