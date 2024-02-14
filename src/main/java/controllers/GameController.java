package main.java.controllers;

import main.java.controllers.http.objects.GameRepresentation;
import main.java.controllers.validators.GameCreationInfoValidator;
import main.java.databank.game.Game;
import main.java.databank.game.Table;
import main.java.services.DBServices.TableService;
import main.java.services.EloCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.WebDataBinder;
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

    /**
     * @param tableID The table the games where played on
     * @param n The number of games
     * @return the n most recent games played on a Table
     */
    @GetMapping(value = "/fromTable")
    public List<GameRepresentation> getGamesFromTable(Integer tableID,Integer n) {
        Table table = tableService.getTable(tableID);
        List<Game> games = tableService.getRecentGames(table, n);
        List<GameRepresentation> gameRepresentations = new ArrayList<>();
        games.forEach(game -> gameRepresentations.add(defaultConversionService.convert(game, GameRepresentation.class)));
        return gameRepresentations;
    }

    /**
     * TODO:    This might be problematic over a large amount of time, however with the currently projected usage
     *          of this project there seems no need to change from this solution to an alternative. Alternatives may be:
     *          -getGamesOfTableFromTo(int startIndex, int endIndex)
     *          -or just to delete this and just solely use getGamesFromTable(Integer tableID,Integer n)
     * @param tableID The Table
     * @return All games played on that table
     */
    @GetMapping(value = "/fromTable")
    public List<GameRepresentation> getGamesFromTable(Integer tableID) {
        Table table = tableService.getTable(tableID);
        List<Game> games = table.getGames();
        List<GameRepresentation> gameRepresentations = new ArrayList<>();
        games.forEach(game -> gameRepresentations.add(defaultConversionService.convert(game, GameRepresentation.class)));
        return gameRepresentations;
    }




}
