package main.java.services;

import main.java.databank.game.player.Player;
import main.java.databank.game.score.Score;
import main.java.services.DBServices.PlayerService;
import main.java.services.DBServices.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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

        return getPlayersSorted(yesterday, tomorrow);
    }

    public List<Player> getWeeklyLeaderboard() {
        //This here seems faulty
        LocalDateTime lastMonday = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).atStartOfDay();
        LocalDateTime nextMonday = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atStartOfDay();
        return getPlayersSorted(lastMonday, nextMonday);
    }

    private List<Player> getPlayersSorted(LocalDateTime start, LocalDateTime end) {
        List<Score> scores = scoreService.getScoresBetween(start,end);
        HashMap<Player, Double> playerEloDifference = getPlayerEloDifference(scores);
        List<Player> players = new ArrayList<>(playerEloDifference.keySet().stream().toList());
        players.sort(Comparator.comparingDouble(playerEloDifference::get));
        return players;
    }

    private HashMap<Player, Double> getPlayerEloDifference(List<Score> scores) {
        HashMap<Player, Double> playerEloDifference = new HashMap<>();
        for (Score score : scores) {
            if(playerEloDifference.containsKey(score.getPlayer())) {
                Double eloDifference = playerEloDifference.get(score.getPlayer()) + score.getEloDifference();
                playerEloDifference.put(score.getPlayer(),eloDifference);
            } else playerEloDifference.put(score.getPlayer(), score.getEloDifference());
        }
        return playerEloDifference;
    }


    public List<Player> getAllTimeLeaderboard() {
        List<Player> players = playerService.getAllPlayers();
        players.sort(Comparator.comparingDouble(Player::getElo));
        return players;
    }

}
