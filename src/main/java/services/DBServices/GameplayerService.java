package main.java.services.DBServices;

import main.java.databank.game.GamePlayer;
import main.java.databank.game.GamePlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameplayerService {

    /**
     * The gamePlayerRepository is basically the connection between the DAO and the GamePlayer table
     */
    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    public void saveGamePlayer(GamePlayer gamePlayer) {
        gamePlayerRepository.save(gamePlayer);
    }

    public GamePlayer getGameplayerByID(Integer id) {
        return gamePlayerRepository.findById(id).get();
    }
}
