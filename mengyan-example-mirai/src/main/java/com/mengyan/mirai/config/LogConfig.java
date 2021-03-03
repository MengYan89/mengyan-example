package com.mengyan.mirai.config;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

/**
 * log4j2配置类
 */
public class LogConfig {
    static {
        BufferedInputStream in = new BufferedInputStream(LogConfig.class.getClassLoader().getResourceAsStream("log4j2.xml"));
        final ConfigurationSource source;
        try {
            source = new ConfigurationSource(in);
            Configurator.initialize(null, source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
