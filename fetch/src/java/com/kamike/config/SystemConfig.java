/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamike.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author brin
 */
public class SystemConfig {

    private static volatile SystemConfig ec = new SystemConfig();

    private final ConcurrentHashMap‎<SystemParam, String> systemParameter = new ConcurrentHashMap‎<>();

    private ConfigReader configReader;

    public static String WATCH_PATH = "E:/share/kami";
    public static String DOMAIN = "high.xinhuavideo.com";
    public static String SYS_NAME="xla";
    public static String FASP_PATH = "E:/high/fasp/bin/";
    public static String SYSDBNAME = "kamike";
    public static String THIS_DB_ID = "kamike";
    public static String SYSTEM_PATH = "";
    public static String BUCKET_SIZE="100000000000";
    public static String FS_PREFIX="C:/data";
    public static int FETCH_MAX_SIZE=10*1000*1000;

    public static String DB_USER = "root";
    public static String DB_PASS = "wlnwzkybd";
    public static String DB_IP = "127.0.0.1";
    public static String DB_PORT = "3306";
    public static String DB_NAME = "kamike";
    public static String SECURE_PASS = "wlnwzkybd";
    public static String INSTALL = "0";
  

    private final ReentrantLock lock = new ReentrantLock();

    private SystemConfig() {

    }

    public static SystemConfig getInstance() {
        return ec;
    }

    public void init(String configFile) {
        lock.lock();
        try {
            this.configReader = new ConfigReader(configFile);
            SystemParam[] params = SystemParam.values();
            
            for (SystemParam param : params) {
                systemParameter.put(param, configReader.getValue(param.toString().toLowerCase()));
            }

        } finally {
            lock.unlock();
        }

    }

    /**
     * @param param
     * @param name
     * @return the systemConstants
     */
    public String getParameter(SystemParam param) {
        return this.systemParameter.get(param);
    }

}
