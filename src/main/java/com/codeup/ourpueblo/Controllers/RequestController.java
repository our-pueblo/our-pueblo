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
    public String addRequest (@ModelAttribute Request request){
        request.setGoogle_translate("Translated Text goes here");
        request.setUntranslated_text("Untranslated text can go here");
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
