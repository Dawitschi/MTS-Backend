package main.java.services.DBServices;

import main.java.databank.game.game.Game;
import main.java.databank.game.game.GameRepository;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.player.Player;
import main.java.databank.game.score.Score;
import main.java.databank.game.table.Table;
import main.java.databank.game.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game getGamebyID(Integer id) {
        return gameRepository.findById(id).get();
    }

    public List<Game> getGames(Date from, Date to) {
        return gameRepository.findBytimeOfGameBetween(from,to);
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

    public Game createGame(Player a1, Player a2, Player b1, Player b2, Integer scoreA, Integer scoreB ) {
        Game game = new Game();
        gameRepository.save(game);

        Set<GamePlayer> teamAGameplayers = new HashSet<>();
        Set<GamePlayer> teamBGameplayers = new HashSet<>();

        Team teamA = new Team(teamAGameplayers);
        Team teamB = new Team(teamBGameplayers);

        GamePlayer ga1 = new GamePlayer(-1,a1,teamA,a1.getElo());
        GamePlayer ga2 = new GamePlayer(-1,a2,teamA,a2.getElo());
        GamePlayer gb1 = new GamePlayer(-1,b1,teamB,b1.getElo());
        GamePlayer gb2 = new GamePlayer(-1,b2,teamB,b2.getElo());

        teamAGameplayers.add(ga1);
        teamAGameplayers.add(ga2);
        teamBGameplayers.add(gb1);
        teamBGameplayers.add(gb2);

        game.setTimeOfGame(LocalDateTime.now());
        game.setTeam1(teamA);
        game.setTeam2(teamB);
        game.setTeam1Score(scoreA);
        game.setTeam2Score(scoreB);
        game.setGameFinished(true);
        gameRepository.save(game);

        return game;
    }

    public Game createGame(LocalDateTime localDateTime,Player a1,Player a2, Player b1, Player b2, Integer scoreA, Integer scoreB , boolean gamefinished, Table table) {
        Game game = new Game();

        Set<GamePlayer> teamAGameplayers = new HashSet<>();
        Set<GamePlayer> teamBGameplayers = new HashSet<>();

        Team teamA = new Team(teamAGameplayers);
        teamA.setGame(game);
        Team teamB = new Team(teamBGameplayers);
        teamB.setGame(game);

        GamePlayer ga1 = new GamePlayer(-1,a1,teamA,a1.getElo());
        GamePlayer ga2 = new GamePlayer(-1,a2,teamA,a2.getElo());
        GamePlayer gb1 = new GamePlayer(-1,b1,teamB,b1.getElo());
        GamePlayer gb2 = new GamePlayer(-1,b2,teamB,b2.getElo());

        teamAGameplayers.add(ga1);
        teamAGameplayers.add(ga2);
        teamBGameplayers.add(gb1);
        teamBGameplayers.add(gb2);

        game.setTimeOfGame(localDateTime);
        game.setTeam1(teamA);
        game.setTeam2(teamB);
        game.setTeam1Score(scoreA);
        game.setTeam2Score(scoreB);
        game.setGameFinished(gamefinished);
        game.setTable(table);
        gameRepository.save(game);
        return game;
    }


    public void addScore(Game game, Score score) {
        List<Score> scores = game.getScores();
        scores.add(score);
        game.setScores(scores);
    }
}
