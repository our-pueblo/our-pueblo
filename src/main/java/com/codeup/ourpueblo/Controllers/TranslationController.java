package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.Models.Translation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TranslationController {
    @GetMapping("/translate/request")
    public String requestTranslation(){
        return "request_translation";
    }

    @GetMapping("/translate/viewall")
    public String viewAllTranslations(){
        return "translate_viewall";
    }

    @GetMapping("/translate/view")
    public String viewTranslation(@RequestParam long id){
        return "view_translation";
    }

    @GetMapping("/translate/{id}")
    public String translate(@PathVariable long id, Model model){
        model.addAttribute("request_id", id);
        return "translate";
    }

    //TODO Write translation repository
    @PostMapping("/translate")
    public String submitTranslation (@ModelAttribute Translation translation){
        //TODO Come back after repository
        return "/";
    }

    @PostMapping("/translate/delete")
    public String deleteTranslation(@RequestParam long deleteID){
        //TODO Come back after repository
        return "/";
    }

    @PostMapping ("/translate/edit")
    public String editTranslation(@ModelAttribute Translation editedTranslation){
        //TODO Come back after repository
        return "/";
    }
}
