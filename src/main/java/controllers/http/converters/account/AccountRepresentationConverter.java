package main.java.controllers.http.converters.account;

import main.java.controllers.http.objects.AccountRepresentation;
import main.java.databank.accounts.Account;
import main.java.databank.game.Player;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepresentationConverter implements Converter<Account, AccountRepresentation> {

    @Override
    public AccountRepresentation convert(Account account) {
        List<Integer> playerIDs = new ArrayList<>();
        for (Player player : account.getPlayers()) playerIDs.add(player.getPlayer_ID());

        List<Integer> friendIDs = new ArrayList<>();
        for (Account friend : account.getFriends()) friendIDs.add(friend.getAccount_ID());

        return new AccountRepresentation(account.getAccount_ID(), playerIDs, account.getPassword(), account.getToken(), account.getUsername(), friendIDs);
    }

}
