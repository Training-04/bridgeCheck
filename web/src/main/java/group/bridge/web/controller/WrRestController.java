package group.bridge.web.controller;

import group.bridge.web.entity.Warn_record;
import group.bridge.web.service.Warn_recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("warn_record")
public class WrRestController {
    @Autowired
    private Warn_recordService warn_recordService;
    @RequestMapping("/add")
    private Warn_record addWarn_record(Warn_record warn_record){
        return warn_recordService.addWarn_record(warn_record);
    }

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }
}
