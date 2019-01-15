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
@RequestMapping("sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    //展示所有传感器
    @RequestMapping("/list")
    public String getAllSensor(Model model){
        List<Sensor> lists = sensorService.getAll();
        model.addAttribute("title","所有的传感器信息");
        model.addAttribute("sensors",lists);
        return "sensor/list";
    }

    //更改
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.get(id);
        model.addAttribute("title","修改传感器阈值");
        model.addAttribute("sensor",sensor);
        return "sensor/update";
    }

    //更改阈值
    @RequestMapping("/update")
    public String update(Sensor sensor){
        sensorService.update(sensor);
        return "redirect:/sensor/list";
    }
}