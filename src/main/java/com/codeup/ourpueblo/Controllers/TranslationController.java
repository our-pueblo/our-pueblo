package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.*;
import com.codeup.ourpueblo.Models.*;
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

    @GetMapping("/translate/request")
    public String requestTranslation(Model model) {
        Iterable<Request> requests = requestDao.findAll();
        model.addAttribute("requests", requests);
        return "request_translation";
    }

    @GetMapping("/translate/viewall")
    public String viewAllTranslations() {
        return "translate_viewall";
    }

    @GetMapping("/translate/view")
    public String viewTranslation(@RequestParam long id) {
        return "view_translation";
    }

    @GetMapping("/translate/{id}")
    public String translate(@PathVariable long id, Model model) {
        Request translationRequest = requestDao.findOne(id);
        if (translationRequest.getStatus().getId()!=101){
            return "request_translation";
        }else {
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

    @PostMapping("/translate")
    public String submitTranslation(@ModelAttribute Translation newTranslation, @RequestParam Long refRequest){
        User testUser = userDao.findOne(1L);
        newTranslation.setUser(testUser);
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
        return "index";
    }

    //TODO Write translation repository

    @PostMapping("/translate/delete")
    public String deleteTranslation(@RequestParam long deleteID) {
        //TODO Come back after repository
        return "index";
    }

    @PostMapping("/translate/edit")
    public String editTranslation(@ModelAttribute Translation editedTranslation) {
        //TODO Come back after repository
        return "index";
    }
}
