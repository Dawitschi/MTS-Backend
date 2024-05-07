package main.java.services.DBServices;

import main.java.databank.game.score.Score;
import main.java.databank.game.score.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getScoresBetween(LocalDateTime from,LocalDateTime to) {
       return scoreRepository.findAllByDateBetween(from, to);
    }

    public void  saveScore(Score score) {
        scoreRepository.save(score);
    }

    public Score getScore(Integer id) {
        return scoreRepository.getReferenceById(id);
    }

}
