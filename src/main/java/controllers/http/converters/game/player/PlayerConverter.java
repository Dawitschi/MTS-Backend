package main.java.controllers.http.converters.game.player;

import main.java.controllers.http.objects.PlayerRepresentation;
import main.java.databank.game.GamePlayer;
import main.java.databank.game.Player;
import main.java.services.DBServices.AccountService;
import main.java.services.DBServices.GameplayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayerConverter implements Converter<PlayerRepresentation, Player> {

    @Autowired
    private AccountService accountService;

    @Autowired
    private GameplayerService gameplayerService;

    @Override
    public Player convert(PlayerRepresentation source) {
        Set<GamePlayer> gamePlayerSet = new HashSet<>();
        for(Integer gameplayer_ID : source.gamePlayer_IDs()) gamePlayerSet.add(gameplayerService.getGameplayerByID(gameplayer_ID));
        return new Player(accountService.getAccountbyID(source.account_ID()),source.player_ID(),
                source.elo(), source.name(), source.nickname(),gamePlayerSet, new ArrayList<>());
    }
}
