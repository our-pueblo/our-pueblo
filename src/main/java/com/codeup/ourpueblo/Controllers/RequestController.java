package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.RequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {

    private final RequestRepository requestDao;

    public RequestController(RequestRepository requestDao){
        this.requestDao = requestDao;
    }

    @GetMapping("/request")
    public String makeRequest(){
        return "request";
    }

    @GetMapping("/request/viewall")
    public String viewAllUntranslated (){
        return "view_all_untranslated";
    }
}
