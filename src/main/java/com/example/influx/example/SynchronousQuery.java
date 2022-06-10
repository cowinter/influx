package com.example.influx.example;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.util.List;

/**
 * @author Shen
 * @Description TODO
 * @createTime 2022-06-10
 */
public class SynchronousQuery {
    private static char[] token = "tOkw91REtIzHc5ArwokoTbxIQLsiBnJ07mOLUWkXstCxp71GbCTZj8EzJQZ84_qwZcpM5iRUrCch61sEF9LhNQ==".toCharArray();
    private static String org = "org_01";

    public static void main(final String[] args) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://81.68.76.15:8086", token, org);

        String flux = "from(bucket:\"bucket_01\") |> range(start: 0)";

        QueryApi queryApi = influxDBClient.getQueryApi();

        //
        // Query data
        //
        List<FluxTable> tables = queryApi.query(flux);
        for (FluxTable fluxTable : tables) {
            List<FluxRecord> records = fluxTable.getRecords();
            for (FluxRecord fluxRecord : records) {
                System.out.println(fluxRecord.getTime() + ": " + fluxRecord.getValueByKey("_value"));
            }
        }

        influxDBClient.close();
    }
}
