package group.bridge.web.controller;

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
        //逻辑待完善
        warnRecordService.insertWarn_record();
    }

}
