package com.example.influx.example.generic;

/**
 * @author Shen
 * @Description 泛型类的测试
 * @createTime 2022-07-28
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * 有的方法返回值为 <T> T ，有的方法返回值为 T ,区别在那里 ？
 **/
public class Request<T> {


    public <T> T getObject(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T t = tClass.newInstance();
        return t;
    }
    /**
     * 方法返回前的 <T> 是告诉编译器，当前方法的值传入类型可以和类初始化的泛型类型不同，
     * 也是就是该方法的泛型类可以自定义，不需要跟类初始化的泛型类相同
     *
     * 参数 T
     *  第一个 表示是泛型
     *  第二个 表示是返回是T类型的数据
     *  第三个 表示限制参数类型为T
     * @param data
     * @param <T>
     * @return
     */
    private <T> T getListFirst(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    /**
     * 这个只能传T类型的数据
     * @param data
     * @return
     */
    private T getListFirst2(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        // 入参由List<T>的T 决定，因为返回值为<T> T ,所以入参不受 Request<T> 影响
        Integer a = new Request<String>().getListFirst(data);

        // 编译出错，入参由Request<T> T的决定，受Request<T>影响
//        new Request<String>().getListFirst2(data);

        // 没什么区别
        String aa = new Request<String>().getListFirst(data2);
        String bb = new Request<String>().getListFirst2(data2);
    }

}