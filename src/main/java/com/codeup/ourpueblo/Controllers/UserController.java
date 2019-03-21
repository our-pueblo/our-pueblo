package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.*;
import com.codeup.ourpueblo.Models.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;

    private final Request_StatusRepository requestStatusDao;

    private final TranslationStatusRepository translationStatusDao;

    private final DepartmentRepository departmentDao;

    private final RequestRepository requestDao;

    private final TranslationRepository translationDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, Request_StatusRepository requestStatusDao, TranslationStatusRepository translationStatusDao, DepartmentRepository departmentDao, RequestRepository requestDao, TranslationRepository translationDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.requestStatusDao = requestStatusDao;
        this.translationStatusDao = translationStatusDao;
        this.departmentDao = departmentDao;
        this.requestDao = requestDao;
        this.translationDao = translationDao;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User user, @RequestParam String password, @RequestParam String verifyPassword, Model model) {
        if (!password.equals(verifyPassword)) {
            model.addAttribute("passwordError", true);
            return "redirect:/register";
        } else if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            model.addAttribute("emailError", true);
            return "redirect:/register";
        } else {
            if (user.getUsername().equals("admin")) {
                user.setAdmin(true);
            }else {
                user.setAdmin(false);
            }
            user.setActive(true);
            String hashedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPass);
            User newUser = userDao.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/user/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/user/profile/edit")
    public String editProfile() {
        return "edit_profile";
    }

    @GetMapping("/user/dashboard")
    public String dashboard(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User current = userDao.findOne(user.getId());
        model.addAttribute("currentUser", current);
        return "dashboard";
    }

    @GetMapping("/admin/userlist")
    public String userlist(Model model) {
        Iterable<User> userList = userDao.findAll();
        model.addAttribute("userList", userList);
        return "userlist";

    }

    @GetMapping("/admin/userlist/toggle/{userID}")
    public String changeUserStatus(@PathVariable Long userID) {
        User user = userDao.findOne(userID);
        boolean status = user.isActive();
        if (status) {
            user.setActive(false);
        } else {
            user.setActive(true);
        }
        User alteredUser = userDao.save(user);
        return "redirect:/admin/userlist";
    }

    @GetMapping("/admin/userlist/makeadmin/{userID}")
    public String makeAdming(@PathVariable Long userID) {
        User user = userDao.findOne(userID);
        user.setAdmin(true);
        User alteredUser = userDao.save(user);
        return "redirect:/admin/userlist";
    }

    @GetMapping("/admin/userlist/delete/{userID}")
    public String deleteUser(@PathVariable Long userID, Model model) {
        User user = userDao.findOne(userID);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @PostMapping("/admin/userlist/delete")
    public String confirmedDelete(@RequestParam long deleteID) {
        userDao.delete(deleteID);
        return "redirect:/admin/userlist";
    }


    @GetMapping("/seed")
    public String seedTables(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User current = userDao.findOne(user.getId());
        if (current.getUsername().equals("admin")) {
            model.addAttribute("currentUser", current);
            model.addAttribute("checkUser", "admin");
            return "initialSeed";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    @GetMapping("/admin/requests")
    public String adminViewRequests(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User current = userDao.findOne(user.getId());
        if (!current.isAdmin()) {
            return "redirect:/user/dashboard";
        } else {
            Iterable<Request> requests = requestDao.findAll();
            model.addAttribute("requestList", requests);
            return "adminRequests";
        }
    }

    @GetMapping("/admin/requests/delete/{id}")
    public String confirmRequestDelete(@PathVariable long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User current = userDao.findOne(user.getId());
        if (!current.isAdmin()) {
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("id", id);
            return "confrimRequestDelete";
        }
    }

    @PostMapping("/admin/requests/delete")
    public String deleteRequest(@RequestParam long deleteID) {
        Request request = requestDao.findOne(deleteID);
        if (request.getStatus().getId() == 101) {
            requestDao.delete(request);
            return "redirect:/user/dashboard";
        } else {
            Translation deleteTranslation = translationDao.findByRequest(request);
            translationDao.delete(deleteTranslation);
            requestDao.delete(request);
            return "redirect:/user/dashboard";
        }

    }

    @PostMapping("/seed")
    public String seeded() {
        Request_Status seedStatus = new Request_Status();
        seedStatus.setId(101);
        seedStatus.setStatus("Request submitted, awaiting user translation");
        requestStatusDao.save(seedStatus);
        seedStatus.setId(201);
        seedStatus.setStatus("Translated by User");
        requestStatusDao.save(seedStatus);
        Department department = new Department();
        department.setName("Animal Care Services");
        departmentDao.save(department);
        Department department2 = new Department();
        department2.setName("Arts & Cultural Affairs");
        departmentDao.save(department2);
        Department department3 = new Department();
        department3.setName("City Council");
        departmentDao.save(department3);
        Department department4 = new Department();
        department4.setName("Parks and Recreation");
        departmentDao.save(department4);
        Department department5 = new Department();
        department5.setName("Finance");
        departmentDao.save(department5);
        Department department6 = new Department();
        department6.setName("Health");
        departmentDao.save(department6);
        Department department7 = new Department();
        department7.setName("Police Department");
        departmentDao.save(department7);
        Department department8 = new Department();
        department8.setName("Other");
        departmentDao.save(department8);
        Translation_Status status = new Translation_Status();
        status.setId(101);
        status.setStatus("Waiting for Admin Approval");
        translationStatusDao.save(status);
        status.setId(201);
        status.setStatus("Approved by Admin");
        translationStatusDao.save(status);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/admin/deployment")
    public String deployment(Model model){
        Translation_Status translation_status = translationStatusDao.findOne(201L);
        Iterable<Translation> translationsList = translationDao.findByStatus(translation_status);
        model.addAttribute("deploymentList", translationsList);
        return "deploymentList";
    }

    @GetMapping("/admin/translations")
    public String seeTranslations (Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User current = userDao.findOne(user.getId());
        if (!current.isAdmin()){
            return "redirect:/user/dashboard";
        } else {
            Translation_Status status = translationStatusDao.findOne(101L);
            Iterable<Translation> list = translationDao.findByStatus(status);
            model.addAttribute("list", list);
            return "adminViewTranslations";
        }
    }

    @GetMapping("/admin/translations/approve/{id}")
    public String confirmApproval (@PathVariable long id, Model model){
        model.addAttribute("id", id);
        return "confirmApproval";
    }

    @PostMapping("/admin/translations/approve/")
    public String approve (@RequestParam long approveID){
        Translation translation = translationDao.findOne(approveID);
        Translation_Status status = translationStatusDao.findOne(201L);
        translation.setStatus(status);
        translationDao.save(translation);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/user/forgot")
    public String forgotPassword(Model model){
        return "forgotPassword";
    }

    @PostMapping("/user/forgot")
    public String emailCheck (@RequestParam String email, Model model){
        User user = userDao.findByEmail(email);
        if (user==null){
            model.addAttribute("wrongEmail", true);
            return "forgotPassword";
        }
        else {
            Long userID = user.getId();
            model.addAttribute("userID", userID);
            return "verifySecurity";
        }
    }

    @PostMapping("/verifysecurity")
    public String verify (Model model, @RequestParam Long resetUser, @RequestParam String answer){
        User user = userDao.findOne(resetUser);
        String security = user.getSecurity_question();
        if (answer.equals(security)){
            Long userID = user.getId();
            model.addAttribute("userID", userID);
            return "reset";
        } else {
            model.addAttribute("wrongAnswer", true);
            model.addAttribute("user", resetUser);
            return "verifySecurity";
        }
    }


    @PostMapping("/reset/password")
    public String done(@RequestParam Long resetUser, @RequestParam String password, @RequestParam String verify, Model model){
        if (password.equals(verify)){
            String hashedPass = passwordEncoder.encode(password);
            User user = userDao.findOne(resetUser);
            user.setPassword(hashedPass);
            return "redirect:/login";
        }else {
            model.addAttribute("wrong", true);
            model.addAttribute("user", resetUser);
            return "reset";
        }
    }



}
