package main.java.databank.game.game;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import main.java.databank.game.score.Score;
import main.java.databank.game.team.Team;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer game_ID;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeOfGame;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> teams;

    @ManyToOne
    private main.java.databank.game.table.Table table;

    private boolean gameFinished;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> scores;

    public Game() {

    }

    public Game(LocalDateTime time, Team team1, Team team2, boolean gameFinished) {
        this.timeOfGame = time;
        this.teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        this.gameFinished = gameFinished;
        this.scores = new ArrayList<>();
    }

    public Game(Integer game_ID, LocalDateTime time, Team team1, Team team2, boolean gameFinished) {
        this.game_ID = game_ID;
        this.timeOfGame = time;
        this.teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        this.gameFinished = gameFinished;
        this.scores = new ArrayList<>();
    }

    public Game(Integer game_ID, LocalDateTime timeOfGame, List<Team> teams,
                main.java.databank.game.table.Table table, boolean gameFinished, List<Score> scores) {
        this.game_ID = game_ID;
        this.timeOfGame = timeOfGame;
        this.teams = teams;
        this.table = table;
        this.gameFinished = gameFinished;
        this.scores = scores;
    }

    public main.java.databank.game.table.Table getTable() {
        return table;
    }

    public void setTable(main.java.databank.game.table.Table table) {
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
        return teams.get(0).getScore();
    }

    public Integer getTeam2Score() {
        return teams.get(1).getScore();
    }

}
