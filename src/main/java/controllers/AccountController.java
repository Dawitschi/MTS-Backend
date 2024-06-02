package main.java.controllers;

import main.java.controllers.dtos.AccountDTO;
import main.java.controllers.validations.markers.onCreation;
import main.java.controllers.validations.markers.onUpdate;
import main.java.databank.accounts.Account;
import main.java.services.DBServices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/Account")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private DefaultConversionService defaultConversionService;


    @PostMapping("/createAccount")
    public ResponseEntity<Object> submitAccount(@RequestBody @Validated(onCreation.class) AccountDTO account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder("The following errors where found: \n");
            bindingResult.getAllErrors().forEach(err -> builder.append(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(builder.toString());
        }
        accountService.saveAccount(defaultConversionService.convert(account, Account.class));
        return ResponseEntity.ok(account);
    }

    @PostMapping("/updateAccount")
    public ResponseEntity<Object> updateAccount(@RequestBody @Validated(onUpdate.class) AccountDTO account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder("The following errors where found: \n");
            bindingResult.getAllErrors().forEach(err -> builder.append(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(builder.toString());
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
