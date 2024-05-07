package main.java.controllers.http.converters.game;

import main.java.controllers.http.converters.AbstractTwoWayConverter;
import main.java.controllers.http.dtos.GameDTO;
import main.java.databank.game.game.Game;
import main.java.databank.game.score.Score;
import main.java.databank.game.table.Table;
import main.java.databank.game.team.Team;
import main.java.services.DBServices.ScoreService;
import main.java.services.DBServices.TableService;
import main.java.services.DBServices.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TwoWayGameConverter extends AbstractTwoWayConverter<Game, GameDTO> {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TableService tableService;

    @Autowired
    private ScoreService scoreService;

    @Override
    protected GameDTO convert(Game source) {
        List<Integer> score_IDs = new ArrayList<>();
        for(Score score : source.getScores()) score_IDs.add(score.getScore_ID());
        return new GameDTO(source.getGame_ID(), source.getTimeOfGame(), source.isGameFinished(),
                source.getTeam1().getTeam_ID(),source.getTeam2().getTeam_ID(),
                source.getTeam1Score(), source.getTeam2Score(), source.getTable().getTable_ID(), score_IDs);
    }

    @Override
    protected Game convertBack(GameDTO target) {
        Team team1 = teamService.getTeambyID(target.team1ID());
        Team team2 = teamService.getTeambyID(target.team2ID());
        Table table = tableService.getTable(target.table_ID());
        List<Team> teamList = new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);
        List<Score> scores = new ArrayList<>();
        for(Integer id : target.score_IDs()) scores.add(scoreService.getScore(id));
        return new Game(target.game_ID(),target.timeOfGame(), teamList, table,
                target.team1Score(), target.team2Score(), target.gameFinished(), scores);
    }
}
