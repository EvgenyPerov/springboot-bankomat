package my.home.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String password;

    @OneToMany(mappedBy = "ownerOfDeposit", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Column(name = "deposit_id")
    private List<Deposit> depositsList; // = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;

    public Person(long id, String login, String firstName, String lastName, String password, Role role, State state) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.state = state;
    }

    public void addDeposit(Deposit deposit){
//        System.out.println(deposit);
//        depositsList.add(deposit);
//        deposit.setOwnerOfDeposit(this);
    }

}
