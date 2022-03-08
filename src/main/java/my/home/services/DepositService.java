package my.home.services;


import my.home.forms.DepositForm;
import my.home.models.Currency;
import my.home.models.Deposit;
import my.home.models.Person;

import java.util.List;

public interface DepositService {

    void addDeposit(DepositForm form, Person person);

    void getDeposit(DepositForm form, Person person);

    Deposit containsDeposit(List<Deposit> list, Currency currency);

    public List<Deposit> getDepositsByPerson(Person person);

//    Deposit createDeposit2(AddDepositForm form);

//    void setOwnerOfThisDeposit1(Deposit deposit, Person person);

}
