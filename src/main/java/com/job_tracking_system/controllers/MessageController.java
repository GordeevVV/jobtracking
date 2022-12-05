package com.job_tracking_system.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.job_tracking_system.entity.BaseResponse;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracking")
public class MessageController {
    @Autowired
    PersonJpaRepository personJpaRepository;
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;

    @GetMapping
    public BaseResponse showStatus() {
        return new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS);
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String login, @RequestParam String password, @RequestParam String position, Model model) {
        Person user = new Person(login, password, position);

        // save user to database
        personJpaRepository.save(user);

        model.addAttribute("user", user);
        return "registration-success";
    }
    @PostMapping("/authorize")
    public String authorization(@RequestParam String login, @RequestParam String password, Model model){
        Person person = personJpaRepository.findPersonByLoginAndPassword(login, password);
        if(person!= null) {
            return "authorize-success";
        }else
            return "authorize-failed";
    }
    @GetMapping("/getinfo")
    public JsonElement getInfo(@RequestParam String login, @RequestParam String password, Model model){
        JsonElement jsonElement=new JsonObject();

        return jsonElement;
    }
}
