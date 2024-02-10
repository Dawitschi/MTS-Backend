package main.java.controllers.http.converters.game.game;

import main.java.controllers.http.objects.GameRepresentation;
import main.java.databank.game.Game;
import main.java.databank.game.Score;
import main.java.databank.game.Table;
import main.java.databank.game.Team;
import main.java.services.DBServices.ScoreService;
import main.java.services.DBServices.TableService;
import main.java.services.DBServices.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class GameConverter implements Converter<GameRepresentation, Game> {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TableService tableService;

    @Autowired
    private ScoreService scoreService;

    @Override
    public Game convert(GameRepresentation source) {
        Team team1 = teamService.getTeambyID(source.team1ID());
        Team team2 = teamService.getTeambyID(source.team2ID());
        Table table = tableService.getTable(source.table_ID());
        List<Team> teamList = new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);
        List<Score> scores = new ArrayList<>();
        for(Integer id : source.score_IDs()) scores.add(scoreService.getScore(id));
        return new Game(source.game_ID(),source.timeOfGame(), teamList, table,
                source.team1Score(), source.team2Score(), source.gameFinished(), scores);
    }
}
