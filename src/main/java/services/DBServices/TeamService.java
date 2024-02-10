package main.java.services.DBServices;

import main.java.databank.game.Team;
import main.java.databank.game.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    /**
     * The teamRepository is basically the connection between the DAO and the Team table
     */
    @Autowired
    private TeamRepository teamRepository;

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public Team getTeambyID(Integer id) {
        return teamRepository.findById(id).get();
    }
}
