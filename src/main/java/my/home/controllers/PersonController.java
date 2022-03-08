package my.home.controllers;

import my.home.forms.UpdateForm;
import my.home.models.Role;
import my.home.models.State;
import my.home.repositories.ClientsRepository;
import my.home.services.PersonService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class PersonController {
    @Value("${my.property}")
    private String myProperty;

//    @Autowired
//    private ClientsRepository clientsRepository;  //подключение-инъекция базы данных
    @Autowired
    private PersonService service;

    @GetMapping("/persons")
    public String getPersonsPage(ModelMap model) {
        System.out.println(myProperty + " страница со списком клиентов");
//        Arrays.asList(Role.values()).forEach(x-> System.out.println(x));

        model.addAttribute("personsFromServer", service.findAll());
        model.addAttribute("ids", service.findAllId());
        model.addAttribute("roles", Arrays.asList(Role.values()));
        model.addAttribute("states", Arrays.asList(State.values()));
        model.addAttribute("myProperty", myProperty);
        return "persons";
    }

    @PostMapping("/persons")
    public String updatePersonsPage(UpdateForm updateForm){
//        System.out.println(updateForm.getId()+" "+ updateForm.getRole()+" "+ updateForm.getState());
        service.updateRoleOrState(updateForm);
//        return "redirect:/welcome";
        return "redirect:/persons";
    }

//    @PostMapping("/persons")
//    public String updatePersonsPage(@ModelAttribute("updateForm") UpdateForm updateForm,
//                                    @RequestParam(required = false) Role role){
//        System.out.println(updateForm.getId()+" "+ updateForm.getRole()+" "+ updateForm.getState()+" "+role);
//
//        return "redirect:/welcome";
//    }

// <div class="form-style-2">
//        <div class="form-style-2-heading">
//    Already have registred!
//        </div>
// <td><a href="/persons"> Сохранить изменения Роли или Статуса клиента</a></td>

}
