package group.bridge.web.controller;


<<<<<<< HEAD
import group.bridge.web.entity.Sensor;
import group.bridge.web.service.SensorService;
=======
import group.bridge.web.service.PersonService;
>>>>>>> 09be948137f6f6fcaeb8e523bf8ed45d2960b2b9
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
>>>>>>> 09be948137f6f6fcaeb8e523bf8ed45d2960b2b9
import java.util.List;


@Controller
<<<<<<< HEAD
@RequestMapping("web")
public class DemoController {
    @Autowired
    private SensorService sensorService;
    @RequestMapping("/")
    public String demo(Model model) {
//        页面的标题为test
=======
public class DemoController extends BaseController {
    @Autowired
    PersonService personService;
    @RequestMapping("/")
    public String demo(Model model, HttpServletRequest request) {
        HttpSession session= request.getSession();
        List<String> stringList=new ArrayList<String>();
        stringList.add("1");
        stringList.add("2");
        session.setAttribute("list",stringList);
>>>>>>> 09be948137f6f6fcaeb8e523bf8ed45d2960b2b9
        model.addAttribute("title","test");
        return "demo/fragment1";
    }

<<<<<<< HEAD
    //展示所有传感器
    @RequestMapping("/list")
    public String getAllSensor(Model model){
        List<Sensor> lists = sensorService.getAllSensor();
        model.addAttribute("sensors",lists);
        return "CRUD/list";
    }

    //更改
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model, @PathVariable("id") Integer id){
        Sensor sensor = sensorService.getSensorById(id);
        model.addAttribute("sensor",sensor);
        return "CRUD/update";
    }

    //更改阈值
    @RequestMapping("/update")
    public String update(Sensor sensor){
        sensorService.updateSensor(sensor);
        return "redirect:/web/list";
    }
=======
    @RequestMapping("/siderbar")
    public String siderBar(Model model){
        model.addAttribute("menu","test");
        return "demo/fragment1";
    }

>>>>>>> 09be948137f6f6fcaeb8e523bf8ed45d2960b2b9
}
