package com.example.influx.config;


import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shen
 * @Description TODO
 * @createTime 2022-06-10
 */
@Configuration
public class InfluxDBConfig {
    @Value("${spring.influx.url:''}")
    private String influxUrl;
    @Value("${spring.influx.user:''}")
    private String influxUser;
    @Value("${spring.influx.password:''}")
    private String influxPwd;
    @Value("${spring.influx.token:''}")
    private String influxToken;
    @Value("${spring.influx.org:''}")
    private String influxOrg;
    @Value("${spring.influx.bucket:''}")
    private String influxBucket;

    @Bean
    public InfluxDBClient createClient(){
        InfluxDBClient influxDBClient =
                InfluxDBClientFactory.create(influxUrl, influxToken.toCharArray(), influxOrg,influxBucket);
        return influxDBClient;
    }
}
