package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.Models.User;
import com.codeup.ourpueblo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserRepository userDao;

    public UserController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String register (Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser (@ModelAttribute User user){
        user.setActive(true);
        User newUser = userDao.save(user);
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String login (){
        return "login";
    }

    @GetMapping("/user/profile")
    public String profile (){
        return "profile";
    }

    @GetMapping("/user/profile/edit")
    public String editProfile(){
        return "edit_profile";
    }

    @GetMapping("/user/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/admin/userlist")
    public String userlist(Model model){
        Iterable<User> userList = userDao.findAll();
        model.addAttribute("userList", userList);
        return "userlist";

    }
}
