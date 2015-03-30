/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamike.misc;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

/**
 *
 * @author Brin
 */
public class MiscDateUtils {

    private static final String[] DATE_PATTERN = new String[]{"yyyy-MM", "yyyyMM", "yyyy/MM",
        "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
        "yyyyMMddHHmmss",
        "yyyy-MM-dd HH:mm:ss",
        "yyyy/MM/dd HH:mm:ss"};

    public static Date getDate(String value) {

        if ("".endsWith(value) || value == null) {
            return new Date(System.currentTimeMillis());
        }
        try {
            return DateUtils.parseDate(value, DATE_PATTERN);

        } catch (ParseException ex) {
            Logger.getLogger(MiscDateUtils.class.getName()).log(Level.SEVERE, null, ex);
            return new Date(System.currentTimeMillis());
        }
    }

    public static String getDate(Date now) {

        return FastDateFormat.getInstance("yyyyMMddHH").format(now);

    }
     public static String getDateTime(Date now) {

        return FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss").format(now);

    }
}
