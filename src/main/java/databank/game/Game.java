package main.java.databank.game;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Game represents a played (or eventually later a in progress) game.
 * It has only all relevant characteristics of a game, for those look at the comments of the values.
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
@Table(name = "Games") //Name of the Table
public class Game {

    /**
     * The ID of the Game (mainly used to identify every game without duplicates in the database)
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer game_ID;

    /**
     * The time the game was "played" (more like admitted to the system)
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeOfGame;

    /**
     * The first team of the game
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> teams;

    @ManyToOne
    private main.java.databank.game.Table table;

    /**
     * The first teams final score (TODO: Delete setters)
     */
    private Integer team1Score;

    /**
     * The second teams final score (TODO: Delete setters)
     */
    private Integer team2Score;

    /**
     * If the game has finished (just for possible future uses in case a game can be played live on like a website or sth)
     */
    private boolean gameFinished;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> scores;

    /**
     * An empty constructor, needed for spring. Will throw error if deleted
     */
    public Game() {
        this.scores = new ArrayList<>();
        this.teams = new ArrayList<>();
        teams.add(null);
        teams.add(null);
    }

    /**
     * The basic constructor of the game, setting everything of the game.
     * @param time The time the game was "played" (more like admitted to the system)
     * @param team1 The first team of the game
     * @param team2 The second team of the game
     * @param team1Score The first teams final score (TODO: Delete setters)
     * @param team2Score The second teams final score (TODO: Delete setters)
     * @param gameFinished If the game has finished (just for possible future uses in case a game can be played live on like a website or sth)
     */
    public Game(LocalDateTime time, Team team1, Team team2, Integer team1Score, Integer team2Score, boolean gameFinished) {
        this.timeOfGame = time;
        this.teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.gameFinished = gameFinished;
        this.scores = new ArrayList<>();
    }

    public Game(Integer game_ID, LocalDateTime time, Team team1, Team team2, Integer team1Score, Integer team2Score, boolean gameFinished) {
        this.game_ID = game_ID;
        this.timeOfGame = time;
        this.teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.gameFinished = gameFinished;
        this.scores = new ArrayList<>();
    }

    public Game(Integer game_ID, LocalDateTime timeOfGame, List<Team> teams,
                main.java.databank.game.Table table, Integer team1Score,
                Integer team2Score, boolean gameFinished, List<Score> scores) {
        this.game_ID = game_ID;
        this.timeOfGame = timeOfGame;
        this.teams = teams;
        this.table = table;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.gameFinished = gameFinished;
        this.scores = scores;
    }

    //Methods of the Game


    //Getters and Setters


    public main.java.databank.game.Table getTable() {
        return table;
    }

    public void setTable(main.java.databank.game.Table table) {
        this.table = table;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Team getTeam1() {
        return teams.get(0);
    }

    public void setTeam1(Team team1) {
        teams.set(0, team1);
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public Integer getGame_ID() {
        return game_ID;
    }

    public void setGame_ID(Integer game_ID) {
        this.game_ID = game_ID;
    }

    public LocalDateTime getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(LocalDateTime timeOfGame) {
        this.timeOfGame = timeOfGame;
    }

    public Team getTeam2() {
        return teams.get(1);
    }

    public void setTeam2(Team team2) {
        teams.set(1, team2);
    }

    public Integer getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(Integer team1Score) {
        this.team1Score = team1Score;
    }

    public Integer getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(Integer team2Score) {
        this.team2Score = team2Score;
    }
}
