package main.java.controllers.http.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import main.java.controllers.validations.annotations.existsindb.ExistsInDB;
import main.java.controllers.validations.markers.onCreation;
import main.java.controllers.validations.markers.onUpdate;
import main.java.databank.game.team.TeamRepository;

import java.util.List;

public record TeamDTO(
        @Null(groups = onCreation.class)
        @NotNull(groups = onUpdate.class)
        @ExistsInDB(repo = TeamRepository.class, groups = onUpdate.class)
        Integer team_ID,
        Integer game_ID,
        List<Integer> player_IDs)
{
}
