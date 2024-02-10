package main.java.controllers.http.converters.account;

import main.java.controllers.http.objects.AccountRepresentation;
import main.java.databank.accounts.Account;
import main.java.databank.game.Player;
import main.java.services.DBServices.AccountService;
import main.java.services.DBServices.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountConverter implements Converter<AccountRepresentation,Account> {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private AccountService accountService;

    @Override
    public Account convert(AccountRepresentation accountRepresentation) {
        List<Player> players = new ArrayList<>();
        for (Integer playerID : accountRepresentation.player_IDs()) players.add(playerService.getPlayerbyID(playerID));

        List<Account> friends = new ArrayList<>();
        for (Integer friendID : accountRepresentation.friend_IDs()) friends.add(accountService.getAccountbyID(friendID));

        return new Account(players, accountRepresentation.password(), accountRepresentation.token(), accountRepresentation.username(), friends);

    }
}
