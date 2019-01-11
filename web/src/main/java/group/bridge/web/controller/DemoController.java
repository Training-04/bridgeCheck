package group.bridge.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemoController {
    @RequestMapping("/")
    public String demo(Model model) {
        model.addAttribute("title","test");
        return "demo/fragment1";
    }

}
