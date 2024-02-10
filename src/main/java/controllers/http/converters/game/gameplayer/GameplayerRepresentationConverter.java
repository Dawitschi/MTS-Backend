package main.java.controllers.http.converters.game.gameplayer;

import main.java.controllers.http.objects.GameplayerRepresentation;
import main.java.databank.game.GamePlayer;
import org.springframework.core.convert.converter.Converter;

public class GameplayerRepresentationConverter implements Converter<GamePlayer, GameplayerRepresentation> {

    @Override
    public GameplayerRepresentation convert(GamePlayer source) {
        return new GameplayerRepresentation(source.getGamePlayer_ID(), source.getPosition(), source.getElo(),
                source.getPlayer().getPlayer_ID(), source.getTeams().getTeam_ID());
    }
}
