package group.bridge.web.controller;


import group.bridge.web.service.SensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/echarts")
public class SVGController extends BaseController{

    @Autowired
    SensorRecordService sensorRecordService;

    @RequestMapping("/livedata")
    public String DrawLiveSensorRecord(){
        return "svg/svglivedata";
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
    public String DrawWarnRecords(){
        return "svg/svgwarnrecords";
    }
}
