package com.codeup.ourpueblo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/register")
    public String register (){
        return "register";
    }

    @GetMapping("/user/profile")
    public String profile (){
        return "profile";
    }

    @GetMapping("/user/profile/edit")
    public String editProfile(){
        return "edit_profile";
    }
}
