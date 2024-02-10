package main.java.controllers.http.objects;

import java.time.LocalDateTime;
import java.util.List;

public record GameRepresentation(Integer game_ID, LocalDateTime timeOfGame, boolean gameFinished,
                                 Integer team1ID, Integer team2ID, Integer team1Score, Integer team2Score,
                                 Integer table_ID, List<Integer> score_IDs) {
}
