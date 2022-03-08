package my.home.controllers;

import my.home.forms.DepositForm;
import my.home.models.Currency;
import my.home.security.details.UserDetailsImpl;
import my.home.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class GetMoneyController {

    @Autowired
    private DepositService depositService;

    @GetMapping("/getMoney")
    public String getMoneyPage(ModelMap model, Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        model.addAttribute("firstName", details.getUser().getFirstName());
        model.addAttribute("lastName", details.getUser().getLastName());
        model.addAttribute("currencies", Arrays.asList(Currency.values()));
        return "getMoney";
    }

    @PostMapping("/getMoney")
    public String addMoneyPage(DepositForm depositForm, Authentication authentication){
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();

        if (depositForm.getAmount()>0) {
            depositService.getDeposit(depositForm, details.getUser());
        } else  System.out.println("Выбрано количество: "+depositForm.getAmount()+ " должно быть больше нуля");

        return "redirect:/getMoney";
    }

}
