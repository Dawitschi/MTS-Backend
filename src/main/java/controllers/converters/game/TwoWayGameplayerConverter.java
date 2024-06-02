package main.java.controllers.converters.game;

import main.java.controllers.converters.AbstractTwoWayConverter;
import main.java.controllers.dtos.GameplayerDTO;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.player.Player;
import main.java.databank.game.team.Team;
import main.java.services.DBServices.PlayerService;
import main.java.services.DBServices.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwoWayGameplayerConverter extends AbstractTwoWayConverter<GamePlayer, GameplayerDTO> {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;
    @Override
    protected GameplayerDTO convert(GamePlayer source) {
        return new GameplayerDTO(source.getGamePlayer_ID(), source.getPosition(), source.getElo(),
                source.getPlayer().getPlayer_ID(), source.getTeams().getTeam_ID());
    }

    @Override
    protected GamePlayer convertBack(GameplayerDTO target) {
        Team team = teamService.getTeambyID(target.team_ID());
        Player player = playerService.getPlayerbyID(target.player_ID());
        return new GamePlayer(target.gamesPlayer_ID(),target.position(), player,team,target.elo());
    }
}
