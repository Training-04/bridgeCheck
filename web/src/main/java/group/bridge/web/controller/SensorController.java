package group.bridge.web.controller;

import group.bridge.web.entity.Bridge;
import group.bridge.web.entity.Sensor;
import group.bridge.web.service.BridgeService;
import group.bridge.web.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    //搜索相应名称的阈值（中介）
    @RequestMapping("/toSearchThreshold")
    public String toSearchThreshold(Model model){
        model.addAttribute("title","查询传感器阈值信息页面");
        return "sensor/searchThreshold";
    }

    //搜索相应名称的阈值
    @RequestMapping("/searchThreshold")
    public String getByName1(Model model,String sensor_name){
        List<Sensor> lists = sensorService.getByName(sensor_name);
        model.addAttribute("title","查询传感器阈值信息页面");
        model.addAttribute("sensor",lists);
        return "sensor/searchThreshold";
    }

    //搜索相应名称的阈值
    @RequestMapping("/searchThreshold2")
    public String getByName3(Model model,@ModelAttribute("sensor_name") String sensor_name){
        List<Sensor> lists = sensorService.getByName(sensor_name);
        model.addAttribute("title","查询传感器阈值信息页面");
        model.addAttribute("sensor",lists);
        return "sensor/searchThreshold";
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

    //搜索相应名称的传感器
    @RequestMapping("/searchSensor2")
    public String getByName2(Model model,@ModelAttribute("sensor_name") String sensor_name){
        List<Sensor> lists = sensorService.getByName(sensor_name);
        model.addAttribute("title","查询传感器信息页面");
        model.addAttribute("sensor",lists);
        return "sensor/searchSensor";
    }

//    在搜索页面更改阈值
    @RequestMapping("/search_toUpdateThreshold/{id}")
    public String sensor_toUpdate(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.get(id);
        model.addAttribute("title","修改传感器阈值");
        model.addAttribute("sensor",sensor);
        return "sensor/search_updateThreshold";
    }
    //在搜索页面更改阈值
    @RequestMapping("/search_updateThreshold")
    public String search_update(Sensor sensor,@RequestParam(value = "sensor_name") String sensor_name, RedirectAttributes model){
        sensorService.update(sensor);
        model.addFlashAttribute("sensor_name", sensor_name);
        return "redirect:/sensor/searchThreshold2";
    }

    //    在搜索页面更改传感器
    @RequestMapping("/search_toUpdateSensor/{id}")
    public String search_toUpdateSensor(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.get(id);
        model.addAttribute("title","修改传感器");
        model.addAttribute("sensor",sensor);
        return "sensor/search_updateSensor";
    }
    //在搜索页面更改传感器
    @RequestMapping("/search_updateSensor")
    public String search_updateSensor(Sensor sensor, @RequestParam(value = "sensor_name") String sensor_name, RedirectAttributes model){
        sensorService.update(sensor);
        model.addFlashAttribute("sensor_name", sensor_name);
        return "redirect:/sensor/searchSensor2";
    }

//    添加传感器
    @RequestMapping("/toAddSensors")
    public String toAdd(Model model,HttpSession session){
        model.addAttribute("title","添加传感器页面");
//      下边两行是从数据库中获取桥梁名称
        List<Bridge> lists = bridgeService.getAll();
        session.setAttribute("bridges",lists);
        return "sensor/addSensors";
    }

//  添加传感器
    @RequestMapping("/addSensors")
//    使用@RequestParam注解将请求参数绑定至方法参数，value为请求参数名
    public String addSensor(Sensor sensor,@RequestParam(value = "bridge_id") Integer bridge_id){
        Bridge bridge = bridgeService.get(bridge_id);
        sensor.setBridge(bridge);
        sensorService.add(sensor);
        return "redirect:/sensor/allSensors";
    }
    //删除传感器
//    通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中
    @RequestMapping("/delSensor/{id}")
    public String delSensor(@PathVariable("id") Integer id) {
        sensorService.deleteById(id);
        return "redirect:/sensor/allSensors";
    }
}