package main.java.services.DBServices;

import main.java.controllers.dtos.GameDTO;
import main.java.databank.game.game.Game;
import main.java.databank.game.game.GameRepository;
import main.java.databank.game.score.Score;
import main.java.databank.game.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TableService tableService;

    public Game getGamebyID(Integer id) {
        return gameRepository.findById(id).get();
    }

    public List<Game> getGames(Date from, Date to) {
        return gameRepository.findBytimeOfGameBetween(from,to);
    }
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game createGame(GameDTO gameDTO) {
        List<Team> teams = new ArrayList<>();
        teams.add(teamService.createTeam(gameDTO.team1ID()));
        teams.add(teamService.createTeam(gameDTO.team2ID()));

        Game game = new Game(gameDTO.game_ID(), gameDTO.timeOfGame(), teams,
                tableService.getTable(gameDTO.table_ID()), gameDTO.gameFinished(),new ArrayList<>());
        for (Team team: teams) team.setGame(game);
        gameRepository.save(game);
        return game;
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public boolean isTeamWinningTeam(Team team) {
        Game game = team.getGame();
        if(team.getTeam_ID() == game.getTeam1().getTeam_ID()) {
            return game.getTeam1Score() > game.getTeam2Score();
        } else return game.getTeam2Score() > game.getTeam1Score();
    }

    public void addScore(Game game, Score score) {
        List<Score> scores = game.getScores();
        scores.add(score);
        game.setScores(scores);
    }
}
