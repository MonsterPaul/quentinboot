package com.quentin.example.utils.mail;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用的SMTP服务器
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
@SuppressWarnings("serial")
public interface SmtpServer {

    Map<String, String> SMTP_163 = new HashMap<String, String>() {
        {

            put("smtp", "smtp.163.com");
            put("port", "25");

        }
    };

    Map<String, String> SMTP_SINA = new HashMap<String, String>() {
        {

            put("smtp", "smtp.sina.com");
            put("port", "25");

        }
    };

    Map<String, String> SMTP_SOHU = new HashMap<String, String>() {
        {

            put("smtp", "smtp.sohu.com");
            put("port", "25");

        }
    };

    Map<String, String> SMTP_126 = new HashMap<String, String>() {
        {

            put("smtp", "smtp.126.com");
            put("port", "25");

        }
    };

    Map<String, String> SMTP_YAHOO = new HashMap<String, String>() {
        {

            put("smtp", "smtp.mail.yahoo.com");
            put("port", "25");

        }
    };

    Map<String, String> SMTP_FOXMAIL = new HashMap<String, String>() {
        {

            put("smtp", "smtp.foxmail.com");
            put("port", "25");

        }
    };

}
