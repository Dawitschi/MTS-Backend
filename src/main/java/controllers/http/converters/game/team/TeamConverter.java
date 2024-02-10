package main.java.controllers.http.converters.game.team;

import main.java.controllers.http.objects.TeamRepresentation;
import main.java.databank.game.GamePlayer;
import main.java.databank.game.Team;
import main.java.services.DBServices.GameService;
import main.java.services.DBServices.GameplayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TeamConverter implements Converter<TeamRepresentation, Team> {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameplayerService gameplayerService;

    @Override
    public Team convert(TeamRepresentation source) {
        Set<GamePlayer> gamePlayers = new HashSet<>();
        for(Integer gameplayer_ID : source.player_IDs()) gamePlayers.add(gameplayerService.getGameplayerByID(gameplayer_ID));
        return new Team(source.team_ID(), gameService.getGamebyID(source.game_ID()), gamePlayers);
    }
}
