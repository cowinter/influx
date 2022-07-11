package com.example.influx.domain;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

/**
 * @author Shen
 * @Description TODO
 * @createTime 2022-07-11
 */
@Measurement(name = "student")
public class Student {
    @Column
    public String name;
    @Column(tag = true)
    public String sex;
    @Column
    public Integer age;
    @Column(timestamp = true)
    public Instant time;
}
