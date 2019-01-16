package group.bridge.web.controller;


import group.bridge.web.service.SensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class SVGController extends BaseController{

    @Autowired
    SensorRecordService sensorRecordService;

    @RequestMapping("/siderbar1")
    public String DrawLiveSensorRecord(){



        return "test";
    }
}
