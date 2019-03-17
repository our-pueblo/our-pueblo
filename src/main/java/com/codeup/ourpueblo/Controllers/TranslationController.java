package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.Models.Request;
import com.codeup.ourpueblo.Models.Translation;
import com.codeup.ourpueblo.Models.Translation_Status;
import com.codeup.ourpueblo.Models.User;
import com.codeup.ourpueblo.RequestRepository;
import com.codeup.ourpueblo.TranslationRepository;
import com.codeup.ourpueblo.TranslationStatusRepository;
import com.codeup.ourpueblo.UserRepository;
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

    public TranslationController(TranslationRepository translationDao, TranslationStatusRepository translationStatusDao, RequestRepository requestDao, UserRepository userDao) {
        this.translationDao = translationDao;
        this.translationStatusDao = translationStatusDao;
        this.requestDao = requestDao;
        this.userDao = userDao;
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
        String untranslatedText = translationRequest.getUntranslated_text();
        model.addAttribute("untranslatedText", untranslatedText);
        model.addAttribute("translationRequest", translationRequest);
        model.addAttribute("request_id", id);
        model.addAttribute("translation", new Translation());
        return "translate";
    }

    @GetMapping("/translate")
    public String redirectTranslate() {
        return "redirect:/translate/request";
    }

    @PostMapping("/translate")
    public String submitTranslation(@ModelAttribute Translation translation){
        User testUser = userDao.findOne(1L);
        translation.setUser(testUser);
        Translation_Status translationStatus = translationStatusDao.findOne(101L);
        if (translation.isFlag_problem()){
            //TODO Change this once status list is seeded
            translation.setStatus(translationStatus);
        }else {
            translation.setStatus(translationStatus);

        }
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        translation.setTime(ts);
        Translation newTranslation = translationDao.save(translation);
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
