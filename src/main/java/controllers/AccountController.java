package main.java.controllers;

import main.java.controllers.http.objects.AccountRepresentation;
import main.java.controllers.validators.AccountValidator;
import main.java.databank.accounts.Account;
import main.java.services.DBServices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Account")
/**
 * TO-DO:   Make the leaderboards an in memory List, that refreshes on elo changes
 *          and initializes on Program start
 */
public class AccountController {

    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DefaultConversionService defaultConversionService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(accountValidator);
    }

    @PostMapping("/newAccount")
    public AccountRepresentation submitAccount(AccountRepresentation account) {
        accountService.saveAccount(defaultConversionService.convert(account, Account.class));
        return account;
    }

    @GetMapping("/allAccounts")
    public List<AccountRepresentation> getAllAccounts() {
        List<AccountRepresentation> list = new ArrayList<>();
        for(Account account: accountService.getAllAccounts()) {
            list.add(defaultConversionService.convert(account, AccountRepresentation.class));
        }
        return list;
    }

    @GetMapping()
    public AccountRepresentation getAccount(Integer id) {
        return defaultConversionService.convert(accountService.getAccountbyID(id), AccountRepresentation.class);
    }

}
