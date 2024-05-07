package main.java.services.DBServices;

import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.gameplayer.GamePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameplayerService {

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    public void saveGamePlayer(GamePlayer gamePlayer) {
        gamePlayerRepository.save(gamePlayer);
    }

    public GamePlayer getGameplayerByID(Integer id) {
        return gamePlayerRepository.findById(id).get();
    }
}
