package group.bridge.web.controller;

import group.bridge.web.entity.Permission;
import group.bridge.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PermissionController extends BaseController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/allPer")
    public String getAllPer(Model model, HttpSession session){
        List<Permission> lists=permissionService.getAll();
        model.addAttribute("per",lists);
        model.addAttribute("title","所有权限信息");
        session.setAttribute("session",lists);
        return "sysmanagement/permissionmanagement/allper";
    }


    @RequestMapping("/toAddPer")
    public String toAdd(Model model){
        model.addAttribute("title","添加权限");
        return "sysmanagement/permissionmanagement/addper";
    }

    @RequestMapping("/addPer")
    public String add(Permission permission){
        permissionService.add(permission);
        return "redirect:/allPer";
    }


    @RequestMapping("/toUpdatePer/{id}")
    public String toUpdate(Model model,@PathVariable("id") int permissionID){
        Permission per=permissionService.getPerByID(permissionID);
        model.addAttribute("per",per);
        model.addAttribute("cap","修改权限信息");
        model.addAttribute("title","修改权限信息");
        return "sysmanagement/permissionmanagement/updateper";
    }


    @RequestMapping("/updatePer")
    public String update(Permission permission){
        permissionService.updatePer(permission);
        return "redirect:/allPer";
    }

    @RequestMapping("/deletePer/{id}")
    public String delete(@PathVariable("id") int permissionID){
        permissionService.deleteById(permissionID);
        return "redirect:/allPer";
    }

    @RequestMapping("/toSearchPer")
    public String toSearch(Model model){
        model.addAttribute("title","查找权限信息");
        return "sysmanagement/permissionmanagement/searchper";
    }

    @RequestMapping("/searchPer")
    public String search(Model model,String permission_name){
        List<Permission> per=permissionService.findPerByName(permission_name);
        model.addAttribute("per",per);
        model.addAttribute("title","查找权限信息");
        return "sysmanagement/permissionmanagement/searchper";
    }
}
