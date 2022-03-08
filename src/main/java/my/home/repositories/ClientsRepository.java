package my.home.repositories;

import my.home.models.Person;
import my.home.models.Role;
import my.home.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientsRepository extends JpaRepository <Person, Long> {
    List<Person> findAllByFirstName(String firstName); // так выглядит запрос SQL через JPA
 //   List<Long> findAllId();


    Optional<Person> findOneByLogin(String login);

    Person findOneById(Long id);

//    void updateOneById(Long id, Role role, State state);
//        void updateOneById(Long id);
}
