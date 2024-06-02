package main.java.controllers;

import main.java.controllers.dtos.PlayerDTO;
import main.java.databank.game.player.Player;
import main.java.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @Autowired
    private DefaultConversionService defaultConversionService;

    @GetMapping("/Daily")
    public List<PlayerDTO> getDailyLeaderboard() {
        List<PlayerDTO> list = new ArrayList<>();
        for (Player player : leaderboardService.getDailyLeaderboard()) list.add(defaultConversionService.convert(player, PlayerDTO.class));
        return list;
    }

    @GetMapping("/Weekly")
    public List<PlayerDTO> getWeeklyLeaderboard() {
        List<PlayerDTO> list = new ArrayList<>();
        for (Player player : leaderboardService.getWeeklyLeaderboard()) list.add(defaultConversionService.convert(player, PlayerDTO.class));
        return list;
    }

    @GetMapping("/Alltime")
    public List<PlayerDTO> getAllTimeLeaderboard() {
        List<PlayerDTO> list = new ArrayList<>();
        for (Player player : leaderboardService.getAllTimeLeaderboard()) list.add(defaultConversionService.convert(player, PlayerDTO.class));
        return list;
    }

}
