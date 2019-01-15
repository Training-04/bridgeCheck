package group.bridge.web.controller;

import group.bridge.web.entity.User;
import group.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.*;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/allUser")
    public String getAllUser(Model model){
        List<User> lists=userService.getAll();
        model.addAttribute("user",lists);
        return "demo/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "demo/addUser";
    }

    @RequestMapping("/add")
    public String add(User user){
        userService.add(user);
        return "redirect:/allUser";
    }


    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(Model model,@PathVariable("id") int userID){
        User user=userService.getUserByID(userID);
        model.addAttribute("user",user);
        model.addAttribute("cap","修改用户信息");
        return "demo/updateUser";
    }


    @RequestMapping("/update")
    public String update(User user){
        userService.updateUser(user);
        return "redirect:/allUser";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int userID){
        userService.deleteById(userID);
        return "redirect:/allUser";
    }

}
