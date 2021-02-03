package com.mengyan.mirai.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QQProperties {
    private static Properties properties = new Properties();
    private static Integer qq = null;
    private static String pwd = null;

    static {
        InputStream in = QQProperties.class.getClassLoader().getResourceAsStream("qq.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    in = null;
                }
            }
        }
        qq = Integer.valueOf(properties.getProperty("QQ"));
        pwd = properties.getProperty("PWD");
    }

    public static Integer getQQ() {
        return qq;
    }

    public static String getPwd() {
        return pwd;
    }

}
