package group.bridge.web.controller;


import group.bridge.web.service.PersonService;
import group.bridge.web.util.CookieUtil;
import group.bridge.web.util.JwtBuilder;
import group.bridge.web.util.XmlOperator;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Controller
public class DemoController extends BaseController {
    @Autowired
    PersonService personService;
    @RequestMapping("/")
    public String demo(Model model, HttpServletRequest request) {
        return "demo/fragment1";
    }

    @RequestMapping("/siderbar")
    public String siderBar(Model model){

        return "demo/fragment1";
    }

}
