package group.bridge.web.controller;

import group.bridge.web.entity.Sensor;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.service.SensorRecordService;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("sensor_records")
public class SensorRecordController extends BaseController{

    @Autowired
    private SensorRecordService sensorRecordService;

    @Autowired
    private WarnRecordService warnRecordService;

    //扫描的时间起点
    private Date startDate;

    public void CheckSensorRecord(){
        List<SensorRecord> ls1 = sensorRecordService.getWarn1Sensor_records(startDate);
        List<SensorRecord> ls2 = sensorRecordService.getWarn2Sensor_records(startDate);
        //插入
        for(int index = 0; index < ls1.size(); index++){
            SensorRecord seRecord = ls1.get(index);
            long date1 = seRecord.getDate().getTime();
            long startdate = startDate.getTime();
            if(date1>startdate){
                startDate = seRecord.getDate();
            }
            warnRecordService.insertWarn_record(seRecord.getDate(), "未解除", "1级", seRecord.getSensor().getSensor_id());
        }
        for(int index = 0; index < ls2.size(); index++){
            SensorRecord seRecord = ls2.get(index);
            long date2 = seRecord.getDate().getTime();
            long startdate = startDate.getTime();
            if(date2>startdate){
                startDate=seRecord.getDate();
            }
            warnRecordService.insertWarn_record(seRecord.getDate(), "未解除", "2级", seRecord.getSensor().getSensor_id());
        }
    }

}
