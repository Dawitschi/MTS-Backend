package main.java.controllers.converters.game;

import main.java.controllers.converters.AbstractTwoWayConverter;
import main.java.controllers.dtos.GameDTO;
import main.java.controllers.dtos.TeamDTO;
import main.java.databank.game.game.Game;
import main.java.databank.game.score.Score;
import main.java.databank.game.table.Table;
import main.java.databank.game.team.Team;
import main.java.services.DBServices.ScoreService;
import main.java.services.DBServices.TableService;
import main.java.services.DBServices.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.support.DefaultConversionService;
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

    @Autowired
    @Lazy
    private DefaultConversionService defaultConversionService;

    @Override
    protected GameDTO convert(Game source) {
        List<Integer> score_IDs = new ArrayList<>();
        for(Score score : source.getScores()) score_IDs.add(score.getScore_ID());
        return new GameDTO(source.getGame_ID(), source.getTimeOfGame(), source.isGameFinished(),
                defaultConversionService.convert(source.getTeam1(), TeamDTO.class),
                defaultConversionService.convert(source.getTeam2(), TeamDTO.class),
                source.getTable().getTable_ID(), score_IDs);
    }

    @Override
    protected Game convertBack(GameDTO target) {
        Table table = tableService.getTable(target.table_ID());
        List<Score> scores = new ArrayList<>();
        for(Integer id : target.score_IDs()) scores.add(scoreService.getScore(id));
        List<Team> teamList = new ArrayList<>();
        Team team1 = defaultConversionService.convert(target.team1ID(), Team.class);
        Team team2 = defaultConversionService.convert(target.team2ID(), Team.class);
        teamList.add(team1);
        teamList.add(team2);
        return new Game(target.game_ID(),target.timeOfGame(), teamList, table, target.gameFinished(), scores);
    }
}
