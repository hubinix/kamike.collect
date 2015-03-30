/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamike.boot;
 
import cn.exinhua.fetch.FetchInst;
import com.kamike.config.SystemConfig;
import com.kamike.config.SystemParam;

 
import com.kamike.watch.DeleteWatcher;
import com.kamike.watch.EventInst;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author THiNk
 */
public class HtmlServletListener implements ServletContextListener {

    private DeleteWatcher deleteWatcher;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SystemConfig.SYSTEM_PATH = sce.getServletContext().getRealPath("/");

        if (SystemConfig.SYSTEM_PATH == null) {
            String webPath = this.getClass().getResource("/").getPath().replaceAll("^\\/", "");
            webPath = webPath.replaceAll("[\\\\\\/]WEB-INF[\\\\\\/]classes[\\\\\\/]?", "/");
            webPath = webPath.replaceAll("[\\\\\\/]+", "/");
            webPath = webPath.replaceAll("%20", " ");

            if (webPath.matches("^[a-zA-Z]:.*?$")) {

            } else {
                webPath = "/" + webPath;
            }

            webPath += "/";
            webPath = webPath.replaceAll("[\\\\\\/]+", "/");
            SystemConfig.SYSTEM_PATH = webPath;

        }
        SystemConfig.getInstance().init(SystemConfig.SYSTEM_PATH + "/WEB-INF/config.ini");
        SystemConfig.DB_IP = SystemConfig.getInstance().getParameter(SystemParam.DB_IP);
        SystemConfig.DB_NAME = SystemConfig.getInstance().getParameter(SystemParam.DB_NAME);
        SystemConfig.DB_PASS = SystemConfig.getInstance().getParameter(SystemParam.DB_PASS);
        SystemConfig.DB_USER = SystemConfig.getInstance().getParameter(SystemParam.DB_USER);
        SystemConfig.DB_PORT = SystemConfig.getInstance().getParameter(SystemParam.DB_PORT);
        SystemConfig.SYS_NAME = SystemConfig.getInstance().getParameter(SystemParam.SYS_NAME);
        SystemConfig.INSTALL = SystemConfig.getInstance().getParameter(SystemParam.INSTALL);
        SystemConfig.SECURE_PASS = SystemConfig.getInstance().getParameter(SystemParam.SECURE_PASS);
        SystemConfig.WATCH_PATH = SystemConfig.getInstance().getParameter(SystemParam.WATCH_PATH);
        SystemConfig.BUCKET_SIZE = SystemConfig.getInstance().getParameter(SystemParam.BUCKET_SIZE);
        SystemConfig.FS_PREFIX = SystemConfig.getInstance().getParameter(SystemParam.FS_PREFIX);

        FetchInst.getInstance();
        //       String path = request.getSession().getServletContext().getRealPath("/path");
        deleteWatcher = new DeleteWatcher(EventInst.getInstance().getAsyncEventBus(),
                Paths.get(SystemConfig.WATCH_PATH));
        try {
            deleteWatcher.start();

        } catch (IOException ex) {
            Logger.getLogger(HtmlServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (deleteWatcher != null) {
            deleteWatcher.close();
        }
    }
}
