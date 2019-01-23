package group.bridge.web.controller;

import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import group.bridge.web.service.RoleService;
import group.bridge.web.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController extends BaseController{

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @RequestMapping("/allUser")
    @RequiresPermissions("userInfo:alluser")
    public String getAllUser(Model model){
        List<User> lists=userService.getAll();
        model.addAttribute("user",lists);
        model.addAttribute("title","所有用户信息");
        return "sysmanagement/usermanagement/alluser";
    }

    @RequestMapping("/toAdd")
    @RequiresPermissions("userInfo:toAdd")
    public String toAdd(Model model, HttpSession session){
        List<Role> roles=roleService.getAll();
        session.setAttribute("session",roles);
        model.addAttribute("title","添加用户");
        return "sysmanagement/usermanagement/adduser";
    }

    @RequestMapping("/add")
    public String add(User user,Role role){
        //往中间表添加内容
        Set<User> users=new HashSet<>();
        users.add(user);
        role.setUsers(users);
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/allUser";
    }


    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model,@PathVariable("id") int userID){
        User user=userService.getUserByID(userID);
        model.addAttribute("user",user);
        model.addAttribute("cap","修改用户信息");
        model.addAttribute("title","修改用户信息");
        return "sysmanagement/usermanagement/updateuser";
    }


    @RequestMapping("/update")
    public String update(User user,Role role){
        //往中间表添加内容
        Set<User> users=new HashSet<>();
        users.add(user);
        role.setUsers(users);
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/allUser";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int userID){
        userService.deleteById(userID);
        return "redirect:/allUser";
    }

    @RequestMapping("/toSearch")
    @RequiresPermissions("userInfo:toSearch")
    public String toSearch(Model model){
        model.addAttribute("title","查找用户信息");
        return "sysmanagement/usermanagement/searchuser";
    }

    @RequestMapping("/search")
    public String search(Model model,String user_name){
        List<User> user=userService.findUserByName(user_name);
        //List<Role> roles=roleService.findRolenameByUserId();

        //model.addAttribute("role",roles);
        model.addAttribute("user",user);
        model.addAttribute("title","查找用户信息");
        return "sysmanagement/usermanagement/searchuser";
    }
}
