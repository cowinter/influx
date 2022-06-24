# influx
- - -
## 2022-6-10
1. influxdb start config
influxdb restart,数据丢失
参考nohup.out日志，数据可能暂时存储/tmp/***,导致重新启动，新建目录，未读取到data
```
public void main(String[] args){

}
```