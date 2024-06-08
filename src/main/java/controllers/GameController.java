package main.java.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import main.java.controllers.dtos.GameDTO;
import main.java.controllers.validations.annotations.existsindb.ExistsInDB;
import main.java.controllers.validations.markers.onCreation;
import main.java.databank.game.game.Game;
import main.java.databank.game.table.Table;
import main.java.databank.game.table.TableRepository;
import main.java.services.DBServices.GameService;
import main.java.services.DBServices.TableService;
import main.java.services.EloCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Game")
@Validated
public class GameController {

    @Autowired
    private EloCalculatorService eloCalculatorService;

    @Autowired
    private TableService tableService;

    @Autowired
    private GameService gameService;

    @Autowired
    private DefaultConversionService defaultConversionService;


    @GetMapping(value = "/AllGames")
    public List<GameDTO> getGames() {
        List<Game> games = gameService.getGames();
        List<GameDTO> gameDTOS = new ArrayList<>();
        games.forEach(game -> gameDTOS.add(defaultConversionService.convert(game, GameDTO.class)));
        return gameDTOS;
    }

    @PostMapping(value = "/newGame")
    public ResponseEntity<Object> newGame(@RequestBody @Validated(value = onCreation.class) GameDTO gameDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder("The following errors where found: \n");
            bindingResult.getAllErrors().forEach(err -> builder.append(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(builder.toString());
        }
        Game game = gameService.createGame(gameDTO);
        eloCalculatorService.reevaluateElos(game);
        return ResponseEntity.ok(defaultConversionService.convert(game, GameDTO.class));
    }

    @GetMapping(value = "/fromTable/{tableID}")
    public ResponseEntity<Object> getGamesFromTable(@ExistsInDB(repo = TableRepository.class) @PathVariable @Validated Integer tableID,
                                                    @RequestBody @Validated @Min(value = 1) @Max(value = 100) Integer n, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder("The following errors where found: \n");
            bindingResult.getAllErrors().forEach(err -> builder.append(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(builder.toString());
        }
        Table table = tableService.getTable(tableID);
        List<Game> games = tableService.getRecentGames(table, n);
        List<GameDTO> gameDTOS = new ArrayList<>();
        games.forEach(game -> gameDTOS.add(defaultConversionService.convert(game, GameDTO.class)));
        return ResponseEntity.ok(gameDTOS);
    }

    @GetMapping(value = "/fromTable/{tableID}/all")
    public ResponseEntity<Object> getGamesFromTable(@PathVariable @Validated @ExistsInDB(repo = TableRepository.class) Integer tableID, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder("The following errors where found: \n");
            bindingResult.getAllErrors().forEach(err -> builder.append(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(builder.toString());
        }
        Table table = tableService.getTable(tableID);
        List<Game> games = table.getGames();
        List<GameDTO> gameDTOS = new ArrayList<>();
        games.forEach(game -> gameDTOS.add(defaultConversionService.convert(game, GameDTO.class)));
        return ResponseEntity.ok(gameDTOS);
    }

    @GetMapping(value = "/fromTableOngoing")
    public ResponseEntity<Object> getCurrentGameFromTable(@RequestBody @Validated @ExistsInDB(repo = TableRepository.class) Integer tableID, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder("The following errors where found: \n");
            bindingResult.getAllErrors().forEach(err -> builder.append(err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(builder.toString());
        }
        Table table = tableService.getTable(tableID);
        return ResponseEntity.ok(defaultConversionService.convert(tableService.getCurrentGame(table), GameDTO.class));
    }
}
