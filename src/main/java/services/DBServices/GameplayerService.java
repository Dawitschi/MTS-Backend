package main.java.services.DBServices;

import main.java.controllers.dtos.GameplayerDTO;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.gameplayer.GamePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameplayerService {

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerService playerService;

    public void saveGamePlayer(GamePlayer gamePlayer) {
        gamePlayerRepository.save(gamePlayer);
    }

    public GamePlayer getGameplayerByID(Integer id) {
        return gamePlayerRepository.findById(id).get();
    }

    public GamePlayer createGameplayer(GameplayerDTO gameplayerDTO) {
        GamePlayer gamePlayer = new GamePlayer(gameplayerDTO.gamesPlayer_ID(), gameplayerDTO.position(),
                playerService.getPlayerbyID(gameplayerDTO.player_ID()), null, gameplayerDTO.elo());
        return gamePlayer;
    }
}
