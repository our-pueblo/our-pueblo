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

    // Register a new user
    @GetMapping("/register")
    public String register(Model model) {
        //Add a blank user object to the model
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User user, @RequestParam String password, @RequestParam String verifyPassword, Model model) {
        //Check to make sure the password and the verified password match
        if (!password.equals(verifyPassword)) {
            model.addAttribute("passwordError", true);
            return "redirect:/register";
            //Make the the email is properly formatted
        } else if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            model.addAttribute("emailError", true);
            return "redirect:/register";
        } else {
            //Super admin check: first user to create an account with the username admin is automatically set as an admin and has access to a few other unique features
            if (user.getUsername().equals("admin")) {
                user.setAdmin(true);
            } else {
                user.setAdmin(false);
            }
            //Set the user as active
            user.setActive(true);
            //Hash the password then set it as the user password
            String hashedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPass);
            //Save the user
            User newUser = userDao.save(user);
            //Head to the login page
            return "redirect:/login";
        }
    }

    @GetMapping("/user/profile")
    public String profile(Model model) {
        //Get the current user
       User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isActive()){
            return "redirect:/sorry";
        }
        model.addAttribute("currentUser", current);
        Iterable<Translation> list = translationDao.findByUser(current);
        int count = 0;
        for (Translation translation : list){
            count++;
        }
        model.addAttribute("totalTranslations", count);
        return "profile";
    }

    @GetMapping("/user/profile/edit")
    public String editProfile(Model model) {
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isActive()){
            return "redirect:/sorry";
        }
        model.addAttribute("currentUser", current);
        return "edit_profile";
    }

    @PostMapping("/user/profile/edit")
    public String saveEdit(@RequestParam String email, @RequestParam String phoneNumber, @RequestParam String password, @RequestParam String verifyPassword, @RequestParam Long userID){
        User user = userDao.findOne(userID);
        user.setEmail(email);
        user.setPhone_number(phoneNumber);
        if (password!=null){
            if (password.equals(verifyPassword)){
                String hash = passwordEncoder.encode(password);
                user.setPassword(hash);
            }else{
                return "redirect:/user/profile/edit";
            }
        }
        userDao.save(user);
        return "redirect:/user/dashboard";
    }
    //TODO Write edit profile mappings

    @GetMapping("/user/dashboard")
    public String dashboard(Model model) {
        //Get the current user
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isActive()){
            return "redirect:/sorry";
        }
        //Add it to the model, used to figure out what links they should see
        model.addAttribute("currentUser", current);
        return "dashboard";
    }

    //Admin feature, gives a list of all users
    @GetMapping("/admin/userlist")
    public String userlist(Model model) {
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isAdmin()){
            return "redirect:/user/dashboard";
        }
        //Find all users, put them in a list
        Iterable<User> userList = userDao.findAll();
        //Give the list to the model
        model.addAttribute("userList", userList);
        return "userlist";

    }

    //Admin feature, toggle the status of a user
    @GetMapping("/admin/userlist/toggle/{userID}")
    public String changeUserStatus(@PathVariable Long userID) {
        //Get the user based on their ID
        User user = userDao.findOne(userID);
        //If active make deactive and vice versa
        boolean status = user.isActive();
        if (status) {
            user.setActive(false);
        } else {
            user.setActive(true);
        }
        //Save the altered user
        User alteredUser = userDao.save(user);
        //Head back to the userlist
        return "redirect:/admin/userlist";
    }

    //Admin Feature: all users (except the super admin) start out as normal, they have to be made admins by other admins
    @GetMapping("/admin/userlist/makeadmin/{userID}")
    public String makeAdming(@PathVariable Long userID) {
        //Get the user based on their ID
        User user = userDao.findOne(userID);
        //Set them as an admin
        user.setAdmin(true);
        //Save the altered user
        User alteredUser = userDao.save(user);
        //Go back to the user list
        return "redirect:/admin/userlist";
    }

