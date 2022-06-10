package com.example.influx.monitor;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Shen
 * @Description TODO
 * @createTime 2022-06-10
 */
@Service
@Slf4j
//@Builder
@AllArgsConstructor
public class Monitor {

    private InfluxDBClient influxDBClient;

    @Scheduled(fixedRate = 5000)
    public void writeQPS(){
        log.info("start");
        int count = (int)(Math.random()*100);
        Point point = Point.measurement("ApiQPS")
                .addField("count",count)
                .addField("name","hello")
//                .addTag("标签1","标签1-value")
//                .addTag("标签2","标签2-value")
                .time(System.currentTimeMillis(), WritePrecision.MS);
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        writeApi.writePoint(point);
//        influxDBClient.close();
        log.info("influxDB 插入统计数据");
    }
}
