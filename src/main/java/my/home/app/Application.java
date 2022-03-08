package my.home.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@ComponentScan(basePackages = "my.home") //без этого не найдет файлы .jsp
@EnableJpaRepositories(basePackages = "my.home.repositories") //указать путь к JPA repositories
@EntityScan(basePackages = "my.home.models") //указать путь к объектам-таблицам
public class Application {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Started");

    }


}

