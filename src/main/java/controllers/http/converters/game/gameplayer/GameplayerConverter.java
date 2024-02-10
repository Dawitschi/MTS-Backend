package main.java.controllers.http.converters.game.gameplayer;

import main.java.controllers.http.objects.GameplayerRepresentation;
import main.java.databank.game.GamePlayer;
import main.java.databank.game.Player;
import main.java.databank.game.Team;
import main.java.services.DBServices.PlayerService;
import main.java.services.DBServices.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameplayerConverter implements Converter<GameplayerRepresentation, GamePlayer> {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Override
    public GamePlayer convert(GameplayerRepresentation source) {
        Team team = teamService.getTeambyID(source.team_ID());
        Player player = playerService.getPlayerbyID(source.player_ID());
        return new GamePlayer(source.gamesPlayer_ID(),source.position(), player,team,source.elo());
    }
}
