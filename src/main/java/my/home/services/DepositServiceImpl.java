package my.home.services;

import my.home.forms.DepositForm;
import my.home.models.Currency;
import my.home.models.Deposit;
import my.home.models.Person;
import my.home.repositories.ClientsRepository;
import my.home.repositories.DepositsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private DepositsRepository depositsRepository;

//    @Override
//    public Deposit createDeposit2(AddDepositForm form){
//        Deposit deposit = Deposit.builder()
//                .amount(form.getAmount())
//                .currency(form.getCurrency())
//                .ownerOfDeposit(null)
//                .build();
//        return deposit;
//    }


    @Override
    public void addDeposit(DepositForm form, Person person) { // нужно логировать операцию
//        Person person = clientsRepository.findOneById(form.getId());
  //      System.out.println("ID# " +person.getId());
        Currency currency = form.getCurrency();
        double amount = form.getAmount();
//        Deposit dep = depositsRepository.findOneByOwnerOfDepositAndByCurrency(person,currency);
        List<Deposit> depositList = getDepositsByPerson(person);
        Deposit deposit = containsDeposit(depositList,currency);
        if (deposit != null) {
            System.out.println("Такой счет существует. Добавляем сумму");
            deposit.setAmount(deposit.getAmount()+amount);
            } else {
            // если депозит с такой валютой не существует, создать новый
            System.out.println("Такой счет НЕ существует. Создаем новый и добавляем сумму");
            deposit = Deposit.builder()
                    .amount(form.getAmount())
                    .currency(form.getCurrency())
                    .ownerOfDeposit(person)
                    .build();
            }
        depositsRepository.save(deposit); //возможно нужно поднять вверх перед скобкой
//        person.addDeposit(deposit);

    }

    @Override
    public void getDeposit(DepositForm form, Person person) { // нужно логировать операцию
        double amount = form.getAmount();
        Currency currency = form.getCurrency();
        List<Deposit> depositList = getDepositsByPerson(person);
        Deposit deposit = containsDeposit(depositList,currency);
        if (deposit != null) {
            double moneyOnDeposit = deposit.getAmount();
            System.out.println("На счету " + moneyOnDeposit+" Валюта = "+deposit.getCurrency());
            System.out.println("В запросе " + amount +" Валюта = "+currency);
            if (moneyOnDeposit >= amount) {
                deposit.setAmount(moneyOnDeposit - amount);
                depositsRepository.save(deposit);
            } else {
                System.out.println("На счету не достаточно средств");
            }

        } else {
            // если депозит с такой валютой не существует
            System.out.println("Счета с указанной валютой не существует");
        }

    }

    public Deposit containsDeposit(List<Deposit> list, Currency currency){
        for (Deposit dep : list) {
            if (dep.getCurrency() == currency) {
                return dep;
            }
        }
        return null;
    }

    public List<Deposit> getDepositsByPerson(Person person) {
        return depositsRepository.findAllByOwnerOfDeposit(person);

    }

//    public Deposit getFromDepositList (List<Deposit> list, Currency currency){
//
//    }
}