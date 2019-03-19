package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.Models.User;
import com.codeup.ourpueblo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register (Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser (@ModelAttribute User user, @RequestParam String password, @RequestParam String verifyPassword, Model model){
        if (!password.equals(verifyPassword)){
            model.addAttribute("passwordError", true);
            return "redirect:/register";
        }else if (!user.getEmail().contains("@")||!user.getEmail().contains(".")){
            model.addAttribute("emailError", true);
            return "redirect:/register";
        }else {
            user.setActive(true);
            String hashedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPass);
            User newUser = userDao.save(user);
            return "redirect:/login";
        }
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

    @GetMapping("/admin/userlist/toggle/{userID}")
    public String changeUserStatus (@PathVariable Long userID){
        User user = userDao.findOne(userID);
        boolean status = user.isActive();
        if (status){
            user.setActive(false);
        }else {
            user.setActive(true);
        }
        User alteredUser = userDao.save(user);
        return "redirect:/admin/userlist";
    }

    @GetMapping("/admin/userlist/makeadmin/{userID}")
    public String makeAdming (@PathVariable Long userID){
        User user = userDao.findOne(userID);
        user.setAdmin(true);
        User alteredUser = userDao.save(user);
        return "redirect:/admin/userlist";
    }

    @GetMapping("/admin/userlist/delete/{userID}")
    public String deleteUser(@PathVariable Long userID, Model model){
        User user = userDao.findOne(userID);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @PostMapping("/admin/userlist/delete")
    public String confirmedDelete(@RequestParam long deleteID){
        userDao.delete(deleteID);
        return "redirect:/admin/userlist";
    }

}
