package group.bridge.web.controller;

import group.bridge.web.entity.Sensor;
import group.bridge.web.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class SensorController extends BaseController{
    @Autowired
    private SensorService sensorService;

    //展示所有传感器
    @RequestMapping("/allSensors")
    public String getAllSensor(Model model){
        List<Sensor> lists = sensorService.getAll();
        model.addAttribute("title","展示传感器信息页面");
        model.addAttribute("sensors",lists);
        return "sensor/allSensors";
    }

    //更改
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.get(id);
        model.addAttribute("title","修改传感器阈值");
        model.addAttribute("sensor",sensor);
        return "sensor/updateSensor";
    }

    //更改阈值
    @RequestMapping("/updateSensor")
    public String update(Sensor sensor){
        sensorService.update(sensor);
        return "redirect:/web/allSensors";
    }
}