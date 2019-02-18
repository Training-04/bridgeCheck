package group.bridge.web.controller;

import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.WarnRecordService;
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
public class WarnRecordController extends BaseController{
    @Autowired
    private WarnRecordService warnRecordService;

    @RequestMapping("/allWarn_records")
    public String getAllWarn_record(Model model){
        List<WarnRecord> lists = warnRecordService.getWarn_record();
        model.addAttribute("title","展示报警信息页面");
        model.addAttribute("warn_records",lists);
        return "warn_record/allWarn_records";
    }


    @RequestMapping("toAdd")
    public String toAdd(){
        return "warn_record/addWarn_records";
    }

    @RequestMapping("/addWarn_records")
    public String addWarn_record(WarnRecord wr){
        warnRecordService.add(wr);
        return "redirect:/allWarn_records";
    }
    @RequestMapping("toUpdateW/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        WarnRecord wr = warnRecordService.get(id);
        model.addAttribute("title","修改警报信息");
        model.addAttribute("wr",wr);
        return "warn_record/updateWarn_record";
    }

    @RequestMapping("/updateWarn_record")
    public String update(WarnRecord wr){
        warnRecordService.update(wr);
        return "redirect:/allWarn_records";
    }

    //删除报警记录
    @RequestMapping("/delWarn_record/{id}")
    public String delWarn_record(@PathVariable("id") Integer id) {
        warnRecordService.deleteById(id);
        return "redirect:/allWarn_records";
    }
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
