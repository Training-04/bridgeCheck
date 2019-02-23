package group.bridge.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import group.bridge.web.entity.SensorRecord;
import group.bridge.web.service.SensorRecordService;
import group.bridge.web.util.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.management.openmbean.CompositeData;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class URLLoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getlogin() {
        System.out.println("进入登陆界面");
        return "login/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String dologin(HttpServletRequest request, String userName, String password){

        System.out.println("进入登陆处理");
        HttpSession session=request.getSession();
        System.out.println("对用户["+userName+"]密码["+password+"]验证");
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userName,password);
        Subject subject= SecurityUtils.getSubject();
        try {
            //完成登录
            subject.login(usernamePasswordToken);
            //登录成功后对导航栏的操作
            SessionUtil.setNavigation(request);
            //User user= (User) subject.getPrincipals();
            System.out.println("对用户[" + userName + "]进行登录验证..验证通过");
            //session.setAttribute("user",user);
            return "redirect:/index";
        }catch (UnknownAccountException uae){
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message", "未知账户");
            return "/login/login";
        }catch (IncorrectCredentialsException ice){
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,密码不正确");
            request.setAttribute("message", "密码不正确");
            return "/login/login";
        }
    }


    @RequestMapping("/index")
    public String index(){
        return"demo/fragment1";
    }

    @RequestMapping("/404")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "/sysmanagement/404";
    }
}
