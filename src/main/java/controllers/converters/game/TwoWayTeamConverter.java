package main.java.controllers.converters.game;

import main.java.controllers.converters.AbstractTwoWayConverter;
import main.java.controllers.dtos.GameplayerDTO;
import main.java.controllers.dtos.TeamDTO;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.team.Team;
import main.java.services.DBServices.GameService;
import main.java.services.DBServices.GameplayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.support.DefaultConversionService;
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
    @Lazy
    private DefaultConversionService defaultConversionService;

    @Override
    protected TeamDTO convert(Team source) {
        List<GameplayerDTO> player_IDs = new ArrayList<>();
        for(GamePlayer player : source.getPlayers()) player_IDs.add(defaultConversionService.convert(player, GameplayerDTO.class));
        return new TeamDTO(source.getTeam_ID(), source.getGame().getGame_ID(),source.getScore(), player_IDs);
    }

    @Override
    protected Team convertBack(TeamDTO target) {
        Set<GamePlayer> gamePlayers = new HashSet<>();
        for(GameplayerDTO gameplayer_ID : target.player_IDs()) gamePlayers.add(defaultConversionService.convert(gameplayer_ID, GamePlayer.class));
        return new Team(target.team_ID(), gameService.getGamebyID(target.game_ID()), gamePlayers, target.score());
    }
}
