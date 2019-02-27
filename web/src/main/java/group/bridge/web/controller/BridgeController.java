package group.bridge.web.controller;

import group.bridge.web.entity.Bridge;
import group.bridge.web.service.BridgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("bridge")
public class BridgeController {
    @Autowired
    private BridgeService bridgeService;

//    在添加传感器页面中自动加载下拉列表中的桥梁的名称
    @RequestMapping("/getBridges")
    public String getAllBridges(HttpSession session){
        List<Bridge> lists = bridgeService.getAll();
        session.setAttribute("bridges",lists);
        return "sensor/addSensors";
    }

    //    得到所有的桥梁
    @RequestMapping("/allBridges/{index}")
    //@RequiresPermissions("展示桥梁")
    public String getAllBridges(Model model,@PathVariable("index") Integer index){
        Pageable pageable = PageRequest.of(index-1,10);
        Page<Bridge> bPage = bridgeService.getAll(pageable);
        int count = bPage.getTotalPages();
        model.addAttribute("title","展示所有桥梁页面");
        //得到所有桥梁内容，在页面上遍历对象
        model.addAttribute("bridge",bPage.getContent());
        //当前页保存为pageIndex
        model.addAttribute("pageIndex",index);
        //得到数据总的数目
        model.addAttribute("pageTotal",count);
        return "bridge/allBridges";
    }

    //搜索相应名称的桥梁（中介）
    @RequestMapping("/toSearchBridge")
//    @RequiresPermissions("查找桥梁")
    public String toSearch(Model model){
        model.addAttribute("title","查询桥梁信息页面");
        return "bridge/searchBridge";
    }

    //搜索相应名称的桥梁
    @RequestMapping("/searchBridge")
    public String getByName(Model model,String bridge_name){
        List<Bridge> lists = bridgeService.getByName(bridge_name);
        model.addAttribute("title","查询传感器信息页面");
        model.addAttribute("bridge",lists);
        return "bridge/searchBridge";
    }

    //    添加桥梁
    @RequestMapping("/toAddBridges")
//    @RequiresPermissions("test")
    public String toAdd(Model model){
        model.addAttribute("title","添加桥梁页面");
        return "bridge/addBridges";
    }
    //  添加桥梁
    @RequestMapping("/addBridges")
    public String addBridge(Bridge bridge){
        bridgeService.add(bridge);
        return "redirect:/bridge/allBridges/1";
    }

    //在所有桥梁页面更改桥梁信息
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Bridge bridge = bridgeService.get(id);
        model.addAttribute("title","修改桥梁信息");
        model.addAttribute("bridge",bridge);
        return "bridge/updateBridge";
    }

    //在所有桥梁页面更改桥梁信息
    @RequestMapping("/updateBridge")
    public String update(Bridge bridge){
        bridgeService.update(bridge);
        return "redirect:/bridge/allBridges/1";
    }

    //删除桥梁
    @RequestMapping("/delBridge/{id}")
    public String delBridge(@PathVariable("id") Integer id) {
        bridgeService.deleteById(id);
        return "redirect:/bridge/allBridges/1";
    }
}
