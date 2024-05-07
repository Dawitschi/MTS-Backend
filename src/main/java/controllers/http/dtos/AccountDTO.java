package main.java.controllers.http.dtos;

import java.util.List;

public record AccountDTO(Integer account_ID, List<Integer> player_IDs,
                         String password, String token, String username,
                         List<Integer> friend_IDs) {
}
