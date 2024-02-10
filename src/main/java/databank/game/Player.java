package main.java.databank.game;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import main.java.databank.accounts.Account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Player represents a Person playing the game.
 * It allows tracking of that persons activities and other things such as its capabilities(elo)
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
@Table(name = "Players") // Name of the Table
public class Player {

    /**
     * The initial Elo every player gets when they start out.
     */
    private final double INITIAL_ELO = 1000.0d;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer player_ID;

    /**
     * The elo of the player, basically a numeric representation of the players generic skill level.
     */
    private double elo;

    /**
     * The name of the player
     */
    private String name;

    /**
     * The players chosen nickname
     */
    private String nickname;

    /**
     * The players related Gameplayers (basically sort of historical record of all the times the player played in a game)
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> scores;

    /**
     * An empty constructor, needed for spring. Will throw error if deleted
     */
    public Player() {}

    /**
     * Constructor for a player that is new.
     * Player gets the initial elo
     * @param name The players name
     * @param nickname The players chosen nickname
     */
    public Player(String name, String nickname) {
        this.elo = INITIAL_ELO;
        this.name = name;
        this.nickname = nickname;
        this.scores = new ArrayList<>();
    }

    /**
     * Constructor for a player that should be placed at a specific elo
     * @param name The players name
     * @param nickname The players chosen nickname
     */
    public Player(String name, String nickname, double elo) {
        this.elo = elo;
        this.name = name;
        this.nickname = nickname;
        this.scores = new ArrayList<>();
    }

    public Player(Account account, String name, String nickname) {
        this.account = account;
        this.elo = INITIAL_ELO;
        this.name = name;
        this.nickname = nickname;
        this.gamePlayers = new HashSet<>();
        this.scores = new ArrayList<>();
    }

    public Player(Account account, Integer player_ID, double elo, String name, String nickname, Set<GamePlayer> gamePlayers, List<Score> scores) {
        this.account = account;
        this.player_ID = player_ID;
        this.elo = elo;
        this.name = name;
        this.nickname = nickname;
        this.gamePlayers = gamePlayers;
        this.scores = scores;
    }
    //Methods

    //Getters and Setters

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public Integer getPlayer_ID() {
        return player_ID;
    }

    public void setPlayer_ID(Integer player_ID) {
        this.player_ID = player_ID;
    }

    public double getElo() {
        return elo;
    }

    public void setElo(double elo) {
        this.elo = elo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
