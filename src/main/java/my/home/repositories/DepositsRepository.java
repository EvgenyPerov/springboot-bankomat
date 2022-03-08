package my.home.repositories;

import my.home.models.Currency;
import my.home.models.Deposit;
import my.home.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepositsRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findAllByOwnerOfDeposit(Person person); // так выглядит запрос SQL через JPA
 //   List<Long> findAllId();

//    Deposit findOneByOwnerOfDepositAndByCurrency(Person person,Currency currency);

    double findAmountByCurrency(Currency currency);

    Deposit findOneByCurrency(Currency currency);



//    void updateOneById(Long id, Role role, State state);
//        void updateOneById(Long id);
}
