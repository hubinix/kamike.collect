/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamike.misc;

import com.kamike.config.SystemConfig;
import com.kamike.config.SystemParam;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author THiNk
 */
public class MailUtils {

    public static void sendSystemMail(String email, String msg) {
        SimpleEmail mail = new SimpleEmail();
        if (SystemConfig.getInstance().getParameter(SystemParam.SYSTEM_EMAIL_SMTP) == null
                && SystemConfig.getInstance().getParameter(SystemParam.SYSTEM_EMAIL) == null
                && SystemConfig.getInstance().getParameter(SystemParam.SYSTEM_EMAIL_ACCOUNT) == null
                && SystemConfig.getInstance().getParameter(SystemParam.SYSTEM_EMAIL_PASSWORD) == null) {
            //有一个没有配置，说明不想发邮件
            return;
        }
        try {
            mail.setHostName(SystemConfig.getInstance().getParameter(SystemParam.SYSTEM_EMAIL_SMTP));
            mail.addTo(email, email);
            mail.setFrom(SystemConfig.getInstance().getParameter(SystemParam.SYSTEM_EMAIL), "资产管理系统");
            mail.setAuthentication(SystemConfig.getInstance().getParameter(SystemParam.SYSTEM_EMAIL_ACCOUNT), SystemConfig.getInstance().getParameter(SystemParam.SYSTEM_EMAIL_PASSWORD));
            mail.setSubject("来自资产管理系统的邮件");
            mail.setMsg(msg);
            String a = mail.send();
            System.out.println(a);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
