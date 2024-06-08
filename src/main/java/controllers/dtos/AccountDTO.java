package main.java.controllers.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import main.java.controllers.validations.annotations.existsindb.ExistsInDB;
import main.java.controllers.validations.markers.onCreation;
import main.java.controllers.validations.markers.onUpdate;
import main.java.databank.accounts.AccountRepository;

import java.util.List;

public record AccountDTO(
                        @Null(groups = onCreation.class)
                        @NotNull(groups = onUpdate.class)
                        @ExistsInDB(repo = AccountRepository.class, groups = onUpdate.class)
                        Integer account_ID,
                        @Null(groups = onCreation.class)
                        @Null(groups = onUpdate.class)
                        List<Integer> player_IDs,
                        @Size(max = 32, min = 8)
                        String password,
                        String creation_Token,
                        @Size(max = 32, min = 8)
                        String username,
                        @Null(groups = onCreation.class)
                        List<Integer> friend_IDs) {
}
