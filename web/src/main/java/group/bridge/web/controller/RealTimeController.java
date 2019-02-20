package group.bridge.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.SensorRecordService;
import group.bridge.web.service.WarnRecordService;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.runtime.ObjectMonitor;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class RealTimeController {
    @Autowired
    SensorRecordService sensorRecordService;

    @Autowired
    WarnRecordService warnRecordService;

    @PostMapping("/ajax_test")
    public List<SensorRecord> getSensorRecord(Integer bridge_id, String sensor_name){
        return sensorRecordService.getSensor_records(bridge_id, sensor_name);
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
}
