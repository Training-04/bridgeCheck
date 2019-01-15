package group.bridge.web.controller;

import group.bridge.web.entity.Warn_record;
import group.bridge.web.service.Warn_recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("warn_record")
public class Warn_recordController {
    @Autowired
    private Warn_recordService warn_recordService;

    @RequestMapping("/list")
    public String getAllWarn_record(Model model){
        List<Warn_record> lists = warn_recordService.getAll();
        model.addAttribute("title","所有警报信息");
        model.addAttribute("warn_records","lists");
        return "/warn_record/list";
    }

    @RequestMapping("toUpdate/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Warn_record wr = warn_recordService.get(id);
        model.addAttribute("title","修改警报信息");
        model.addAttribute("wr",wr);
        return "warn_record/update";
    }

    @RequestMapping("update")
    public String update(Warn_record wr){
        warn_recordService.update(wr);
        return "redirect:/warn_record/list";
    }

}
