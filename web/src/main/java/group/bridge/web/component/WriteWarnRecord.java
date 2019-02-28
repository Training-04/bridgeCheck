package group.bridge.web.component;

import group.bridge.web.entity.Sensor;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.SensorRecordService;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component

public class WriteWarnRecord implements Runnable {
    boolean stop = false;
    boolean stoped = false;
    @Autowired
    WarnRecordService warnRecordService;
    @Autowired
    SensorRecordService sensorRecordService;
    public WriteWarnRecord(){

    }
    public void buildWarnRecord(){
        Long count = warnRecordService.count();
        List<SensorRecord> threshold1List = null;
        List<SensorRecord> threshold2List = null;
        List<WarnRecord> warnRecordList = new ArrayList<>();
        if(count == 0){
            threshold2List = sensorRecordService.getSensorRecordByThreshold2NoWarn();
            threshold1List = sensorRecordService.getSensorRecordByThresholdNoWarn();
        }else{
            threshold2List = sensorRecordService.getSensorRecordByThreshold2HasWarn();
            threshold1List = sensorRecordService.getSensorRecordByThresholdHasWarn();
        }
        if(threshold1List != null){
            for(SensorRecord sensorRecord : threshold1List){
                Sensor sensor = sensorRecord.getSensor();
                String message = sensor.getPara_unit_cn()+">="+sensor.getThreshold1()+sensor.getParameter_unit();
                WarnRecord warnRecord = new WarnRecord();
                warnRecord.setSensor(sensor);
                warnRecord.setDate(sensorRecord.getDate());
                warnRecord.setWarn_para(message);
                warnRecord.setStatus("未解除");

                warnRecordList.add(warnRecord);

            }
        }
        if(threshold2List != null){
            for(SensorRecord sensorRecord : threshold2List){
                Sensor sensor = sensorRecord.getSensor();
                String message = sensor.getPara_unit_cn()+">="+sensor.getThreshold2()+sensor.getParameter_unit();
                WarnRecord warnRecord = new WarnRecord();
                warnRecord.setSensor(sensor);
                warnRecord.setDate(sensorRecord.getDate());
                warnRecord.setWarn_para(message);
                warnRecord.setStatus("未解除");

                warnRecordList.add(warnRecord);

            }
        }
        if(warnRecordList.size()!=0) {
            warnRecordService.addAll(warnRecordList);
        }
    }

    @Override
    public void run() {
        while(!stop) {
            try {

                Thread.sleep(1000);
                buildWarnRecord();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stoped=true;
    }
    @PreDestroy
    public void destory(){
        stop = true;
        System.out.println("warn_record is closing...");
        while (stoped){

        }
        System.out.println("warn_record is closed");

    }
}
