package main.java.services.DBServices;

import main.java.controllers.dtos.GameplayerDTO;
import main.java.controllers.dtos.TeamDTO;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.team.Team;
import main.java.databank.game.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    @Lazy
    private GameplayerService gameplayerService;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public Team createTeam(TeamDTO teamDTO) {
        Set<GamePlayer> gamePlayers = new HashSet<>();
        for (GameplayerDTO gameplayerDTO : teamDTO.player_IDs()) gamePlayers.add(gameplayerService.createGameplayer(gameplayerDTO));
        Team team = new Team(teamDTO.team_ID(), null, gamePlayers, teamDTO.score());
        for (GamePlayer gameplayer : gamePlayers) gameplayer.setTeams(team);
        return team;
    }

    public Team getTeambyID(Integer id) {
        return teamRepository.findById(id).get();
    }
}
