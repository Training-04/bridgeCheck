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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public String add(Role role,@RequestParam(value = "permission_id") List<Integer>permission_id){
        //往中间表添加数据
//        Set<Role> roles=new HashSet<>();
//        roles.add(role);
//        permission.setRoles(roles);
//        Set<Permission> permissions=new HashSet<>();
//        permissions.add(permission);
//        role.setPermissions(permissions);
        Permission permission;
        for (int i=0;i<permission_id.size();i++){
            permission = permissionService.get(permission_id.get(i));
            permission.addRoles(role);
            role.addPermissions(permission);
        }
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

    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    public String update(Role role,@RequestParam("permission_id") List<Integer> permission_id){

        //对需要修改的role的permissions赋值
        Set<Permission> permissions = new HashSet<>();
        for (int i=0;i<permission_id.size();i++){
            permissions.add(permissionService.get(permission_id.get(i)));
        }
        role.setPermissions(permissions);
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
    public String search(Model model,String role_name,HttpSession session){
        List<Role> roles=roleService.findRoleByName(role_name);
        Role role;
        Set<Permission> permissions = null;
        for (int i=0;i<roles.size();i++){
            role=roles.get(i);
            permissions=role.getPermissions();
            model.addAttribute("permission",permissions);
        }
        //session.setAttribute("session",permissions);

        model.addAttribute("role",roles);
        model.addAttribute("title","查找权限组信息");
        return "sysmanagement/rolemanagement/searchrole";
    }

    @RequestMapping("/toroleAddper/{id}")
    public String roleadd(Model model,@PathVariable("id") int roleID,HttpSession session){
        Role role=roleService.getRoleByID(roleID);
        List<Permission> per=permissionService.getAll();
        Set<Permission> permissions=role.getPermissions();
        model.addAttribute("permission",permissions);
        session.setAttribute("session",per);
        model.addAttribute("role",role);
        model.addAttribute("cap","添加权限");
        model.addAttribute("title","添加权限");
        return "sysmanagement/rolemanagement/roleAddper";
    }

    @RequestMapping(value = "/roleAddper",method = RequestMethod.POST)
    public String addper(Role role,@RequestParam(value = "permission_id") List<Integer> permission_id,
                         @RequestParam("permissionid") List<Integer> permissionid){
        //添加关联表的数据
        //通过addPermissions(permissions)实现添加新的权限

        //获取role原有的权限，存储在permissions中
        Set<Permission> permissions = new HashSet<>();
        for (int i=0;i<permissionid.size();i++){
            permissions.add(permissionService.get(permissionid.get(i)));
        }
        //获取role新添加的权限
        Permission permission;
        for (int j=0;j<permission_id.size();j++){
            permission = permissionService.get(permission_id.get(j));
            //此时permissions存放的是原有权限加新的权限
            permissions.add(permission);
        }
        //role.setPermissions(permissions);
        //遍历Set<Object>中的数据，将原有的和新添加的权限存储到中间表中
        for (Permission per:permissions){
            permission = per;
            permission.addRoles(role);
            role.addPermissions(permission);
        }
        roleService.add(role);
        return "redirect:/role/allRole";
    }

    @RequestMapping("/toroleupdateper/{id}")
    public String roleupdateper(Model model,@PathVariable("id") int roleID,HttpSession session){
        Role role=roleService.getRoleByID(roleID);
        //List<Permission> per=permissionService.getAll();
        Set<Permission> permissions=role.getPermissions();
        //model.addAttribute("permission",permissions);
        session.setAttribute("session",permissions);
        model.addAttribute("role",role);
        model.addAttribute("cap","修改权限");
        model.addAttribute("title","修改权限");
        return "sysmanagement/rolemanagement/roleupdateper";
    }

    @RequestMapping(value = "/roleupdateper",method = RequestMethod.POST)
    public String rupdatep(Role role,@RequestParam(value = "permission_id") List<Integer> permission_id){
        //添加关联表的数据
        //通过addPermissions(permissions)实现添加新的权限
        //获取role修改后的权限
        Permission permission;
        for (int j=0;j<permission_id.size();j++){
            permission = permissionService.get(permission_id.get(j));
            permission.addRoles(role);
            role.addPermissions(permission);
        }
        roleService.add(role);
        return "redirect:/role/allRole";
    }

}
