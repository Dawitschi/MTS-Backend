package main.java.controllers.http.dtos;

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
        Integer team1ID,
        Integer team2ID,
        Integer team1Score,
        Integer team2Score,
        @ExistsInDB(repo = TableRepository.class)
        Integer table_ID,
        List<Integer> score_IDs) {
}
