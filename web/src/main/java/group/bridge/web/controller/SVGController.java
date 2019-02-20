package group.bridge.web.controller;



import group.bridge.web.entity.SensorRecord;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.SensorRecordService;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/echarts")
public class SVGController extends BaseController{

    @Autowired
    SensorRecordService sensorRecordService;

    @Autowired
    WarnRecordService warnRecordService;

    @RequestMapping("/livedata")
    public String DrawLiveSensorRecord(){
        return "svg/svglivedata";
        //return "svg/ajax_test";
    }

    @RequestMapping("/historydata")
    public String DrawHisSensorRecord(){
        return "svg/svghisdata";
    }

    @RequestMapping("/statistics")
    public String DrawStatistics(){
        return "svg/svgstatistics";
    }

    @RequestMapping("/warnrecords")
    public String DrawWarnRecords(Model model){
        //List<WarnRecord> warns = warnRecordService.getAll();
        //model.addAttribute("warn", warns);
        return "svg/svgwarnrecords";
    }

/*
    //以下方法用于ajax
    @PostMapping("/ajax_sensor_record")
    public List<SensorRecord> getLiveSensorRecord(Integer bridge_id, String sensor_name, Date curTime){
        return sensorRecordService.getLastSensor_records(bridge_id, sensor_name, curTime);
    }

    @PostMapping("/ajax_test")
    public List<SensorRecord> getSensorRecord(Integer bridge_id, String sensor_name){
        return sensorRecordService.getSensor_records(bridge_id, sensor_name);
    }
*/
}
