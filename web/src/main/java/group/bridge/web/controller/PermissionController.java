package group.bridge.web.controller;

import group.bridge.web.entity.Permission;
import group.bridge.web.service.PermissionService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/allPer/{index}")
    //logical= Logical.OR的意思是，只要存在value={"所有权限信息","修改权限信息","删除权限信息"}的一个，就可以拥有这个类的权限
    @RequiresPermissions(value={"所有权限信息","修改权限信息","删除权限信息"},logical= Logical.OR)
    public String getAllPer(Model model, HttpSession session,@PathVariable("index") Integer index){
        //List<Permission> lists=permissionService.getAll();
        //model.addAttribute("per",lists);
        //session.setAttribute("session",lists);
        //分页所用
        Pageable pageable = PageRequest.of(index-1,10);
        Page<Permission> bPage = permissionService .getAll(pageable);
        int count = bPage.getTotalPages();
        //分页所用
        //得到所有权限组内容，在页面上遍历对象
        model.addAttribute("per",bPage.getContent());
        model.addAttribute("title","所有权限信息");
        session.setAttribute("session",bPage.getContent());
        //当前页保存为pageIndex
        model.addAttribute("pageIndex",index);
        //得到数据总的数目
        model.addAttribute("pageTotal",count);
        return "sysmanagement/permissionmanagement/allper";
    }

    @RequestMapping("/toAddPer")
    @RequiresPermissions("添加权限信息")

    public String toAdd(Model model){
        model.addAttribute("title","添加权限");
        return "sysmanagement/permissionmanagement/addper";
    }

    @RequestMapping("/addPer")
    public String add(Permission permission){
        permissionService.add(permission);
        return "redirect:/permission/allPer";
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
        return "redirect:/permission/allPer";
    }

    @RequestMapping("/deletePer/{id}")
    public String delete(@PathVariable("id") int permissionID){
        permissionService.deleteById(permissionID);
        return "redirect:/permission/allPer";
    }

    @RequestMapping("/toSearchPer")
    @RequiresPermissions("查询权限信息")
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
