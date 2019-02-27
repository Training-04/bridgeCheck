package group.bridge.web.controller;


import com.alibaba.fastjson.JSON;
import group.bridge.web.entity.Bridge;
import group.bridge.web.entity.Sensor;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.entity.WarnRecord;
import group.bridge.web.service.BridgeService;
import group.bridge.web.service.SensorRecordService;
import group.bridge.web.service.SensorService;
import group.bridge.web.service.WarnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DataListController {
    @Autowired
    private BridgeService bridgeService;
    @Autowired
    private WarnRecordService warnRecordService;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorRecordService sensorRecordService;


    @RequestMapping("/real_time_datalist")
    public String Real_time_datalist(Model model)
    {
        List<Bridge> bridges = bridgeService.getAll();
        model.addAttribute("bridges",bridges);
        return "/datalist/real_time_datalist";
    }



    @RequestMapping("/history_datalist/{index}")
    public String getPage(@PathVariable("index")Integer index , Model model){
        Pageable pageable= PageRequest.of(index-1, 10);
        Page<SensorRecord> srPage = sensorRecordService.getPage(pageable);

        int num=srPage.getSize();
        int count=srPage.getTotalPages();

        model.addAttribute("sr",srPage.getContent());
        // 当前页
        model.addAttribute("pageIndex", index);
        // 总页数
        model.addAttribute("pageTotal", count);
        return "/datalist/history_datalist";
    }

    @RequestMapping("/history_warn_record/{index}")
    public String History_warn_record(@PathVariable("index")Integer index ,Model model)
    {
        Pageable pageable= PageRequest.of(index-1, 10);
        Page<WarnRecord> wrPage = warnRecordService.getPage(pageable);

        int count=wrPage.getTotalPages();

        model.addAttribute("wr",wrPage.getContent());
        // 当前页
        model.addAttribute("pageIndex", index);
        // 总页数
        model.addAttribute("pageTotal", count);

        return "/datalist/history_warn_record";
    }



    @RequestMapping("/statistic_datalist")
    public String Statistic_datalist(Model model)
    {
        List<Bridge> bridges = bridgeService.getAll();
        model.addAttribute("bridges",bridges);
        return "/datalist/statistic_datalist";
    }

}
