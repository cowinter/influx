package com.example.influx.example.read;

//import com.example.influx.domain.Temperature;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;

import java.time.Instant;

/**
 * @author Shen
 * @Description 异步查询
 * @createTime 2022-06-23
 */
public class AsynchronousQuery {
    private static char[] token = "3PI0dMFVNGb2AqXxjkBTXH0aXRUfzvO8g0Ks6K3MMsIytYq2SehcXbT_aaAK_5lwLDhfTc4VbfUns3uvGwctcw==".toCharArray();
    private static String org = "org_02";

    public static void main(final String[] args) throws InterruptedException {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://81.68.76.15:8086", token, org);
        //
        // Query data
        //
//        String flux = "from(bucket:\"bucket_02\") |> range(start: 0)";
        String flux = "from(bucket:\"bucket_02\") |> range(start: 0) |> filter(fn: (r) => r._measurement == \"temperature\")";

        QueryApi queryApi = influxDBClient.getQueryApi();

        queryApi.query(flux, Temperature.class, (cancellable, temperature) -> {

            //t
            // The callback to consume a FluxRecord
            //
            // cancelable - object has the cancel method to stop asynchronous query
            //
            System.out.println(temperature.location+"===="+temperature.value);
//            System.out.println(temperature.time + "====" + temperature.valueInt);

        }, throwable -> {

            //
            // The callback to consume any error notification
            //
            System.out.println("Error occurred: " + throwable.getMessage());

        }, () -> {

            //
            // The callback to consume a notification about successfully end of stream.
            //
            System.out.println("Query completed");

        });

        System.out.println("before sleep");
        Thread.sleep(1_000);

        influxDBClient.close();
    }

    @Measurement(name = "temperature")
    public static class Temperature {

        @Column(tag = true)
        String location;

        @Column
        Double value;

        @Column(timestamp = true)
        Instant time;
    }
//@Measurement(name = "school")
//public static class School {
//
//    @Column(tag = true)
//    String location;
//
//    @Column
//    String value;
//
//    @Column(timestamp = true)
//    Instant time;
//}
}
