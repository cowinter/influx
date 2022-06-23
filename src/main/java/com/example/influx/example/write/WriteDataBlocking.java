package com.example.influx.example.write;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.exceptions.InfluxException;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

/**
 * @author Shen
 * @Description 阻塞写
 * @createTime 2022-06-23
 */
public class WriteDataBlocking {
    private static char[] token = "3PI0dMFVNGb2AqXxjkBTXH0aXRUfzvO8g0Ks6K3MMsIytYq2SehcXbT_aaAK_5lwLDhfTc4VbfUns3uvGwctcw=="
            .toCharArray();
    private static String org = "org_02";
    private static String bucket = "bucket_02";

    public static void main(final String[] args) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://81.68.76.15:8086", token, org, bucket);

        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

        try {
            //
            // Write by LineProtocol
            //
            String record = "temperature,location=north value=60.0";

            writeApi.writeRecord(WritePrecision.NS, record);

            //
            // Write by Data Point
            //
            Point point = Point.measurement("temperature")
                    .addTag("location", "west")
                    .addField("value", 55D)
                    .time(Instant.now().toEpochMilli(), WritePrecision.MS);

            writeApi.writePoint(point);

            //
            // Write by POJO
            //
            Temperature temperature = new Temperature();
            temperature.location = "south";
            temperature.value = 62D;
            temperature.time = Instant.now();

            writeApi.writeMeasurement(WritePrecision.NS, temperature);

        } catch (InfluxException ie) {
            System.out.println("InfluxException: " + ie);
        }

        influxDBClient.close();
        System.out.println("completed");
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
