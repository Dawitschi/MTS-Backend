package main.java.controllers.http.converters.game;

import main.java.controllers.http.converters.AbstractTwoWayConverter;
import main.java.controllers.http.dtos.TeamDTO;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.team.Team;
import main.java.services.DBServices.GameService;
import main.java.services.DBServices.GameplayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TwoWayTeamConverter extends AbstractTwoWayConverter<Team, TeamDTO> {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameplayerService gameplayerService;

    @Override
    protected TeamDTO convert(Team source) {
        List<Integer> player_IDs = new ArrayList<>();
        for(GamePlayer player : source.getPlayers()) player_IDs.add(player.getGamePlayer_ID());
        return new TeamDTO(source.getTeam_ID(), source.getGame().getGame_ID(), player_IDs);
    }

    @Override
    protected Team convertBack(TeamDTO target) {
        Set<GamePlayer> gamePlayers = new HashSet<>();
        for(Integer gameplayer_ID : target.player_IDs()) gamePlayers.add(gameplayerService.getGameplayerByID(gameplayer_ID));
        return new Team(target.team_ID(), gameService.getGamebyID(target.game_ID()), gamePlayers);
    }
}
