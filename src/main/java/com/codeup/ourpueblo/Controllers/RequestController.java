package com.codeup.ourpueblo.Controllers;

import com.codeup.ourpueblo.DepartmentRepository;
import com.codeup.ourpueblo.Models.Department;
import com.codeup.ourpueblo.Models.Request;
import com.codeup.ourpueblo.Models.Request_Status;
import com.codeup.ourpueblo.Models.User;
import com.codeup.ourpueblo.RequestRepository;
import com.codeup.ourpueblo.Request_StatusRepository;
import com.codeup.ourpueblo.UserRepository;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

import static com.codeup.ourpueblo.Controllers.TestScraper.scrapeText;

@Controller
public class RequestController {

// Used to call the actual API key from application properties
    private String apikey;

    public String getSpanish(String text) throws Exception {
        // Instantiates a client
        System.out.println(apikey);
        Translate translate = TranslateOptions.newBuilder().setApiKey(apikey).build().getService();        // Enter sample text to be translated


        // Translates some text into Spanish
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage("en"),
                        Translate.TranslateOption.targetLanguage("es"));

        // Print for comparison
        System.out.printf("Text: %s%n", text);
        System.out.printf("Translation: %s%n", translation.getTranslatedText());


        return translation.getTranslatedText();
    }

    Date date = new Date();

    private final RequestRepository requestDao;

    private final UserRepository userDao;

    private final Request_StatusRepository requestStatusDao;
    private final DepartmentRepository departmanetDao;

    public RequestController(RequestRepository requestDao, UserRepository userDao, Request_StatusRepository requestStatusDao, DepartmentRepository departmanetDao, @Value("${api-key}") String apikey) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.requestStatusDao = requestStatusDao;
        this.departmanetDao = departmanetDao;
        this.apikey = apikey;
    }
//Mapping for the Make A Request page
    @GetMapping("/request")
    public String makeRequest(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User current = userDao.findOne(user.getId());
        if (current==null){
            return "redirect:/login";
        }
        if(!current.isAdmin()){
            return "redirect:/user/dashboard";
        }
        //Create a new instance of of a request object and add it to the model
        model.addAttribute("request", new Request());
        //Get a list of all the departments, the request page has a drop down that is populated by this
        Iterable<Department> list = departmanetDao.findAll();
        //Add the list to the model
        model.addAttribute("dList", list);
        model.addAttribute("current", current);
        //Load the page
        return "request";
    }

    @PostMapping("/request")
    public String addRequest(@ModelAttribute Request request, @RequestParam long department) throws Exception {
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
        //Get the currently logged in user and store it
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User current = userDao.findOne(user.getId());
        //Set the user_id for the request to the current user
        request.setUser_id(current);
        //Get the current time
        long time = date.getTime();
        //Make a timestamp using the current time
        Timestamp ts = new Timestamp(time);
        // Add the timestamp to the request object
        request.setTime(ts);
        // Find the default Request_Status, 101 is Submitted but not user reviewed
        Request_Status testStatus = requestStatusDao.findOne(101L);
        //Set the Request_Status
        request.setStatus(testStatus);
        //The request form only gives back the ID of the selected department, here we pull the actual Department object that corresponds to the ID
        Department setDepartment = departmanetDao.findOne(department);
        //Set the department of the request
        request.setDepartment_id(setDepartment);
        //Save the request
        Request newRequest = requestDao.save(request);
        //Go back to the dashboard
        return "redirect:/user/dashboard";
    }


}


// [END translate_quickstart]
