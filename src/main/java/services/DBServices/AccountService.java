package main.java.services.DBServices;

import main.java.controllers.dtos.AccountDTO;
import main.java.databank.accounts.Account;
import main.java.databank.accounts.AccountRepository;
import main.java.databank.accounts.Role;
import main.java.databank.game.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    private Role user;

    public void setUser(Role user) {
        this.user = user;
    }

    public Account createAccount(AccountDTO accountDTO) {
        Account account = new Account(new ArrayList<>(), accountDTO.password(), accountDTO.creation_Token(),
                accountDTO.username(), new ArrayList<>(), Collections.singletonList(user));
        accountRepository.save(account);
        return account;
    }

    public Account getAccountbyID(Integer id) throws NoSuchElementException {
        return accountRepository.findById(id).get();
    }
    public void addPlayer(Player player, Account account) {
        List<Player> players = account.getPlayers();
        players.add(player);
        account.setPlayers(players);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountsByUsernameEqualsIgnoreCase(username);
    }
}
