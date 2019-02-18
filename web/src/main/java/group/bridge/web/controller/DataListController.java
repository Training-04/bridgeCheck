package group.bridge.web.controller;


import group.bridge.web.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataListController {
    @Autowired
    private BridgeService bridgeService;

    @RequestMapping("/real_time_datalist")
    public String Real_time_datalist()
    {
        return "/DataList/real_time_datalist";
    }


    @RequestMapping("/history_datalist")
    public String History_datalist()
    {
        return "/DataList/history_datalist";
    }

    @RequestMapping("/history_warn_record")
    public String History_warn_record()
    {
        return "/DataList/history_warn_record";
    }

    @RequestMapping("/statistic_datalist")
    public String Statistic_datalist()
    {
        return "/DataList/statistic_datalist";
    }


}
