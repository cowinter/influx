package com.example.influx.monitor;

import com.example.influx.annotation.LogMath;
import com.example.influx.domain.Temperature;
import com.example.influx.example.write.WriteDataNoBlocking;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.client.write.events.WriteErrorEvent;
import com.influxdb.client.write.events.WriteSuccessEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Shen
 * @Description The no-blocking WriteApi is supposed to be use as a singleton. Don't create a new instance for every write!
 * @createTime 2022-06-10
 */
@Service
@Slf4j
//@Builder
@AllArgsConstructor
public class Monitor {

    private InfluxDBClient influxDBClient;

    private static Random random ;
    {
        random = new Random();
    }

    @Scheduled(fixedRate = 1000)
    @LogMath(value = "writeApi",ratio = 100)
    public void writeQPS(){
        log.info("开始时间{}",Calendar.getInstance().getTime());
        try(WriteApi writeApi = influxDBClient.makeWriteApi()){
            writeApi.listenEvents(WriteSuccessEvent.class, event->{
                String data = event.getLineProtocol();
//                System.out.println("插入成功");
                System.out.println(data);
            });
            writeApi.listenEvents(WriteErrorEvent.class, writeErrorEvent -> {
                Throwable throwable = writeErrorEvent.getThrowable();
                System.out.println(throwable.getMessage());
            });
            //
            // Write by POJO
            //
            Temperature temperature = new Temperature();
            temperature.location = "south";
//            temperature.city = "shanghai";
//            temperature.valueInt = Integer.valueOf(random.nextInt(100));
//            temperature.value = Double.valueOf(random.nextInt(100));
            temperature.value = random.nextInt(100);
            temperature.time = Instant.now();

            writeApi.writeMeasurement(WritePrecision.NS, temperature);
        }
//        influxDBClient.close();
    }
}
