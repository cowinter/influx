package com.example.influx.example.read;

import com.example.influx.domain.Student;
import com.example.influx.domain.Temperature;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;

/**
 * @author Shen
 * @Description 同步查询
 * The synchronous query is not intended for large query results because the Flux response can be potentially unbound.
 * @createTime 2022-06-10
 */
public class SynchronousQuery {
    private static char[] token = "3PI0dMFVNGb2AqXxjkBTXH0aXRUfzvO8g0Ks6K3MMsIytYq2SehcXbT_aaAK_5lwLDhfTc4VbfUns3uvGwctcw==".toCharArray();
    private static String org = "org_02";

    public static void main(final String[] args) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://81.68.76.15:8086", token, org);


        String flux = "from(bucket:\"bucket_02\") |> range(start: 0) |> filter(fn:(r) => r._measurement == \"student\")";

        // FluxTable
        QueryApi queryApi = influxDBClient.getQueryApi();

        List<FluxTable> tables = queryApi.query(flux);
        System.out.println(tables.size());
        for (FluxTable fluxTable : tables) {
            List<FluxRecord> records = fluxTable.getRecords();
            for (FluxRecord fluxRecord : records) {
//                System.out.println(fluxRecord.getTime() + ": " + fluxRecord.getValueByKey("_value"));
                System.out.println(fluxRecord.getTime() + ": " + fluxRecord.getValueByKey("_field")+"="+fluxRecord.getValueByKey("_value"));
            }
        }


        //
        // Map to POJO
        //
//        String flux = "from(bucket:\"bucket_02\") |> range(start: 0) |> filter(fn: (r) => r._measurement == \"temperature\")";
//
//        QueryApi queryApi = influxDBClient.getQueryApi();
//
//        List<Temperature> temperatures = queryApi.query(flux, Temperature.class);
//        for (Temperature temperature : temperatures) {
//            System.out.println(temperature.location +": " + temperature.value + " at " + temperature.time);
//        }

//        String flux = "from(bucket:\"bucket_02\") |> range(start: 0) |> filter(fn: (r) => r._measurement == \"student\")";
//
//        QueryApi queryApi = influxDBClient.getQueryApi();
//
//        List<Student> students = queryApi.query(flux, Student.class);
//        for (Student student : students) {
//            System.out.println(student.name +": " + student.sex + "-" +student.age + "-" + student.time);
//        }

        influxDBClient.close();
    }

//    @Measurement(name = "temperature")
//    public static class Temperature {
//
//        @Column(tag = true)
//        String location;
//
//        @Column
//        Double value;
//
//        @Column(timestamp = true)
//        Instant time;
//    }
}
