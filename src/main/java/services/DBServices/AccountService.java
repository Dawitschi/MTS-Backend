package main.java.services.DBServices;

import main.java.controllers.dtos.AccountDTO;
import main.java.databank.accounts.Account;
import main.java.databank.accounts.AccountRepository;
import main.java.databank.game.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public Account createAccount(AccountDTO accountDTO) {
        Account account = new Account(new ArrayList<>(), accountDTO.password(), accountDTO.creation_Token(), accountDTO.username(), new ArrayList<>());
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


}
