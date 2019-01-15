package group.bridge.web.controller;


import group.bridge.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DemoController {
    @Autowired
    PersonService personService;
    @RequestMapping("/")
    public String demo(Model model, HttpServletRequest request) {
        HttpSession session= request.getSession();
        List<String> stringList=new ArrayList<String>();
        stringList.add("1");
        stringList.add("2");
        session.setAttribute("list",stringList);
        model.addAttribute("title","test");
        return "demo/fragment1";
    }

    @RequestMapping("/siderbar")
    public String siderBar(Model model){
        model.addAttribute("menu","test");
        return "demo/fragment1";
    }

}
