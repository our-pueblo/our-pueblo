package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.DepartmentRepository;
import com.codeup.ourpueblo.Models.Department;
import com.codeup.ourpueblo.Models.Request;
import com.codeup.ourpueblo.Models.Request_Status;
import com.codeup.ourpueblo.Models.User;
import com.codeup.ourpueblo.RequestRepository;
import com.codeup.ourpueblo.Request_StatusRepository;
import com.codeup.ourpueblo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.Date;

import static com.codeup.ourpueblo.Controllers.GetSpanish.getSpanish;
import static com.codeup.ourpueblo.Controllers.TestScraper.scrapeText;

@Controller
public class RequestController {
    Date date = new Date();

    private final RequestRepository requestDao;

    private final UserRepository userDao;

    private final Request_StatusRepository requestStatusDao;

    private final DepartmentRepository departmanetDao;

    public RequestController(RequestRepository requestDao, UserRepository userDao, Request_StatusRepository requestStatusDao, DepartmentRepository departmanetDao){
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.requestStatusDao = requestStatusDao;
        this.departmanetDao = departmanetDao;
    }

    @GetMapping("/request")
    public String makeRequest(Model model){
        model.addAttribute("request", new Request());
        return "request";
    }

    @PostMapping("/request")
    public String addRequest (@ModelAttribute Request request) throws Exception {
        //Get url from request
        String url = request.getWeb_page();
        //use scrapeText method on URL to get untranslated text
        String untranslated = (String) scrapeText(url);
        //set request.untranslated_text to scraped text
        request.setUntranslated_text(untranslated);
        //use getSpanish to translate scraped text (en -> sp)
        String translated = getSpanish(untranslated);
        //set request.google_translate to translated text
        request.setGoogle_translate(translated);

        User testUser = userDao.findOne(1L);
        request.setUser_id(testUser);
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        request.setTime(ts);
        Request_Status testStatus = requestStatusDao.findOne(101L);
        request.setStatus(testStatus);
        Department testDepartment = departmanetDao.findOne(1L);
        request.setDepartment_id(testDepartment);
        Request newRequest = requestDao.save(request);
        return "index";
    }

    @GetMapping("/request/viewall")
    public String viewAllUntranslated (){
        return "view_all_untranslated";
    }
}
