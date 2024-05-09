package main.java.controllers;

import main.java.controllers.http.dtos.GameDTO;
import main.java.databank.game.game.Game;
import main.java.databank.game.table.Table;
import main.java.services.DBServices.TableService;
import main.java.services.EloCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Game")
public class GameController {

    @Autowired
    private EloCalculatorService eloCalculatorService;

    @Autowired
    private TableService tableService;

    @Autowired
    private DefaultConversionService defaultConversionService;


    @PostMapping(value = "/newGame")
    public GameDTO newGame(GameDTO gameDTO) {
        Game game = defaultConversionService.convert(gameDTO, Game.class);
        eloCalculatorService.reevaluateElos(game);
        return gameDTO;
    }

    @GetMapping(value = "/fromTableN")
    public List<GameDTO> getGamesFromTable(Integer tableID, Integer n) {
        Table table = tableService.getTable(tableID);
        List<Game> games = tableService.getRecentGames(table, n);
        List<GameDTO> gameDTOS = new ArrayList<>();
        games.forEach(game -> gameDTOS.add(defaultConversionService.convert(game, GameDTO.class)));
        return gameDTOS;
    }

    @GetMapping(value = "/fromTableAll")
    public List<GameDTO> getGamesFromTable(Integer tableID) {
        Table table = tableService.getTable(tableID);
        List<Game> games = table.getGames();
        List<GameDTO> gameDTOS = new ArrayList<>();
        games.forEach(game -> gameDTOS.add(defaultConversionService.convert(game, GameDTO.class)));
        return gameDTOS;
    }

    @GetMapping(value = "/fromTableOngoing")
    public GameDTO getCurrentGameFromTable(Integer tableID) {
        Table table = tableService.getTable(tableID);
        return defaultConversionService.convert(tableService.getCurrentGame(table), GameDTO.class);
    }
}
