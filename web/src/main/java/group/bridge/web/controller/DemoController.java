package group.bridge.web.controller;


import group.bridge.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemoController {
    @Autowired
    PersonService personService;
    @RequestMapping("/")
    public String demo(Model model) {
        model.addAttribute("title","test");
        return "demo/fragment1";
    }

}
