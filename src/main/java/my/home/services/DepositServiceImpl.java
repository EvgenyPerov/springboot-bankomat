package my.home.services;

import my.home.app.Solution;
import my.home.forms.DepositForm;
import my.home.models.Currency;
import my.home.models.Deposit;
import my.home.models.Person;
import my.home.repositories.ClientsRepository;
import my.home.repositories.DepositsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    private static final Logger logger = Logger.getLogger(DepositServiceImpl.class);

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
        Currency currency = form.getCurrency();
        double amount = form.getAmount();
//        Deposit dep = depositsRepository.findOneByOwnerOfDepositAndByCurrency(person,currency);
        List<Deposit> depositList = getDepositsByPerson(person);
        Deposit deposit = containsDeposit(depositList,currency);
        if (deposit != null) {
            logger.info("Такой счет существует. Добавляем сумму = " + amount);
            deposit.setAmount(deposit.getAmount()+amount);
            logger.info("Итог после добавления для ID# " + deposit.getOwnerOfDeposit().getId()
                    +" сумма = "+ deposit.getAmount()
                    +" Валюта = "+ deposit.getCurrency());
 //           logger.trace(deposit);
            } else {
            // если депозит с такой валютой не существует, создать новый
            logger.warn("Такой счет НЕ существует. Создаем новый и добавляем сумму");
            deposit = Deposit.builder()
                    .amount(form.getAmount())
                    .currency(form.getCurrency())
                    .ownerOfDeposit(person)
                    .build();

            logger.info("Создан депозит для ID# " + deposit.getOwnerOfDeposit().getId()
                    +" сумма = "+ deposit.getAmount()
                    +" Валюта = "+ deposit.getCurrency());
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
            logger.info("На счету " + moneyOnDeposit+" Валюта = "+deposit.getCurrency() +
                    " В запросе " + amount +" Валюта = "+currency);
            if (moneyOnDeposit >= amount) {
                deposit.setAmount(moneyOnDeposit - amount);
                 logger.info("Итог после снятия для ID# " + deposit.getOwnerOfDeposit().getId()
                    +" сумма = "+ deposit.getAmount()
                    +" Валюта = "+ deposit.getCurrency());
                depositsRepository.save(deposit);
            } else {
                logger.error("На счету не достаточно средств");
            }

        } else {
            // если депозит с такой валютой не существует
            logger.error("Счета с указанной валютой не существует");
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