package com.example.influx.test;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Shen
 * @Description 检测从List转Map的方式
 * @createTime 2022-06-23
 */
@Data
public class ListToMap {
    private Long longId;
    private String name;

    public ListToMap(Long longId, String name) {
        this.longId = longId;
        this.name = name;
    }
    public ListToMap() {
    }

    public static void main(String[] args) {
        List<ListToMap> list = new ArrayList<>(16);
        list.add(new ListToMap(1L,"小明"));
        list.add(new ListToMap(2L,"小明"));
        list.add(new ListToMap(3L,"小月"));
        list.add(new ListToMap(4L,"小阳"));
        list.add(new ListToMap(5L,"小太"));
        list.add(new ListToMap(6L,"小三"));

        Map<String,ListToMap> map = list.stream().collect(Collectors.toMap(e->String.valueOf(e.getLongId()), Function.identity()));
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
