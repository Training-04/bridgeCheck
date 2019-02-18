package group.bridge.web.controller;

import group.bridge.web.entity.Warn_record;
import group.bridge.web.service.Warn_recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("Warn_record")
public class Warn_recordController extends BaseController{
    @Autowired
    private Warn_recordService warn_recordService;

//    显示未解除报警的传感器信息
    @RequestMapping("/allWarn_records")
    public String getAllWarn_record(Model model){
        List<Warn_record> lists = warn_recordService.getWarn_record();
        model.addAttribute("title","展示未解除报警信息页面");
        model.addAttribute("warn_records",lists);
        return "warn_record/allWarn_records";
    }

//    显示已解除报警的信息
    @RequestMapping("/relieveWarn_records")
    public String getRelieveWarn_records(Model model){
        List<Warn_record> lists = warn_recordService.getRelieveWarn_record();
        model.addAttribute("title","展示已解除报警信息页面");
        model.addAttribute("warn_records",lists);
        return "warn_record/relieveWarn_records";
    }

    @RequestMapping("/toAddWarn_record")
    public String toAdd(Model model){
        model.addAttribute("title","添加报警记录页面");
        return "warn_record/addWarn_records";
    }

    @RequestMapping("/addWarn_records")
    public String addWarn_record(Warn_record wr){
        warn_recordService.add(wr);
        return "redirect:/Warn_record/allWarn_records";
    }
    @RequestMapping("toUpdateW/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Warn_record wr = warn_recordService.get(id);
        model.addAttribute("title","修改警报信息");
        model.addAttribute("wr",wr);
        return "warn_record/updateWarn_record";
    }

    @RequestMapping("/updateWarn_record")
    public String update(Warn_record wr){
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
        return "redirect:/relieveWarn_records";
    }
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
