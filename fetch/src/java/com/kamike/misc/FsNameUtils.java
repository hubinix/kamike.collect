/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamike.misc;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;
import com.google.common.net.UrlEscapers;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.FastDateFormat;

/**
 *
 * @author THiNk
 */
public class FsNameUtils {
    
    public static String getName(String prefix, String disk, String filename, String fid, String uid) {
        String name = FilenameUtils.getName(filename);
        
        Date date = new Date(System.currentTimeMillis());
        String extension = FilenameUtils.getExtension(name);
        return getName(prefix, disk, getShortDate(date), name, fid, uid, extension);
    }
    
    public static String getIncomingName(String fileName) {
        String name = FilenameUtils.getFullPathNoEndSeparator(fileName);
        //
        int end = FilenameUtils.indexOfLastSeparator(name);
        String parent = name.substring(end);
        int lastEnd = FilenameUtils.indexOfLastSeparator(parent);
        return parent.substring(lastEnd, end);
    }
    
    public static ArrayList<String> getDescriptions(String fileName) {
        ArrayList<String> ret = new ArrayList<String>();
        String baseName = FilenameUtils.getBaseName(fileName);
        String escapedName = escapeName(baseName);
        
        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(escapedName);
        
        while (m.find()) {
            ret.add(m.group(1));
            
        }
        return ret;
    }
    
    public static String getLocalUrl(String disk, String fileName, String fid, String uid, Date date) {

        //同一盘下的桶目录
        String validPath = getName(disk, fileName, fid, uid, date);
        
        return FilenameUtils.getPath(validPath);
    }
    
    public static String getName(String disk, String filename, String fid, String uid, Date date) {
        String name = FilenameUtils.getName(filename);
        String prefix = FilenameUtils.getPrefix(filename);
        
        String extension = FilenameUtils.getExtension(filename);
        //同一盘下的桶目录
        return getName(prefix, disk,
                getShortDate(date), name, fid, uid, extension);
        
    }
    
    public static String getAttributeName(String prefix, String disk, String name,String readableName, String fid, String uid, Date date) {
        
        String extension = FilenameUtils.getExtension(name);
        //同一盘下的目录
        return getAttributeName(prefix, disk,
                getShortDate(date), name,readableName, fid, uid, extension);
        
    }

    public static String getAttributeName(String prefix, String disk, String date, String name,String readableName, String fid, String uid, String extension) {
        
        String escapedName = FilenameUtils.getBaseName(name);
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);
        buffer.append(disk);
        buffer.append('/');
        buffer.append(uid);
        buffer.append('/');
        buffer.append(date);
           buffer.append('/');
        buffer.append(fid);
        buffer.append("/[");

        //然后是工程id，项目id
        buffer.append(escapedName);
        buffer.append(']');
        buffer.append(readableName);
      
        
        return buffer.toString();
    }
    
    public static String getNameInZip(String prefix, String disk, String name, String fid, String uid, Date date) {
        
        String extension = FilenameUtils.getExtension(name);
        //同一盘下的目录
        return getNameInZip(prefix, disk,
                getShortDate(date), name, fid, uid, extension);
        
    }
    
    public static String getName(String prefix, String disk, String name, String fid, String uid, Date date) {
        
        String extension = FilenameUtils.getExtension(name);
        //同一盘下的目录
        return getName(prefix, disk,
                getShortDate(date), name, fid, uid, extension);
        
    }
    
    public static String getName(String disk, String filename, String fid, String uid) {
        String name = FilenameUtils.getName(filename);
        String prefix = FilenameUtils.getPrefix(filename);
        Date date = new Date(System.currentTimeMillis());
        String extension = FilenameUtils.getExtension(filename);
        return getName(prefix, disk, getShortDate(date), name, fid, uid, extension);
    }
    
    public static String escapeUrl(String name) {
        return UrlEscapers.urlFragmentEscaper().escape(name);
    }
    
    public static String escapeName(String name) {
        
        Escaper myEscaper = Escapers.builder()
                .addEscape('\'', "")
                .addEscape(' ', "")
                .addEscape('&', "")
                .addEscape('$', "")
                .addEscape('~', "")
                .addEscape('`', "")
                .addEscape('#', "")
                .addEscape('@', "")
                .addEscape('(', "")
                .addEscape('+', "")
                .addEscape(')', "")
                .addEscape('<', "")
                .addEscape('>', "")
                .addEscape('%', "")
                .addEscape('\"', "")
                .addEscape('.', "")
                .addEscape('=', "")
                .addEscape('-', "")
                .addEscape(';', "")
                .addEscape(':', "")
                .addEscape('?', "")
                .addEscape('*', "")
                .addEscape('|', "")
                .addEscape('\\', "")
                .addEscape('/', "")
                .build();
        
        return myEscaper.escape(name);
    }
    
    public static String getName(String prefix, String disk, String date, String name, String fid, String uid, String extension) {
        String baseName = FilenameUtils.getBaseName(name);
        String escapedName = escapeName(baseName);
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);
        buffer.append(disk);
        buffer.append('/');
        buffer.append(uid);
        buffer.append('/');
        buffer.append(date);
        buffer.append('/');
        //然后是工程id，项目id
        buffer.append(fid);
        buffer.append('/');
        buffer.append(escapedName);
        if (!"".equals(extension)) {
            buffer.append('.');
            buffer.append(extension);
        }
        
        return buffer.toString();
    }

    public static String getNameInZip(String prefix, String disk, String date, String name, String fid, String uid, String extension) {
        
        String escapedName = name;
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);
        buffer.append(disk);
        buffer.append('/');
        buffer.append(uid);
        buffer.append('/');
        buffer.append(date);
        buffer.append('/');
        //然后是工程id，项目id
        buffer.append(fid);
        
        buffer.append(escapedName);
        
        return buffer.toString();
    }
    
    public static String getDate(Date date) {
        
        return FastDateFormat.getInstance("yyyyMMddHHmmss").format(date);
        
    }
    
    public static String getShortDate(Date date) {
        
        return FastDateFormat.getInstance("yyyyMMdd").format(date);
        
    }
}
