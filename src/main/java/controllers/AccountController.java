package main.java.controllers;

import jakarta.validation.Valid;
import main.java.controllers.http.dtos.AccountDTO;
import main.java.controllers.validators.AccountValidator;
import main.java.databank.accounts.Account;
import main.java.services.DBServices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Account")
@Validated
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
    public ResponseEntity<AccountDTO> submitAccount(@RequestBody @Validated() AccountDTO account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        accountService.saveAccount(defaultConversionService.convert(account, Account.class));
        return ResponseEntity.ok(account);
    }

    @GetMapping("/allAccounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> list = new ArrayList<>();
        for(Account account: accountService.getAllAccounts()) {
            list.add(defaultConversionService.convert(account, AccountDTO.class));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Integer id) {
        return ResponseEntity.ok(defaultConversionService.convert(accountService.getAccountbyID(id), AccountDTO.class));
    }

}
