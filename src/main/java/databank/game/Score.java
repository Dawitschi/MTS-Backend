package main.java.databank.game;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "Score") //Name of the Table
public class Score {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer score_ID;

    @ManyToOne(cascade = CascadeType.ALL)
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;

    private LocalDateTime date;

    private boolean won;

    private Double eloDifference;

    private Integer playerScore;

    private Integer enemyScore;

    public Score() {}

    public Score(Player player, Game game, LocalDateTime date, boolean won, Double eloDifference, Integer playerScore, Integer enemyScore) {
        this.player = player;
        this.game = game;
        this.date = date;
        this.won = won;
        this.eloDifference = eloDifference;
        this.playerScore = playerScore;
        this.enemyScore = enemyScore;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getScore_ID() {
        return score_ID;
    }

    public void setScore_ID(Integer score_ID) {
        this.score_ID = score_ID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public Double getEloDifference() {
        return eloDifference;
    }

    public void setEloDifference(Double eloDifference) {
        this.eloDifference = eloDifference;
    }

    public Integer getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(Integer playerScore) {
        this.playerScore = playerScore;
    }

    public Integer getEnemyScore() {
        return enemyScore;
    }

    public void setEnemyScore(Integer enemyScore) {
        this.enemyScore = enemyScore;
    }
}
