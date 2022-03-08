package my.home.security.details;

import my.home.models.Person;
import my.home.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Person> person = repository.findOneByLogin(login);
        if (person.isPresent()) {
            return new UserDetailsImpl(person.get());
        } else throw new IllegalArgumentException("Person not found");
//        return new
//                UserDetailsImpl(repository.findOneByLogin(login)
//                .orElseThrow(IllegalArgumentException::new));
    }
}
