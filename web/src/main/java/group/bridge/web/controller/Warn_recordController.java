package group.bridge.web.controller;

import group.bridge.web.entity.Sensor;
import group.bridge.web.entity.Warn_record;
import group.bridge.web.service.Warn_recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class Warn_recordController extends BaseController{
    @Autowired
    private Warn_recordService warn_recordService;

    @RequestMapping("/allWarn_records")
    public String getAllWarn_record(Model model){
        List<Warn_record> lists = warn_recordService.getAll();
        model.addAttribute("title","展示报警信息页面");
        model.addAttribute("warn_records",lists);
        return "warn_record/allWarn_records";
    }


    @RequestMapping("toAdd")
    public String toAdd(){
        return "warn_record/addWarn_records";
    }

    @RequestMapping("/addWarn_records")
    public String addWarn_record(Warn_record wr){
        warn_recordService.add(wr);
        return "redirect:/web/allWarn_records";
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
        return "redirect:/web/allWarn_records";
    }

    //删除报警记录
    @RequestMapping("/delWarn_record/{id}")
    public String delWarn_record(@PathVariable("id") Integer id) {
        warn_recordService.deleteById(id);
        return "redirect:/web/allWarn_records";
    }
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
