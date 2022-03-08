package my.home.controllers;

import my.home.forms.SignUpForm;
import my.home.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private PersonService service;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String inputSignUp(SignUpForm form){
        service.signUp(form);
        System.out.println("Регистрация прошла успешно");
        return "redirect:/login";
    }
}
