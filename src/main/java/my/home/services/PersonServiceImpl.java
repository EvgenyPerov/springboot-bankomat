package my.home.services;

import my.home.forms.SignUpForm;
import my.home.forms.UpdateForm;
import my.home.models.Deposit;
import my.home.models.Person;
import my.home.models.Role;
import my.home.models.State;
import my.home.repositories.ClientsRepository;
import my.home.repositories.DepositsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

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

//    @Override
//    public Optional<Person> findOne(Long personId) {
//        return repository.findOneById(personId);
//    }

    public void updateRoleOrState(UpdateForm form){
        Person person = repository.findOneById(form.getId());
        System.out.println(form.getId()+" "+ form.getRole()+" "+ form.getState());
        person.setRole(form.getRole());
        person.setState(form.getState());
//        System.out.println(person);
        repository.save(person);

//        repository.updateOneById(form.getId(), form.getRole(),form.getState());

    }

//    public void addDeposit(AddDepositForm form, Person person) {
//        Deposit deposit= depositService.createDeposit2(form);
//          System.out.println(deposit);
//        deposit.setOwnerOfDeposit(person);
// //       depositsRepository.save(deposit);
//         System.out.println(deposit);
//               person.addDeposit(deposit);
//                 System.out.println(deposit);
//        person.getDepositsList().add(deposit);
//        repository.save(person);
//    }

}