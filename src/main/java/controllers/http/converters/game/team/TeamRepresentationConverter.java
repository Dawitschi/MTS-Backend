package main.java.controllers.http.converters.game.team;

import main.java.controllers.http.objects.TeamRepresentation;
import main.java.databank.game.GamePlayer;
import main.java.databank.game.Team;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class TeamRepresentationConverter implements Converter<Team, TeamRepresentation> {
    @Override
    public TeamRepresentation convert(Team source) {
        List<Integer> player_IDs = new ArrayList<>();
        for(GamePlayer player : source.getPlayers()) player_IDs.add(player.getGamePlayer_ID());
        return new TeamRepresentation(source.getTeam_ID(), source.getGame().getGame_ID(), player_IDs);
    }
}
