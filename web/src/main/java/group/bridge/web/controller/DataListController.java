package group.bridge.web.controller;


import group.bridge.web.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DataListController {
    @Autowired
    private BridgeService bridgeService;

    @RequestMapping("/datalist")
    public String Datalist()
    {
        return "demo/test";
    }
}
