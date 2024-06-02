package main.java.controllers.converters.game;

import main.java.controllers.converters.AbstractTwoWayConverter;
import main.java.controllers.dtos.PlayerDTO;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.player.Player;
import main.java.services.DBServices.AccountService;
import main.java.services.DBServices.GameplayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class TwoWayPlayerConverter extends AbstractTwoWayConverter<Player, PlayerDTO> {

    @Autowired
    private AccountService accountService;

    @Autowired
    private GameplayerService gameplayerService;

    @Override
    protected PlayerDTO convert(Player source) {
        Set<Integer> gameplayer_IDs = new HashSet<>();
        for(GamePlayer gamePlayer: source.getGamePlayers()) gameplayer_IDs.add(gamePlayer.getGamePlayer_ID());
        return new PlayerDTO(source.getAccount().getAccount_ID(), source.getPlayer_ID(),
                source.getElo(), source.getName(),source.getNickname(),gameplayer_IDs);
    }

    @Override
    protected Player convertBack(PlayerDTO target) {
        Set<GamePlayer> gamePlayerSet = new HashSet<>();
        for(Integer gameplayer_ID : target.gamePlayer_IDs()) gamePlayerSet.add(gameplayerService.getGameplayerByID(gameplayer_ID));
        return new Player(accountService.getAccountbyID(target.account_ID()),target.player_ID(),
                target.elo(), target.name(), target.nickname(),gamePlayerSet, new ArrayList<>());
    }
}
