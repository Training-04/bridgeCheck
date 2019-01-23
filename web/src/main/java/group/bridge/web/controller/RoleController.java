package group.bridge.web.controller;


import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import group.bridge.web.service.PermissionService;
import group.bridge.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RoleController extends BaseController{

    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/allRole")
    public String getAllRole(Model model){
        List<Role> lists=roleService.getAll();
        model.addAttribute("role",lists);
        model.addAttribute("title","所有权限组信息");
        return "sysmanagement/rolemanagement/allrole";
    }

    @RequestMapping("/toAddRole")
    public String toAdd(Model model, HttpSession session){
        List<Permission> per=permissionService.getAll();
        session.setAttribute("session",per);
        model.addAttribute("title","添加权限组");
        return "sysmanagement/rolemanagement/addrole";
    }

    @RequestMapping("/addRole")
    public String add(Role role,Permission permission){
        //添加中间表的数据
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        permission.setRoles(roles);
        Set<Permission> permissions=new HashSet<>();
        permissions.add(permission);
        role.setPermissions(permissions);
        roleService.add(role);
        return "redirect:/allRole";
    }


    @RequestMapping("/toUpdateRole/{id}")
    public String toUpdate(Model model,@PathVariable("id") int roleID){
        Role role=roleService.getRoleByID(roleID);
        model.addAttribute("role",role);
        model.addAttribute("cap","修改权限组信息");
        model.addAttribute("title","修改权限组信息");
        return "sysmanagement/rolemanagement/updaterole";
    }

    @RequestMapping("/updateRole")
    public String update(Role role,Permission permission){
        //添加中间表的数据
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        permission.setRoles(roles);
        Set<Permission> permissions=new HashSet<>();
        permissions.add(permission);
        role.setPermissions(permissions);
        roleService.updateRole(role);
        return "redirect:/allRole";
    }

    @RequestMapping("/deleteRole/{id}")
    public String delete(@PathVariable("id") int roleID){
        roleService.deleteById(roleID);
        return "redirect:/allRole";
    }

    @RequestMapping("/toSearchRole")
    public String toSearch(Model model){
        model.addAttribute("title","查找权限组信息");
        return "sysmanagement/rolemanagement/searchrole";
    }

    @RequestMapping("/searchRole")
    public String search(Model model,String role_name){
        List<Role> role=roleService.findRoleByName(role_name);
        model.addAttribute("role",role);
        model.addAttribute("title","查找权限组信息");
        return "sysmanagement/rolemanagement/searchrole";
    }

}
