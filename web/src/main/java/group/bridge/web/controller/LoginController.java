package group.bridge.web.controller;

import group.bridge.web.entity.User;
import group.bridge.web.service.PersonService;
import group.bridge.web.service.UserService;
import group.bridge.web.util.CookieUtil;
import group.bridge.web.util.JwtBuilder;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    //@Autowired
    //UserService userService;

//    @RequestMapping("/login")
//    public String login() {
//        return "login/login";
//    }
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String login(String userName,String password,HttpServletResponse response){
//        //test
//        String testName="admin";
//        String testPassword="admin";
//        String url="redirect:/login";
//        if(testName.equals(userName) && password.equals(testPassword)){
//            setToken(userName,response);
//            url="redirect:/";
//        }
//        return url;
//    }
//    @RequestMapping("/logout")
//    public String logout(HttpServletRequest request,HttpServletResponse response) {
//        clean(request,response);
//        return "redirect:/login";
//    }
//    private void clean(HttpServletRequest request,HttpServletResponse response){
//        //remove cookie
//        CookieUtil.writeCookie(response,"token","",0);
//
//        //remove session
//        HttpSession session=request.getSession();
//        session.removeAttribute("nav");
//    }
//    private void setToken(String username,HttpServletResponse response){
//        String token=JwtBuilder.buildToken(username);
//        CookieUtil.writeCookie(response,"token",token);
//    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getlogin() {
        System.out.println("进入登陆界面");
        return "login/login";
    }

//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String dologin(HttpServletRequest request, Map<String, Object> map) throws Exception{
//        System.out.println("LoginController.login()");
//        // 登录失败从request中获取shiro处理的异常信息。
//        // shiroLoginFailure:就是shiro异常类的全类名.
//        System.out.println("进入登陆处理");
//        //String exception = (String) request.getSession().getAttribute("user_name");
//        //String exceptionp = (String) request.getSession().getAttribute("password");
//        //System.out.println("exception=" + exception+"exceptionp=" + exceptionp);
////        String msg = "";
////        if (exception != null&&exceptionp != null) {
////            if (UnknownAccountException.class.getName().equals(exception)) {
////                System.out.println("UnknownAccountException -- > 账号不存在：");
////                msg = "UnknownAccountException -- > 账号不存在：";
////            } else if (IncorrectCredentialsException.class.getName().equals(exceptionp)) {
////                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
////                msg = "IncorrectCredentialsException -- > 密码不正确：";
////            } else {
////                msg = "else >> "+exception+exceptionp;
////                System.out.println("else -- >" + exception+exceptionp);
////            }
////        }
//        String username=request.getParameter("user_name");
//        String password=request.getParameter("password");
//
//        String msg = "";
//        if (username != null&&password!=null) {
//            if (UnknownAccountException.class.getName().equals(username)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
////                return "/login/login";
//            } else if (IncorrectCredentialsException.class.getName().equals(password)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
////                return "/login/login";
//            } else {
//                msg = "else >> "+"username:"+username+" password:"+password;
//                System.out.println("else -- >" +"username:"+username+" password:"+password);
////                return "redirect:/index";
//            }
//        }
//        map.put("msg", msg);
//        // 此方法不处理登录成功,由shiro进行处理
//        return "login/login";
//    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String dologin(HttpServletRequest request,String user_name,String password){
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
