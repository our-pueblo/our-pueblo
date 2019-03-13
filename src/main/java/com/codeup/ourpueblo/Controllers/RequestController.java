package com.codeup.ourpueblo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {
    @GetMapping("/request")
    public String makeRequest(){
        return "request";
    }

    @GetMapping("/request/viewall")
    public String viewAllUntranslated (){
        return "view_all_untranslated";
    }
}
