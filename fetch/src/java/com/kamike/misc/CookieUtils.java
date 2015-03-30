/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamike.misc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author THiNk
 */
public class CookieUtils {

    /**
     *
     * 获取COOKIE
     *
     *
     *
     * @param name
     *
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie ck : cookies) {

            if (StringUtils.equalsIgnoreCase(name, ck.getName())) {
                return ck;
            }
        }

        return null;

    }

    /**
     *
     * 获取COOKIE
     *
     *
     *
     * @param name
     *
     */
    public static String getCookieValue(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie ck : cookies) {

            if (StringUtils.equalsIgnoreCase(name, ck.getName())) {

            }
        }

        return null;

    }

   

    /**
     *
     * 设置COOKIE
     *
     *
     *
     * @param name
     *
     * @param value
     *
     * @param maxAge
     *
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name,
            String value, int maxAge) {

        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(maxAge);

        cookie.setPath("/");

        response.addCookie(cookie);

    }

    public static void deleteCookie(HttpServletRequest request,
            HttpServletResponse response, String name) {

        setCookie(request, response, name, "", 0);

    }

}
