package main.java.controllers.http.objects;

import java.util.List;

public record TeamRepresentation(Integer team_ID, Integer game_ID, List<Integer> player_IDs) {
}
