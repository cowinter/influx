package com.example.influx.example.write;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.events.WriteErrorEvent;
import com.influxdb.client.write.events.WriteSuccessEvent;

import java.time.Instant;

/**
 * @author Shen
 * @Description 异步非阻塞写
 * The WriteApi is supposed to be use as a singleton. Don't create a new instance for every write!
 * @createTime 2022-06-23
 */
public class WriteDataNoBlocking {
    private static char[] token = "3PI0dMFVNGb2AqXxjkBTXH0aXRUfzvO8g0Ks6K3MMsIytYq2SehcXbT_aaAK_5lwLDhfTc4VbfUns3uvGwctcw=="
            .toCharArray();
    private static String org = "org_02";
    private static String bucket = "bucket_02";

    public static void main(final String[] args) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://81.68.76.15:8086", token, org, bucket);

        //
        // Write data
        //
        try (WriteApi writeApi = influxDBClient.makeWriteApi()) {
            writeApi.listenEvents(WriteSuccessEvent.class,event->{
                String data = event.getLineProtocol();
                System.out.println("插入成功");
                System.out.println(data);
            });
            writeApi.listenEvents(WriteErrorEvent.class,writeErrorEvent -> {
                Throwable throwable = writeErrorEvent.getThrowable();
                System.out.println(throwable.getMessage());
            });

            //
            // Write by POJO
            //
            Temperature temperature = new Temperature();
            temperature.location = "south";
            temperature.value = 630D;
            temperature.time = Instant.now();

            writeApi.writeMeasurement(WritePrecision.NS, temperature);
        }
        System.out.println("--------");
        influxDBClient.close();
    }

    @Measurement(name = "temperature")
    private static class Temperature {

        @Column(tag = true)
        String location;

        @Column
        Double value;

        @Column(timestamp = true)
        Instant time;
    }
}
