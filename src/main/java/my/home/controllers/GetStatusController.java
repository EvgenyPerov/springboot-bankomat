package my.home.controllers;

import my.home.forms.UpdateForm;
import my.home.models.Deposit;
import my.home.models.Role;
import my.home.models.State;
import my.home.security.details.UserDetailsImpl;
import my.home.services.DepositService;
import my.home.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class GetStatusController {

    @Autowired
    private DepositService depositService;

    @GetMapping("/getStatus")
    public String getStatusPage(ModelMap model, Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        model.addAttribute("firstName", details.getUser().getFirstName());
        model.addAttribute("lastName", details.getUser().getLastName());

        List<Deposit> depositList = depositService.getDepositsByPerson(details.getUser());

        model.addAttribute("depositsListFromServer", depositList);
//        model.addAttribute("ids", service.findAllId());
//        model.addAttribute("roles", Arrays.asList(Role.values()));
//        model.addAttribute("states", Arrays.asList(State.values()));
        return "getStatus";
    }

}
