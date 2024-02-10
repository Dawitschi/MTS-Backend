package main.java.controllers.http.objects;

import java.util.List;

public record AccountRepresentation(Integer account_ID, List<Integer> player_IDs,
                                    String password, String token, String username,
                                    List<Integer> friend_IDs) {
}
