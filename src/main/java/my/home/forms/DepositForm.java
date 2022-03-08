package my.home.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.home.models.Currency;
import my.home.models.Role;
import my.home.models.State;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositForm {

//    private long id;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private double amount;

}
