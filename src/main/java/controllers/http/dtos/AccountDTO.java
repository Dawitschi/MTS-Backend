package main.java.controllers.http.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import main.java.controllers.validators.markers.onCreation;

import java.util.List;

public record AccountDTO(
                        @NotNull
                        @Null(groups = onCreation.class)
                        Integer account_ID,
                        List<Integer> player_IDs,
                        @Size(max = 32, min = 8)
                        String password,
                        String token,
                        @Size(max = 32, min = 8)
                        String username,
                        List<Integer> friend_IDs) {
}
