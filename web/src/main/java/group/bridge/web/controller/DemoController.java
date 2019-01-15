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
@RequestMapping("web")
public class DemoController {
    @Autowired
    private SensorService sensorService;
    @RequestMapping("/")
    public String demo(Model model) {
//        页面的标题为test
        model.addAttribute("title","test");
        return "demo/fragment1";
    }

    //展示所有传感器
    @RequestMapping("/list")
    public String getAllSensor(Model model){
        List<Sensor> lists = sensorService.getAllSensor();
        model.addAttribute("sensors",lists);
        return "CRUD/list";
    }

    //更改
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.getSensorById(id);
        model.addAttribute("sensor",sensor);
        return "CRUD/update";
    }

    //更改阈值
    @RequestMapping("/update")
    public String update(Sensor sensor){
        sensorService.updateSensor(sensor);
        return "redirect:/web/list";
    }
}
