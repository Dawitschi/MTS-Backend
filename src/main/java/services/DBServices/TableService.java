package main.java.services.DBServices;

import main.java.databank.game.Game;
import main.java.databank.game.Table;
import main.java.databank.game.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public void saveTable(Table table) {
        tableRepository.save(table);
    }

    public Table getTable(Integer id) {
        return tableRepository.getReferenceById(id);
    }

    /**
     * @return all Tables
     */
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    /**
     * @param table The table the games were played on
     * @param n The number of games
     * @return the n most recent games played on a Table
     */
    public List<Game> getRecentGames(Table table, Integer n) {
        //TODO: This can (probably) be rewritten as a sql Query
        List<Game> games = table.getGames();
        games.sort(Comparator.comparing(Game::getTimeOfGame));
        return games.subList(0, n);
    }

    /**
     * @param table the table
     * @return the game being currently played or null
     */
    public Game getCurrentGame(Table table) {
        return tableRepository.findCurrentGame(table.getTable_ID());
    }

}
