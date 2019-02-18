package group.bridge.web.controller;

import group.bridge.web.entity.Bridge;
import group.bridge.web.entity.Sensor;
import group.bridge.web.service.BridgeService;
import group.bridge.web.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SensorController extends BaseController{
    @Autowired
    private SensorService sensorService;
    @Autowired
    private BridgeService bridgeService;

    //展示所有传感器
    @RequestMapping("/allSensors")
    public String getAllSensor(Model model){
        List<Sensor> lists = sensorService.getAll();
        model.addAttribute("title","展示传感器信息页面");
        model.addAttribute("sensors",lists);
        return "sensor/allSensors";
    }

    //搜索相应名称的传感器（中介）
    @RequestMapping("/toSearchSensor")
    public String toSearch(Model model){
        model.addAttribute("title","查询传感器信息页面");
        return "sensor/searchSensor";
    }

    //搜索相应名称的传感器
    @RequestMapping("/searchSensor")
    public String getByName(Model model,String sensor_name){
        List<Sensor> lists = sensorService.getByName(sensor_name);
        model.addAttribute("title","查询传感器信息页面");
        model.addAttribute("sensor",lists);
        return "sensor/searchSensor";
    }

    //在所有传感器页面更改阈值
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.get(id);
        model.addAttribute("title","修改传感器阈值");
        model.addAttribute("sensor",sensor);
        return "sensor/updateSensor";
    }

    //在所有传感器页面更改阈值
    @RequestMapping("/updateSensor")
    public String update(Sensor sensor){
        sensorService.update(sensor);
        return "redirect:/sensor/allSensors";
    }

//    在搜索页面更改阈值
    @RequestMapping("/search_toUpdate/{id}")
    public String sensor_toUpdate(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.get(id);
        model.addAttribute("title","修改传感器阈值");
        model.addAttribute("sensor",sensor);
        return "sensor/search_updateSensor";
    }
    //在搜索页面更改阈值
    @RequestMapping("/search_updateSensor")
    public String search_update(Sensor sensor){
        sensorService.update(sensor);
        return "redirect:/searchSensor";
    }

//    添加传感器
    @RequestMapping("/toAddSensors")
    public String toAdd(Model model,HttpSession session){
        model.addAttribute("title","添加传感器页面");
        List<Bridge> lists = bridgeService.getAll();
        session.setAttribute("bridges",lists);
        return "sensor/addSensors";
    }
//  添加传感器
    @RequestMapping("/addSensors")
    public String addSensor(Sensor sensor){
        sensorService.add(sensor);
        return "redirect:/allSensors";
    }
    //删除传感器
    @RequestMapping("/delSensor/{id}")
    public String delWarn_record(@PathVariable("id") Integer id) {
        sensorService.deleteById(id);
        return "redirect:/allSensors";
    }
}