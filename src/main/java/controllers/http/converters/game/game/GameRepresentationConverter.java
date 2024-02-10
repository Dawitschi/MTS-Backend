package main.java.controllers.http.converters.game.game;

import main.java.controllers.http.objects.GameRepresentation;
import main.java.databank.game.Game;
import main.java.databank.game.Score;
import main.java.services.DBServices.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class GameRepresentationConverter implements Converter<Game, GameRepresentation> {

    @Autowired
    private TeamService teamService;

    @Override
    public GameRepresentation convert(Game source) {
        List<Integer> score_IDs = new ArrayList<>();
        for(Score score : source.getScores()) score_IDs.add(score.getScore_ID());
        return new GameRepresentation(source.getGame_ID(), source.getTimeOfGame(), source.isGameFinished(),
                source.getTeam1().getTeam_ID(),source.getTeam2().getTeam_ID(),
                source.getTeam1Score(), source.getTeam2Score(), source.getTable().getTable_ID(), score_IDs);
    }
}
