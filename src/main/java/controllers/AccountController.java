package main.java.controllers;

import main.java.controllers.http.dtos.AccountDTO;
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
    public AccountDTO submitAccount(AccountDTO account) {
        accountService.saveAccount(defaultConversionService.convert(account, Account.class));
        return account;
    }

    @GetMapping("/allAccounts")
    public List<AccountDTO> getAllAccounts() {
        List<AccountDTO> list = new ArrayList<>();
        for(Account account: accountService.getAllAccounts()) {
            list.add(defaultConversionService.convert(account, AccountDTO.class));
        }
        return list;
    }

    @GetMapping()
    public AccountDTO getAccount(Integer id) {
        return defaultConversionService.convert(accountService.getAccountbyID(id), AccountDTO.class);
    }

}
