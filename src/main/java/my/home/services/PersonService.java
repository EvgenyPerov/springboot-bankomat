package my.home.services;


import my.home.forms.SignUpForm;
import my.home.forms.UpdateForm;
import my.home.models.Person;

import java.util.List;

public interface PersonService {

    void signUp(SignUpForm form);

    List<Person> findAll();

    List<Long> findAllId();


    void updateRoleOrState(UpdateForm form);

}
