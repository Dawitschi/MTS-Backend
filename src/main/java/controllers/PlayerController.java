package main.java.controllers;

import main.java.controllers.http.dtos.PlayerDTO;
import main.java.databank.game.player.Player;
import main.java.services.DBServices.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private DefaultConversionService defaultConversionService;

    @RequestMapping(value = "/newPlayer", method = RequestMethod.POST)
    public PlayerDTO submitPlayer(PlayerDTO playerDTO) {
        Player player = defaultConversionService.convert(playerDTO, Player.class);
        playerService.savePlayer(player);
        return playerDTO;
    }

    @GetMapping(path="/allPlayers")
    public Iterable<PlayerDTO> getAllPlayerTable() {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (Player player : playerService.getAllPlayers())
            playerDTOS.add(defaultConversionService.convert(player, PlayerDTO.class));
        return playerDTOS;
    }



}
