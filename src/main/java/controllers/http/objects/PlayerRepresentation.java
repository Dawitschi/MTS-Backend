package main.java.controllers.http.objects;

import java.util.Set;

public record PlayerRepresentation(Integer account_ID, Integer player_ID, double elo, String name,
                                   String nickname, Set<Integer> gamePlayer_IDs) {
}
