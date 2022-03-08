package my.home.controllers;

import my.home.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @Value("${my.property}") //так можно вытащить значение из файла со свойствами "Application.properties"
    private String myProperty;

    @GetMapping("/welcome")
    public String getLoginPage(ModelMap model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        model.addAttribute("firstName", details.getUser().getFirstName());
        model.addAttribute("lastName", details.getUser().getLastName());
        System.out.println("вход на домашнюю страницу");

            return "welcome";

    }


}
