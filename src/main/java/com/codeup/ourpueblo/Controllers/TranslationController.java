package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.*;
import com.codeup.ourpueblo.Models.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class TranslationController {

    Date date = new Date();


    private final TranslationRepository translationDao;

    private final TranslationStatusRepository translationStatusDao;

    private final UserRepository userDao;

    private final RequestRepository requestDao;

    private final Request_StatusRepository requestStatusDao;

    public TranslationController(TranslationRepository translationDao, TranslationStatusRepository translationStatusDao, RequestRepository requestDao, UserRepository userDao, Request_StatusRepository requestStatusDao) {
        this.translationDao = translationDao;
        this.translationStatusDao = translationStatusDao;
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.requestStatusDao = requestStatusDao;
    }

    //How users request pages to translate, also see getmapping translate
    @GetMapping("/translate/request")
    public String requestTranslation(Model model) {
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        Iterable<Request> requests = requestDao.findAll();
        model.addAttribute("requests", requests);
        model.addAttribute("current", current);
        return "request_translation";
    }



    //Page where user does there translating, also see submitTranslation
//    TODO Change this to just use translation object, add request to the translation here and it should be fine, move user assignment from post to here
    @GetMapping("/translate/{id}")
    public String translate(@PathVariable long id, Model model) {
        User current = getCurrent();
        model.addAttribute("current", current);
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isActive()){
            return "redirect:/sorry";
        }
        Request translationRequest = requestDao.findOne(id);
        if (translationRequest.getStatus().getId() != 101) {
            return "request_translation";
        } else {
            Long reqID = translationRequest.getId();
            System.out.println(reqID);
            model.addAttribute("requestLong", reqID);
            model.addAttribute("translationRequest", translationRequest);
            model.addAttribute("newTranslation", new Translation());
            return "translate";
        }
    }

    @GetMapping("/translate")
    public String redirectTranslate() {
        return "redirect:/translate/request";
    }

    //    How users submit their translations
    @PostMapping("/translate")
    public String submitTranslation(@ModelAttribute Translation newTranslation, @RequestParam Long refRequest) {
        User current = getCurrent();
        newTranslation.setUser(current);
        Translation_Status translationStatus = translationStatusDao.findOne(101L);
        newTranslation.setStatus(translationStatus);
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        newTranslation.setTime(ts);
        System.out.println(refRequest);
        Request request = requestDao.findOne(refRequest);
        newTranslation.setRequest(request);
        Request_Status newStatus = requestStatusDao.findOne(201L);
        request.setStatus(newStatus);
        Translation savedTranslation = translationDao.save(newTranslation);
        Request changedRequest = requestDao.save(request);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/user/translations")
    public String userViewTranslations(Model model) {
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isActive()){
            return "redirect:/sorry";
        }
        Iterable<Translation> userTranslations = translationDao.findByUser(current);
        model.addAttribute("translationList", userTranslations);
        model.addAttribute("current", current);
        return "userViewTranslations";
    }

    @GetMapping("/translate/delete/{id}")
    public String confirmUserDelete(@PathVariable long id, Model model){
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isActive()){
            return "redirect:/sorry";
        }
        Translation check = translationDao.findOne(id);
        if(current!=check.getUser()||!current.isAdmin()){
            return "redirect:/user/dashboard";
        }
        model.addAttribute("deleteID", id);
        model.addAttribute("current", current);
        return "confirmDelete";
    }

    @PostMapping("/translate/delete")
    public String deleteTranslation(@RequestParam long deleteID, Model model) {
        Long id = deleteID;
        Translation deletingTranslation =  translationDao.findOne(id);
        Long requestID = deletingTranslation.getRequest().getId();
        Request editRequest = requestDao.findOne(requestID);
        Request_Status newStatus = requestStatusDao.findOne(101L);
        editRequest.setStatus(newStatus);
        requestDao.save(editRequest);
        translationDao.delete(id);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/translate/edit/{id}")
    public String userEditTranslation(@PathVariable long id, Model model){
        User current = getCurrent();
        if (loginCheck(current)){
            return "redirect:/login";
        }
        if (!current.isActive()){
            return "redirect:/sorry";
        }
        Translation editTranslation = translationDao.findOne(id);
        if(current!=editTranslation.getUser()){
            return "redirect:/user/dashboard";
        }
        model.addAttribute("editTranslation", editTranslation);
        model.addAttribute("current", current);
        return "editTranslation";
    }


    @PostMapping("/translate/edit")
    public String editTranslation(@ModelAttribute Translation editTranslation) {
        System.out.println(editTranslation.getId());
        System.out.println(editTranslation.getUser().getId());
        Translation_Status newStatus = translationStatusDao.findOne(101L);
        editTranslation.setStatus(newStatus);
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        editTranslation.setTime(ts);
        Translation savedTranslation = translationDao.save(editTranslation);
        return "redirect:/user/dashboard";
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
