package group.bridge.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.bridge.web.entity.Bridge;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.BridgeService;
import group.bridge.web.service.SensorRecordService;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class RealTimeController {
    @Autowired
    SensorRecordService sensorRecordService;

    @Autowired
    WarnRecordService warnRecordService;

    @Autowired
    BridgeService bridgeService;

    @PostMapping("/ajax_test")
    public List<SensorRecord> getSensorRecord(Integer bridge_id, String sensor_name){
        return sensorRecordService.getSensor_records(bridge_id, sensor_name);
    }

    @RequestMapping("/ajax_bridges")
    public String getAllBridge(){
        String str = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Bridge> ls = bridgeService.getAll();
            str = mapper.writeValueAsString(ls);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(str);
        return str;
    }

    @RequestMapping("/ajax_warn_records")
    public String getWarnRecord(){
        String str = "";
        ObjectMapper mapper = new ObjectMapper();
        try{
            List<WarnRecord> warns = warnRecordService.getAll();
            str=mapper.writeValueAsString(warns);

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(str);
        return str;
    }

    @RequestMapping("/ajax_history_records")
    public String getHistorySensorRecord(Integer bridge_id, String para_cn){
        String str = "";
        ObjectMapper mapper = new ObjectMapper();
        try{
            List<SensorRecord> seRecord = sensorRecordService.getSensor_records(bridge_id, para_cn);
            str = mapper.writeValueAsString(seRecord);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(str);
        return str;
    }

    @RequestMapping("/ajax_live_records")
    public String getLiveSensorRecord(Integer bridge_id, String para_cn, Date curTime){
        String str = "";
        ObjectMapper mapper = new ObjectMapper();
        try{
            List<SensorRecord> seRecord = sensorRecordService.getLastSensor_records(bridge_id, para_cn, curTime);
            str = mapper.writeValueAsString(seRecord);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(str);
        return str;
    }

    @RequestMapping("/ajax_15Sec_records")
    public String get15SecSensorRecord(Integer bridge_id, String para_cn, Date curTime){
        String str = "";
        ObjectMapper mapper = new ObjectMapper();
        try{
            List<SensorRecord> seRecord = sensorRecordService.get15SecSensor_records(bridge_id, para_cn, curTime);
            str = mapper.writeValueAsString(seRecord);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(str);
        return str;
    }
}
