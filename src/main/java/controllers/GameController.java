package main.java.controllers;

import main.java.controllers.http.objects.GameRepresentation;
import main.java.controllers.validators.GameCreationInfoValidator;
import main.java.databank.game.Game;
import main.java.services.EloCalculatorService;
import main.java.services.DBServices.GameService;
import main.java.services.DBServices.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Game")
public class GameController {

    @Autowired
    private EloCalculatorService eloCalculatorService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameCreationInfoValidator gameValidator;

    @Autowired
    private DefaultConversionService defaultConversionService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(gameValidator);
    }

    @PostMapping(value = "/newGame")
    public GameRepresentation newGame(GameRepresentation gameRepresentation) {
        Game game = defaultConversionService.convert(gameRepresentation, Game.class);
        eloCalculatorService.reevaluateElos(game);
        return gameRepresentation;
    }



}
