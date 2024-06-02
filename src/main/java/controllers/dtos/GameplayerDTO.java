package main.java.controllers.dtos;

import jakarta.validation.constraints.Null;
import main.java.controllers.validations.annotations.existsindb.ExistsInDB;
import main.java.databank.game.player.PlayerRepository;
import main.java.databank.game.team.TeamRepository;

public record GameplayerDTO(
        @Null
        Integer gamesPlayer_ID,
        int position,
        double elo,
        @ExistsInDB(repo = PlayerRepository.class)
        Integer player_ID,
        @ExistsInDB(repo = TeamRepository.class)
        Integer team_ID
)
{
}
