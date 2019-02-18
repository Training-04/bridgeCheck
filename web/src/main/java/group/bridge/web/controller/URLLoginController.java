package group.bridge.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Controller
public class URLLoginController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getlogin() {
        System.out.println("进入登陆界面");
        return "login/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String dologin(HttpServletRequest request, String user_name, String password){
        System.out.println("进入登陆处理");
        HttpSession session=request.getSession();
        System.out.println("对用户["+user_name+"]密码["+password+"]验证");
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(user_name,password);
        Subject subject= SecurityUtils.getSubject();
        try {
            //完成登录
            subject.login(usernamePasswordToken);
            //User user= (User) subject.getPrincipals();
            System.out.println("对用户[" + user_name + "]进行登录验证..验证通过");
            //session.setAttribute("user",user);
            return "redirect:/index";
        }catch (UnknownAccountException uae){
            System.out.println("对用户[" + user_name + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message", "未知账户");
            return "/login/login";
        }catch (IncorrectCredentialsException ice){
            System.out.println("对用户[" + user_name + "]进行登录验证..验证未通过,密码不正确");
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
        return "sysmanagement/404";
    }
}