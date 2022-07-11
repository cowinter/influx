package com.example.influx.example.delete;

import com.influxdb.client.DeleteApi;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.exceptions.InfluxException;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Shen
 * @Description 删除数据
 * @createTime 2022-07-08
 */
public class DeleteData {
    private static char[] token = "3PI0dMFVNGb2AqXxjkBTXH0aXRUfzvO8g0Ks6K3MMsIytYq2SehcXbT_aaAK_5lwLDhfTc4VbfUns3uvGwctcw==".toCharArray();
    private static String org = "org_02";

    public static void main(String[] args) {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://81.68.76.15:8086", token);

        DeleteApi deleteApi = influxDBClient.getDeleteApi();

        try {

            OffsetDateTime start = OffsetDateTime.now().minus(10, ChronoUnit.DAYS);
            System.out.println(start);
            OffsetDateTime stop = OffsetDateTime.now();
            System.out.println(stop);

            deleteApi.delete(start, stop, "", "bucket_02", "org_02");

        } catch (InfluxException ie) {
            System.out.println("InfluxException: " + ie);
        }

        influxDBClient.close();
    }
}
