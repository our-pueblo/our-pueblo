package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.Models.User;
import com.codeup.ourpueblo.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// Just for the login page
@Controller
public class AuthenticationController {
    private final UserRepository userDao;

    public AuthenticationController(UserRepository userDao){
        this.userDao = userDao;
    }
    @GetMapping("/login")
    public String showLoginForm() {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "redirect:/user/dashboard";
        }else {
            return "login";
        }
    }
}