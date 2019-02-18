package group.bridge.web.controller;

import group.bridge.web.entity.Bridge;
import group.bridge.web.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BridgeController {
    @Autowired
    private BridgeService bridgeService;

//    在添加传感器页面中自动加载桥梁的名称
    @RequestMapping("/getBridges")
    public String getAllBridges(HttpSession session){
        List<Bridge> lists = bridgeService.getAll();
        session.setAttribute("bridges",lists);
        return "sensor/addSensors";
    }

    //    得到所有的桥梁
    @RequestMapping("/allBridges")
    public String getAllBridges(Model model){
        List<Bridge> lists = bridgeService.getAll();
        model.addAttribute("bridge",lists);
        return "bridge/allBridges";
    }

    //    添加桥梁
    @RequestMapping("/toAddBridges")
    public String toAdd(Model model){
        model.addAttribute("title","添加桥梁页面");
        return "bridge/addBridges";
    }
    //  添加桥梁
    @RequestMapping("/addBridges")
    public String addBridge(Bridge bridge){
        bridgeService.add(bridge);
        return "redirect:/allBridges";
    }

    //删除桥梁
    @RequestMapping("/delBridge/{id}")
    public String delWarn_record(@PathVariable("id") Integer id) {
        bridgeService.deleteById(id);
        return "redirect:/allBridges";
    }
}
