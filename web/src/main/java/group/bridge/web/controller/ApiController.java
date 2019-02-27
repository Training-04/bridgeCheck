package group.bridge.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.bridge.web.entity.Bridge;
import group.bridge.web.service.BridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    BridgeService bridgeService;
    @RequestMapping("/warn")
    public boolean warn(){
        return true;
    }
    @RequestMapping("/single")
    public String single(){
        String str="";
        Bridge bridge2=null;
        ObjectMapper mapper=new ObjectMapper();
        try{
            //Bridge bridge=bridgeService.get(1);
            bridge2=bridgeService.get2(1);
            System.out.println(bridge2);
//            str=mapper.writeValueAsString(bridge2);
            System.out.println(str);
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return str;
    }
    @RequestMapping("/array")
    public String array(){
        String str="";
        ObjectMapper mapper=new ObjectMapper();
        try{
            List<Bridge> bridges=bridgeService.getAll();
            List<Bridge> bridges2=bridgeService.getAllNotCascade();

            str=mapper.writeValueAsString(bridges);
            System.out.println(str);
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return str;
    }
}
