package main.java.controllers.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import main.java.controllers.validations.annotations.existsindb.ExistsInDB;
import main.java.controllers.validations.markers.onCreation;
import main.java.controllers.validations.markers.onUpdate;
import main.java.databank.accounts.AccountRepository;
import main.java.databank.game.game.GameRepository;

import java.util.Set;

public record PlayerDTO(
        @NotNull()
        @ExistsInDB(repo = AccountRepository.class)
        Integer account_ID,
        @Null(groups = onCreation.class)
        @NotNull(groups = onUpdate.class)
        @ExistsInDB(repo = GameRepository.class, groups = onUpdate.class)
        Integer player_ID,
        double elo,
        String name,
        String nickname,
        Set<Integer> gamePlayer_IDs) {
}