//    //Admin Feature: Delete a user
//    @GetMapping("/admin/userlist/delete/{userID}")
//    public String deleteUser(@PathVariable Long userID, Model model) {
//        //Find the user based on ID
//        User user = userDao.findOne(userID);
//        model.addAttribute("user", user);
//        return "deleteUser";
//    }
//    @PostMapping("/admin/userlist/delete")
//    public String confirmedDelete(@RequestParam long deleteID) {
//        //Delete the user
//        userDao.delete(deleteID);
//        return "redirect:/admin/userlist";
//    }

    //Get mapping for the in app seeder
    @GetMapping("/seed")
    public String seedTables(Model model) {
        //To get to this page logged in user must be a "super admin" the app is set up to take the first user that creates an account with the name of admin and make them the super admin
        User current = getCurrent();
        //If you aren't the super admin you get kicked back to the dashboard
        if (current.getUsername().equals("admin")) {
            model.addAttribute("currentUser", current);
            model.addAttribute("checkUser", "admin");
            return "initialSeed";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    //Admin feature: See all requests
    @GetMapping("/admin/requests")
    public String adminViewRequests(Model model) {
        //Get the current user
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        //If they aren't an admin kick them to the dashboard
        if (!current.isAdmin()) {
            return "redirect:/user/dashboard";
        } else {
            //Otherwise get a list of all requests and add them to the model
            Iterable<Request> requests = requestDao.findAll();
            model.addAttribute("requestList", requests);
            return "adminRequests";
        }
    }

    //Admin Feature: Delete a request
    @GetMapping("/admin/requests/delete/{id}")
    public String confirmRequestDelete(@PathVariable long id, Model model) {
        //Make sure the current user is an admin
        User current = getCurrent();
        if (!current.isAdmin()) {
            return "redirect:/user/dashboard";
        } else {
            //If they are, add the id of the request to the model then go to the confirm page
            model.addAttribute("id", id);
            return "confrimRequestDelete";
        }
    }

    //Continued from above
    @PostMapping("/admin/requests/delete")
    public String deleteRequest(@RequestParam long deleteID) {
        //Pull the request based on its id
        Request request = requestDao.findOne(deleteID);
        //If the status of the request is 101 that means it doesn't have a user translation and we can just delete it
        if (request.getStatus().getId() == 101) {
            requestDao.delete(request);
            return "redirect:/user/dashboard";
        } else {
            //If it has a status other than 101 though it has a translation and that needs to be deleted first
            //Find that translation by looking for one with the corresponding request
            Translation deleteTranslation = translationDao.findByRequest(request);
            //Delete it
            translationDao.delete(deleteTranslation);
            //Then delete the request
            requestDao.delete(request);
            return "redirect:/user/dashboard";
        }

    }

    //This is a seeder for the database you can run in app, run it once on initial set up or if you ever drop the db
    @PostMapping("/seed")
    public String seeded() {
        //Make a new request status object
        Request_Status seedStatus = new Request_Status();
        //Request_Status objects have manually set statuses AND IDs, here we set the first one
        seedStatus.setId(101);
        seedStatus.setStatus("Request submitted, awaiting user translation");
        //Save the status
        requestStatusDao.save(seedStatus);
        //Since we are overwriting all fields iof the status we can just reuse the object
        seedStatus.setId(201);
        seedStatus.setStatus("Translated by User");
        requestStatusDao.save(seedStatus);
        //Creating a blank department object
        Department department = new Department();
        //Setting the department name
        department.setName("Animal Care Services");
        //Saving the department
        departmentDao.save(department);
        //Because the IDs for departments autoincrement we need to make a new object for each department, otherwise they will just overwrite the last one
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
        //Creating a blank Translation_Status
        Translation_Status status = new Translation_Status();
        //Setting the ID
        status.setId(101);
        //Setting the status
        status.setStatus("Waiting for Admin Approval");
        //Save the status
        translationStatusDao.save(status);
        //Just like request status we set both the status and the ID so we can just recycle the object
        status.setId(201);
        status.setStatus("Approved by Admin");
        translationStatusDao.save(status);
        //Return to the dashboard
        return "redirect:/user/dashboard";
    }

    //Admin Feature: Get a list of all translations ready for deployment
    @GetMapping("/admin/deployment")
    public String deployment(Model model) {
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if(!current.isAdmin()){
            return "redirect:/user/dashboard";
        }
        //201 is the Translation_Status associated with user reviewed and admin approved translations
        //Find all translations with the status of 201 and add them to a list, then give that list to the model
        Translation_Status translation_status = translationStatusDao.findOne(201L);
        Iterable<Translation> translationsList = translationDao.findByStatus(translation_status);
        model.addAttribute("deploymentList", translationsList);
        return "deploymentList";
    }

    //Admin Feature: See all translations
    @GetMapping("/admin/translations")
    public String seeTranslations(Model model) {
        // Make sure the current user is an admin
        User current = getCurrent();
        if (!current.isAdmin()) {
            return "redirect:/user/dashboard";
        } else {
            //If they are get all translations with a status of 101, meaning user reviewed but not admin approved
            Translation_Status status = translationStatusDao.findOne(101L);
            Iterable<Translation> list = translationDao.findByStatus(status);
            model.addAttribute("list", list);
            return "adminViewTranslations";
        }
    }

    //Admin Feature: Approve a user translation for deployment
    @GetMapping("/admin/translations/approve/{id}")
    public String confirmApproval(@PathVariable long id, Model model) {
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isAdmin())
        //Store the ID of the translation
        model.addAttribute("id", id);
        return "confirmApproval";
    }

    //Continued from above
    @PostMapping("/admin/translations/approve/")
    public String approve(@RequestParam long approveID) {
        //Tak the ID anf find the translation
        Translation translation = translationDao.findOne(approveID);
        //Pull the Translation_Status 201, Reviewed by user and approved by admin
        Translation_Status status = translationStatusDao.findOne(201L);
        //Set the status
        translation.setStatus(status);
        //Save the translation
        translationDao.save(translation);
        return "redirect:/user/dashboard";
    }

    //Password recovery
    @GetMapping("/user/forgot")
    public String forgotPassword(Model model) {
        return "forgotPassword";
    }

    @PostMapping("/user/forgot")
    public String emailCheck(@RequestParam String email, Model model) {
        //Take in an email and check for it in the users table
        User user = userDao.findByEmail(email);
        if (user == null) {
            //If it's not there take them back to the form and pop up a message
            model.addAttribute("wrongEmail", true);
            return "forgotPassword";
        } else {
            //If it is there get the id of the account associated with it and move on to the next step
            if (!user.isActive()){
                return "redirect:/sorry";
            }
            Long userID = user.getId();
            model.addAttribute("userID", userID);
            return "verifySecurity";
        }
    }

    @PostMapping("/verifysecurity")
    public String verify(Model model, @RequestParam Long resetUser, @RequestParam String answer) {
        //Get the user object based on the id
        User user = userDao.findOne(resetUser);
        //Get the security answer for the account
        String security = user.getSecurity_question();
        //Check user submission against answer on record, if it matches go to the next step
        if (answer.equals(security)) {
            Long userID = user.getId();
            model.addAttribute("userID", userID);
            return "reset";
        } else {
            //If not go back and display a message
            model.addAttribute("wrongAnswer", true);
            model.addAttribute("user", resetUser);
            return "verifySecurity";
        }
    }


    @PostMapping("/reset/password")
    public String done(@RequestParam Long resetUser, @RequestParam String password, @RequestParam String verify, Model model) {
        //Make sure the new password and the verification match, if they do hash the new password, save it and take them to login
        if (password.equals(verify)) {
            String hashedPass = passwordEncoder.encode(password);
            User user = userDao.findOne(resetUser);
            user.setPassword(hashedPass);
            return "redirect:/login";
        } else {
            //If not go back and display a message
            model.addAttribute("wrong", true);
            model.addAttribute("user", resetUser);
            return "reset";
        }
    }

    @GetMapping("/sorry")
    public String sorry(){
        return "sorry";
    }


    private boolean loginCheck(User user){
        return user==null;
    }

    private User getCurrent(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User current = userDao.findOne(user.getId());
        return current;
    }


}
