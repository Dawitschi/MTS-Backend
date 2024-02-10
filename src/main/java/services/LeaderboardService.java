package main.java.services;

import main.java.databank.game.Player;
import main.java.databank.game.Score;
import main.java.services.DBServices.PlayerService;
import main.java.services.DBServices.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ScoreService scoreService;

    public List<Player> getDailyLeaderboard() {
        //This here seems faulty
        LocalDateTime yesterday = LocalDate.now(ZoneId.systemDefault()).atStartOfDay();
        LocalDateTime tomorrow = LocalDate.now(ZoneId.systemDefault()).plusDays(1).atStartOfDay();
        List<Score> scores = scoreService.getScoresBetween(yesterday, tomorrow);
        HashMap<Player, Double> playerEloDifference = new HashMap<>();
        for (Score score : scores) {
            if(playerEloDifference.keySet().contains(score.getPlayer())) {
                Double eloDifference = playerEloDifference.get(score.getPlayer()) + score.getEloDifference();
                playerEloDifference.put(score.getPlayer(),eloDifference);
            } else playerEloDifference.put(score.getPlayer(), score.getEloDifference());
        }
        List<Player> players = new java.util.ArrayList<>(playerEloDifference.keySet().stream().toList());
        players.sort(Comparator.comparingDouble(playerEloDifference::get));
        return players;
    }

    public List<Player> getWeeklyLeaderboard() {
        //This here seems faulty
        LocalDateTime lastMonday = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).atStartOfDay();
        LocalDateTime nextMonday = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atStartOfDay();
        List<Score> scores = scoreService.getScoresBetween(lastMonday,nextMonday);
        HashMap<Player, Double> playerEloDifference = new HashMap<>();
        for (Score score : scores) {
            if(playerEloDifference.keySet().contains(score.getPlayer())) {
                Double eloDifference = playerEloDifference.get(score.getPlayer()) + score.getEloDifference();
                playerEloDifference.put(score.getPlayer(),eloDifference);
            } else playerEloDifference.put(score.getPlayer(), score.getEloDifference());
        }
        List<Player> players = new java.util.ArrayList<>(playerEloDifference.keySet().stream().toList());
        players.sort(Comparator.comparingDouble(playerEloDifference::get));
        return players;
    }

    public List<Player> getAllTimeLeaderboard() {
        List<Player> players = playerService.getAllPlayers();
        players.sort(Comparator.comparingDouble(Player::getElo));
        return players;
    }

}
