package main.java.databank.game;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.Set;

/**
 * A Team represents a number of players playing collaboratively together against another team.
 *
 * It is stored in a database via the Spring dependency's (don't put your life on this info):
 *      -org.springframework.boot:spring-boot-starter-data-jpa      (safe)
 *      -org.springframework.boot:spring-boot-starter-jdbc          (safe)
 *      -org.springframework.boot:spring-boot-starter-data-jdbc     (safeish)
 *      and maybe some others idgaf
 *
 * The Annotations are rather self-explanatory or commented.
 */
@Entity
@Table(name = "Teams")
public class Team {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer team_ID;

    /**
     * The game the team played in.
     */
    @ManyToOne
    private Game game;

    /**
     * The GamePlayers in the team
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<GamePlayer> players;

    /**
     * An empty constructor, needed for spring. Will throw error if deleted
     */
    public Team() {}

    /**
     * A basic Constructor for a team setting all its members
     * @param gamePlayers The GamePlayers in the Team
     */
    public Team(Set<GamePlayer> gamePlayers) {
        this.players = gamePlayers;
    }

    public Team(Integer team_ID, Game game, Set<GamePlayer> players) {
        this.team_ID = team_ID;
        this.game = game;
        this.players = players;
    }

    //Methods
    public double getTotalElo() {
        double sum = 0;
        for(GamePlayer gamePlayer: players) sum += gamePlayer.getElo();
        return sum;
    }

    public Integer size() {
        return players.size();
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    //Getters and Setters
    public Integer getTeam_ID() {
        return team_ID;
    }

    public void setTeam_ID(Integer team_ID) {
        this.team_ID = team_ID;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<GamePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(Set<GamePlayer> players) {
        this.players = players;
    }
}
