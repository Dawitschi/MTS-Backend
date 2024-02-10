package main.java.databank.game;

import jakarta.persistence.*;
import jakarta.persistence.Table;

/**
 * Basically extra information to a player ONLY regarding one certain played Game.
 * Also used to keep a history of the matches of players with their at the time elo, etc.
 *
 * It is stored in a database via the Spring dependency's (don't put your life on this info):
 *  *      -org.springframework.boot:spring-boot-starter-data-jpa      (safe)
 *  *      -org.springframework.boot:spring-boot-starter-jdbc          (safe)
 *  *      -org.springframework.boot:spring-boot-starter-data-jdbc     (safeish)
 *  *      and maybe some others idgaf
 *  *
 *  * The Annotations are rather self-explanatory or commented.
 */
@Entity
@Table(name = "Gameplayers") //Name of the Table
public class GamePlayer {

    /**
     * The ID of the GamePlayer (mainly used to identify every GamePlayer without duplicates in the database)
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer gamesPlayer_ID;

    /**
     * The position the GamePlayer played as: Basically Front or Back
     */
    private int position;

    /**
     * The elo of the player at the time of the game.
     */
    private double elo;

    /**
     * The corresponding player to this GamePlayer
     */
    @ManyToOne
    private Player player;

    /**
     * The corresponding team to this GamePlayer
     */
    @ManyToOne
    private Team teams;

    /**
     * An empty constructor, needed for spring. Will throw error if deleted
     */
    public GamePlayer(){}

    /**
     * Constructor for a GamePlayer
     * @param position The position the GamePlayer played in.
     * @param player The corresponding player to this GamePlayer
     * @param team The corresponding team to this GamePlayer
     * @param elo The elo of the player at the time of the game.
     */
    public GamePlayer(int position, Player player, Team team, double elo) {
        this.position = position;
        this.player = player;
        this.teams = team;
        this.elo = elo;
    }

    public GamePlayer(int gamesPlayer_ID,int position, Player player, Team team, double elo) {
        this.gamesPlayer_ID = gamesPlayer_ID;
        this.position = position;
        this.player = player;
        this.teams = team;
        this.elo = elo;
    }
    //Methods

    //Getters and Setters
    public double getElo() {
        return elo;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player players) {
        this.player = players;
    }

    public Team getTeams() {
        return teams;
    }

    public void setTeams(Team teams) {
        this.teams = teams;
    }

    public Integer getGamePlayer_ID() {
        return gamesPlayer_ID;
    }

    public void setGamePlayer_ID(Integer gamesPlayer_ID) {
        this.gamesPlayer_ID = gamesPlayer_ID;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
