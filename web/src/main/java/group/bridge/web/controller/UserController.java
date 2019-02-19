package group.bridge.web.controller;

import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import group.bridge.web.service.RoleService;
import group.bridge.web.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @RequestMapping("/allUser")
    @RequiresPermissions("userInfo:allUser")
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

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(User user,@RequestParam(value = "role_id") List<Integer> role_id){
        //往中间表添加内容

        Role role;
        for (int i=0;i<role_id.size();i++){
            role = roleService.get(role_id.get(i));
            role.addusers(user);
            user.addRoles(role);
        }
        userService.add(user);
        return "redirect:/user/allUser";
    }


    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model,@PathVariable("id") int userID){
        User user=userService.getUserByID(userID);
        Set<Role>roles=user.getRoles();
        model.addAttribute("role",roles);
        model.addAttribute("user",user);
        model.addAttribute("cap","修改用户信息");
        model.addAttribute("title","修改用户信息");
        return "sysmanagement/usermanagement/updateuser";
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(User user,@RequestParam("role_id") List<Integer> role_id){

        //对需要修改的role的permissions付赋值
        Set<Role> roles = new HashSet<>();
        for (int i=0;i<role_id.size();i++){
            roles.add(roleService.get(role_id.get(i)));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/user/allUser";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int userID){
        userService.deleteById(userID);
        return "redirect:/user/allUser";
    }

    @RequestMapping("/toSearch")
    @RequiresPermissions("userInfo:toSearch")
    public String toSearch(Model model){
        model.addAttribute("title","查找用户信息");
        return "sysmanagement/usermanagement/searchuser";
    }

    @RequestMapping("/search")
    public String search(Model model,String user_name){
        List<User> users=userService.findUserByName(user_name);
        User user;
        List<Role> roleList=new ArrayList();
        Set<Role> roles = null;
        for (int i=0;i<users.size();i++)
        {
            user=users.get(i);
            roles=user.getRoles();
        }
        roleList.addAll(roles);
        model.addAttribute("role",roleList);
        model.addAttribute("user",users);
        model.addAttribute("title","查找用户信息");
        return "sysmanagement/usermanagement/searchuser";
    }

    @RequestMapping("/touserAddrole/{id}")
    public String roleadd(Model model,@PathVariable("id") int userID,HttpSession session){
        User user=userService.getUserByID(userID);
        List<Role> roles=roleService.getAll();
        Set<Role> roleSet = user.getRoles();
        model.addAttribute("role",roleSet);
        session.setAttribute("session",roles);
        model.addAttribute("user",user);
        model.addAttribute("cap","添加权限组");
        model.addAttribute("title","添加权限组");
        return "sysmanagement/usermanagement/userAddrole";
    }

    @RequestMapping(value = "/userAddrole",method = RequestMethod.POST)
    public String addper(User user,@RequestParam(value = "role_id") List<Integer> role_id,
                         @RequestParam("roleid") List<Integer> roleid){
        //添加关联表的数据

        //获取user原有的权限组，存储在roles中
        Set<Role> roles = new HashSet<>();
        for (int j=0;j<roleid.size();j++){
            roles.add(roleService.get(roleid.get(j)));
        }

        //获取user新添加的权限组
        Role role;
        for (int i=0;i<role_id.size();i++){
            role = roleService.get(role_id.get(i));
            //此时roles存放的是原有权限组加新的权限组
            roles.add(role);
        }
        //通过addRole(role)实现添加新的权限
        //遍历Set<Object>中的数据，将原有的和新添加的权限组存储到中间表中
        for (Role ro:roles){
            role = ro;
            role.addusers(user);
            user.addRoles(role);
        }
        userService.add(user);
        return "redirect:/user/allUser";
    }
}
