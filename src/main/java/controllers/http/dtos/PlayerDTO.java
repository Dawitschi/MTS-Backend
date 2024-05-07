package main.java.controllers.http.dtos;

import java.util.Set;

public record PlayerDTO(Integer account_ID, Integer player_ID, double elo, String name,
                        String nickname, Set<Integer> gamePlayer_IDs) {
}
