package com.mengyan.tc.config;

import com.mengyan.tc.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdWorkerConfiguration {

    // 二进制5位的工作机器ID
    @Value("${system.workId}")
    private long workId;
    // 二进制5位的数据中心ID
    @Value("${system.dateSource}")
    private long dateSource;

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(workId, dateSource);
    }

}
