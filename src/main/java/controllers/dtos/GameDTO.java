package main.java.controllers.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import main.java.controllers.validations.annotations.existsindb.ExistsInDB;
import main.java.controllers.validations.markers.onCreation;
import main.java.controllers.validations.markers.onUpdate;
import main.java.databank.game.game.GameRepository;
import main.java.databank.game.table.TableRepository;

import java.time.LocalDateTime;
import java.util.List;

public record GameDTO(
        @Null(groups = onCreation.class)
        @NotNull(groups = onUpdate.class)
        @ExistsInDB(repo = GameRepository.class, groups = onUpdate.class)
        Integer game_ID,
        LocalDateTime timeOfGame,
        boolean gameFinished,
        TeamDTO team1ID,
        TeamDTO team2ID,
        @ExistsInDB(repo = TableRepository.class)
        Integer table_ID,
        @Null(groups = onCreation.class)
        List<Integer> score_IDs) {
}
