package main.java.controllers.http.converters.account;

import main.java.controllers.http.converters.AbstractTwoWayConverter;
import main.java.controllers.http.dtos.AccountDTO;
import main.java.databank.accounts.Account;
import main.java.databank.game.player.Player;
import main.java.services.DBServices.AccountService;
import main.java.services.DBServices.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TwoWayAccountConverter extends AbstractTwoWayConverter<Account, AccountDTO> {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private AccountService accountService;

    @Override
    protected AccountDTO convert(Account account) {
        List<Integer> playerIDs = new ArrayList<>();
        for (Player player : account.getPlayers()) playerIDs.add(player.getPlayer_ID());

        List<Integer> friendIDs = new ArrayList<>();
        for (Account friend : account.getFriends()) friendIDs.add(friend.getAccount_ID());

        return new AccountDTO(account.getAccount_ID(), playerIDs, account.getPassword(), account.getToken(), account.getUsername(), friendIDs);
    }

    @Override
    protected Account convertBack(AccountDTO accountDTO) {
        List<Player> players = new ArrayList<>();
        for (Integer playerID : accountDTO.player_IDs()) players.add(playerService.getPlayerbyID(playerID));

        List<Account> friends = new ArrayList<>();
        for (Integer friendID : accountDTO.friend_IDs()) friends.add(accountService.getAccountbyID(friendID));

        return new Account(players, accountDTO.password(), accountDTO.token(), accountDTO.username(), friends);

    }
}