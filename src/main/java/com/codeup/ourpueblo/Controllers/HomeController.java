package com.codeup.ourpueblo.Controllers;


import com.codeup.ourpueblo.Models.User;
import com.codeup.ourpueblo.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserRepository userDao;

    public HomeController(UserRepository userDao){
        this.userDao=userDao;
    }
    @GetMapping("/")
    public String home (){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
