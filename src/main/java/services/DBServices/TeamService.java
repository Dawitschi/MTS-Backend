package main.java.services.DBServices;

import main.java.databank.game.team.Team;
import main.java.databank.game.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public Team getTeambyID(Integer id) {
        return teamRepository.findById(id).get();
    }
}
