package com.example.influx.domain;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

/**
 * @author Shen
 * @Description influxdb 实体类
 * @createTime 2022-06-23
 */
@Measurement(name = "temperature")
public class Temperature {
    @Column(tag = true)
    public String location;

//    @Column(tag = true)
//    public String city;

    @Column
    public Integer value;

    @Column(timestamp = true)
    public Instant time;
}
