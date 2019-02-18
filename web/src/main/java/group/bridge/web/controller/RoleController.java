package group.bridge.web.controller;


import group.bridge.web.entity.Permission;
import group.bridge.web.entity.Role;
import group.bridge.web.entity.User;
import group.bridge.web.service.PermissionService;
import group.bridge.web.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{

    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/allRole")
    @RequiresPermissions("roleInfo:allRole")
    public String getAllRole(Model model){
        List<Role> lists=roleService.getAll();
        model.addAttribute("role",lists);
        model.addAttribute("title","所有权限组信息");
        return "sysmanagement/rolemanagement/allrole";
    }

    @RequestMapping("/toAddRole")
    @RequiresPermissions("roleInfo:toAddRole")
    public String toAdd(Model model, HttpSession session){
        List<Permission> per=permissionService.getAll();
        session.setAttribute("session",per);
        model.addAttribute("title","添加权限组");
        return "sysmanagement/rolemanagement/addrole";
    }

    @RequestMapping("/addRole")
    public String add(Role role,Permission permission){
        //往中间表添加数据
//        Set<Role> roles=new HashSet<>();
//        roles.add(role);
//        permission.setRoles(roles);
//        Set<Permission> permissions=new HashSet<>();
//        permissions.add(permission);
//        role.setPermissions(permissions);
        permission.addRoles(role);
        role.addPermissions(permission);
        roleService.add(role);
        return "redirect:/role/allRole";
    }


    @RequestMapping("/toUpdateRole/{id}")
    public String toUpdate(Model model,@PathVariable("id") int roleID){
        Role role=roleService.getRoleByID(roleID);
        Set<Permission> permissions=role.getPermissions();
        model.addAttribute("permission",permissions);
        model.addAttribute("role",role);
        model.addAttribute("cap","修改权限组信息");
        model.addAttribute("title","修改权限组信息");
        return "sysmanagement/rolemanagement/updaterole";
    }

    @RequestMapping("/updateRole")
    public String update(Role role){
        roleService.updateRole(role);
        return "redirect:/role/allRole";
    }

    @RequestMapping("/deleteRole/{id}")
    public String delete(@PathVariable("id") int roleID){
        roleService.deleteById(roleID);
        return "redirect:/role/allRole";
    }

    @RequestMapping("/toSearchRole")
    @RequiresPermissions("roleInfo:toSearchRole")
    public String toSearch(Model model){
        model.addAttribute("title","查找权限组信息");
        return "sysmanagement/rolemanagement/searchrole";
    }

    @RequestMapping("/searchRole")
    public String search(Model model,String role_name){
        List<Role> roles=roleService.findRoleByName(role_name);
        Role role;
        List<Permission> permissionList=new ArrayList();
        Set<Permission> permissions = null;
        for (int i=0;i<roles.size();i++){
            role=roles.get(i);
            permissions=role.getPermissions();
        }
        permissionList.addAll(permissions);
        model.addAttribute("permission",permissionList);
        model.addAttribute("role",roles);
        model.addAttribute("title","查找权限组信息");
        return "sysmanagement/rolemanagement/searchrole";
    }

    @RequestMapping("/toroleAddper/{id}")
    public String roleadd(Model model,@PathVariable("id") int roleID,HttpSession session){
        Role role=roleService.getRoleByID(roleID);
        List<Permission> per=permissionService.getAll();
        session.setAttribute("session",per);
        model.addAttribute("role",role);
        model.addAttribute("cap","添加权限");
        model.addAttribute("title","添加权限");
        return "sysmanagement/rolemanagement/roleAddper";
    }

    @RequestMapping("/roleAddper")
    public String addper(Role role,Permission permission){
        //添加关联表的数据
//        Set<Role> roles=new HashSet<>();
//        roles.add(role);
//        permission.setRoles(roles);
//        Set<Permission> permissions=new HashSet<>();
//        permissions.add(permission);
//        role.setPermissions(permissions);
        //通过addPermissions(permissions)实现添加新的权限
        permission.addRoles(role);
        role.addPermissions(permission);
        roleService.add(role);
        return "redirect:/role/allRole";
    }

}
