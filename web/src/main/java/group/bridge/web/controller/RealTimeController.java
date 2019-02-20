package group.bridge.web.controller;

import group.bridge.web.entity.SensorRecord;
import group.bridge.web.service.SensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class RealTimeController {
    @Autowired
    SensorRecordService sensorRecordService;

    @PostMapping("/ajax_test")
    public List<SensorRecord> getSensorRecord(Integer bridge_id, String sensor_name){
        return sensorRecordService.getSensor_records(bridge_id, sensor_name);
    }
}
