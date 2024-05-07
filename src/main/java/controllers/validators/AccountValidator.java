package main.java.controllers.validators;

import main.java.databank.accounts.Account;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;

        if(!account.getUsername().contains("Cool")) errors.rejectValue("username", "notCoolEnough","Your username is not cool enough");
    }
}
