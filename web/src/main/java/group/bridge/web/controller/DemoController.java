package group.bridge.web.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import group.bridge.web.entity.Bridge;
import group.bridge.web.service.BridgeService;
import group.bridge.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemoController extends BaseController{
    @Autowired
    PersonService personService;
    @Autowired
    BridgeService bridgeService;
    @RequestMapping(value = {"/",""})
    public String demo() {
        return "common/index";
    }
    @RequestMapping(value = "ajaxdemo")
    public String ajaxDemo(Model model) {
        Bridge bridge1=bridgeService.get(1);
        Bridge bridge2=bridgeService.get2(2);
        model.addAttribute("b1",bridge1);
        model.addAttribute("b2",bridge2);
        return "demo/ajax";
    }



}
