package group.bridge.web.controller;

import group.bridge.web.entity.Bridge;
import group.bridge.web.entity.Sensor;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.BridgeService;
import group.bridge.web.service.SensorService;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("Warn_record")
public class Warn_recordController extends BaseController{
    @Autowired
    private WarnRecordService warn_recordService;
    @Autowired
    private BridgeService bridgeService;
    @Autowired
    private SensorService sensorService;

//    显示未解除报警的传感器信息
    @RequestMapping("/allWarn_records")
    public String getAllWarn_record(Model model){
        List<WarnRecord> lists = warn_recordService.getWarn_record();
        model.addAttribute("title","展示未解除报警信息页面");
        model.addAttribute("warn_records",lists);
        return "warn_record/allWarn_records";
    }

//    显示已解除报警的信息
    @RequestMapping("/relieveWarn_records")
    public String getRelieveWarn_records(Model model){
        List<WarnRecord> lists = warn_recordService.getRelieveWarn_record();
        model.addAttribute("title","展示已解除报警信息页面");
        model.addAttribute("warn_records",lists);
        return "warn_record/relieveWarn_records";
    }

    @RequestMapping("/toAddWarn_record")
    public String toAdd(Model model, HttpSession session){
        model.addAttribute("title","添加报警记录页面");
        //      下边两行是从数据库中获取桥梁名称
        List<Bridge> lists = bridgeService.getAll();
        session.setAttribute("bridges",lists);
        //      下边两行是从数据库中获取传感器名称
        List<Sensor> lists2 = sensorService.getAll();
        session.setAttribute("sensors",lists2);
        return "warn_record/addWarn_records";
    }

    @RequestMapping("/addWarn_records")
    public String addWarn_record(WarnRecord wr, @RequestParam(value = "sensor_id") Integer sensor_id) {
        Sensor sensor = sensorService.get(sensor_id);
        wr.setSensor(sensor);
        warn_recordService.add(wr);
        return "redirect:/Warn_record/allWarn_records";
    }
    @RequestMapping("toUpdateW/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        WarnRecord wr = warn_recordService.get(id);
        model.addAttribute("title","修改警报信息");
        model.addAttribute("wr",wr);
        return "warn_record/updateWarn_record";
    }

    @RequestMapping("/updateWarn_record")
    public String update(WarnRecord wr){
        warn_recordService.update(wr);
        return "redirect:/Warn_record/allWarn_records";
    }

    //删除未解决报警记录
    @RequestMapping("/delWarn_record/{id}")
    public String delWarn_record(@PathVariable("id") Integer id) {
        warn_recordService.deleteById(id);
        return "redirect:/Warn_record/allWarn_records";
    }

    //删除已解决报警记录
    @RequestMapping("/delRelieveWarn_record/{id}")
    public String delRelieveWarn_record(@PathVariable("id") Integer id) {
        warn_recordService.deleteById(id);
        return "redirect:/Warn_record/relieveWarn_records";
    }
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
