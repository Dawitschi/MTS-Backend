package main.java.controllers.http.dtos;

import java.util.List;

public record TeamDTO(Integer team_ID, Integer game_ID, List<Integer> player_IDs) {
}
