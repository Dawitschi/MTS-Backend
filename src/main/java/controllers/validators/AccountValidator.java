package main.java.controllers.validators;

import main.java.controllers.http.dtos.AccountDTO;
import main.java.databank.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@Service
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz) || AccountDTO.class.equals(clazz);
    }

    @Autowired
    private DefaultConversionService defaultConversionService;

    @Override
    public void validate(Object target, Errors errors) {
        Account account;
        if (target.getClass().equals(AccountDTO.class)) {
            account = defaultConversionService.convert(target, Account.class);
        } else account = (Account) target;
        if(!account.getUsername().contains("Cool")) errors.rejectValue("username", "notCoolEnough","Your username is not cool enough");
    }

}
