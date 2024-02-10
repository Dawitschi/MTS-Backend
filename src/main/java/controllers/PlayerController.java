package main.java.controllers;

import main.java.controllers.http.objects.PlayerRepresentation;
import main.java.databank.game.Player;
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
    public PlayerRepresentation submitPlayer(PlayerRepresentation playerRepresentation) {
        Player player = defaultConversionService.convert(playerRepresentation, Player.class);
        playerService.savePlayer(player);
        return playerRepresentation;
    }

    /**
     * Gives a list of all players in the database.
     * @return The list of all saved Players
     */
    @GetMapping(path="/allPlayers")
    public Iterable<PlayerRepresentation> getAllPlayerTable() {
        List<PlayerRepresentation> playerRepresentations = new ArrayList<>();
        for (Player player : playerService.getAllPlayers())
            playerRepresentations.add(defaultConversionService.convert(player, PlayerRepresentation.class));
        return playerRepresentations;
    }



}
