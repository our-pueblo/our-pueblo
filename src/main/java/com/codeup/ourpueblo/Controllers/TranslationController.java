package com.codeup.ourpueblo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TranslationController {
    @GetMapping("/translate/request")
    public String requestTranslation(){
        return "request_translation";
    }

    @GetMapping("/translate/{id}")
    public String translate(@PathVariable long id, Model model){
        model.addAttribute("request_id", id);
        return "translate";
    }
}
