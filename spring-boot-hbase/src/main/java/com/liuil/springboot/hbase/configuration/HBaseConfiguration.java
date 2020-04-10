package com.liuil.springboot.hbase.configuration;

import lombok.Data;
import org.apache.commons.collections.MapUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * HBase配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "hbase", ignoreInvalidFields = true)
public class HBaseConfiguration {
    /**
     * HBase配置, 从yml中注入
     */
    private Map<String, String> config = new HashMap<>(16);

    @Bean
    public HbaseTemplate hbaseTemplate() {
        if (MapUtils.isEmpty(config)) {
            throw new IllegalArgumentException("HBase config is empty");
        }
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();

        config.entrySet().stream().forEach(e -> configuration.set(e.getKey(), e.getValue()));

        return new HbaseTemplate(configuration);
    }


}
