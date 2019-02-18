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
@RequestMapping("sensor")
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

    //展示所有传感器
    @RequestMapping("/allThreshold")
    public String getAllThreshold(Model model){
        List<Sensor> lists = sensorService.getAll();
        model.addAttribute("title","展示阈值信息页面");
        model.addAttribute("sensors",lists);
        return "sensor/allThreshold";
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
    @RequestMapping("/toUpdateThreshold/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.get(id);
        model.addAttribute("title","修改传感器阈值");
        model.addAttribute("sensor",sensor);
        return "sensor/updateThreshold";
    }

    //在所有传感器页面更改阈值
    @RequestMapping("/updateThreshold")
    public String update(Sensor sensor){
        sensorService.update(sensor);
        return "redirect:/sensor/allSensors";
    }

    //在所有传感器页面更改传感器
    @RequestMapping("/toUpdateSensor/{id}")
    public String toUpdateSensor(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.get(id);
        model.addAttribute("title","修改传感器");
        model.addAttribute("sensor",sensor);
        return "sensor/updateSensor";
    }

    //在所有传感器页面更改传感器
    @RequestMapping("/updateSensor")
    public String updateSensor(Sensor sensor){
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
        return "redirect:/sensor/searchSensor";
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
    public String addSensor(Sensor sensor,Integer bridge_id,HttpSession session){
        sensorService.add(sensor);
        Bridge bridge = bridgeService.get(bridge_id);
//        Bridge bridge = new Bridge();
//
//        Bridge bridge1= session.get();
        return "redirect:/sensor/allSensors";
    }
    //删除传感器
    @RequestMapping("/delSensor/{id}")
    public String delWarn_record(@PathVariable("id") Integer id) {
        sensorService.deleteById(id);
        return "redirect:/sensor/allSensors";
    }

}