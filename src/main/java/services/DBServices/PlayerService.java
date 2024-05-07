package main.java.services.DBServices;

import main.java.databank.game.player.Player;
import main.java.databank.game.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameService gameService;

    public Player createPlayer(String name, String nickname) {
        Player player = new Player(name,nickname);
        playerRepository.save(player);
        return player;
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public Player getPlayerbyID(Integer id) throws NoSuchElementException {
        return playerRepository.findById(id).get();
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

}
