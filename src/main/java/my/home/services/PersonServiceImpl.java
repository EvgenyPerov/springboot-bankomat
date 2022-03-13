package my.home.services;

import my.home.app.Solution;
import my.home.forms.SignUpForm;
import my.home.forms.UpdateForm;
import my.home.models.Deposit;
import my.home.models.Person;
import my.home.models.Role;
import my.home.models.State;
import my.home.repositories.ClientsRepository;
import my.home.repositories.DepositsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

    @Autowired
    private ClientsRepository repository;

    @Autowired
    private DepositService depositService;

    @Autowired
    private DepositsRepository depositsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm form) {
        String hashPassword = passwordEncoder.encode(form.getPassword());

        Person person = Person.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .password(hashPassword)
                .login(form.getLogin())
                .depositsList(new ArrayList<Deposit>()) // может не принимать нул, и нужно будет создать список
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();
        logger.info("Добавлен клиент имя = " + form.getFirstName()
                  + ", фамилия = " + form.getLastName()
                  + ", логин = " + form.getLogin()
                  + ", роль = " + Role.USER
                  + ", статус = " + State.ACTIVE
        );

        repository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Long> findAllId() {
        List<Long> idList = new ArrayList<>();
        findAll().forEach(person -> idList.add(person.getId()));
        return idList;
    }

    public void updateRoleOrState(UpdateForm form){
        Person person = repository.findOneById(form.getId());
//        System.out.println(form.getId()+" "+ form.getRole()+" "+ form.getState());
        person.setRole(form.getRole());
        person.setState(form.getState());
        logger.info("Изменен клиент ID#  " + form.getId()
                + " Новая роль  " + form.getRole()
                + " Новый статус  " + form.getState()
        );
        repository.save(person);

    }

}