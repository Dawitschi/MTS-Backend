package main.java.controllers.http.converters.game.player;

import main.java.controllers.http.objects.PlayerRepresentation;
import main.java.databank.game.GamePlayer;
import main.java.databank.game.Player;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

public class PlayerRepresentationConverter implements Converter<Player, PlayerRepresentation> {
    @Override
    public PlayerRepresentation convert(Player source) {
        Set<Integer> gameplayer_IDs = new HashSet<>();
        for(GamePlayer gamePlayer: source.getGamePlayers()) gameplayer_IDs.add(gamePlayer.getGamePlayer_ID());
        return new PlayerRepresentation(source.getAccount().getAccount_ID(), source.getPlayer_ID(),
                source.getElo(), source.getName(),source.getNickname(),gameplayer_IDs);
    }
}
