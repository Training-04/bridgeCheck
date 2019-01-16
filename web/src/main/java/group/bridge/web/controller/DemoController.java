package group.bridge.web.controller;


import group.bridge.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class DemoController extends BaseController{
    @Autowired
    PersonService personService;
    @RequestMapping(value = {"/",""})
    public String demo(Model model, HttpServletRequest request) {
        return "demo/fragment1";
    }
    @RequestMapping("/siderbar")
    public String siderBar(Model model){

        return "demo/fragment1";
    }


}
