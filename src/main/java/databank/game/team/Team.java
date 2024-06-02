package main.java.databank.game.team;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import main.java.databank.game.game.Game;
import main.java.databank.game.gameplayer.GamePlayer;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

@Entity
@Table(name = "Teams")
public class Team {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer team_ID;

    @ManyToOne
    private Game game;

    private Integer score;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<GamePlayer> players;

    public Team() {}

    public Team(Integer team_ID, Game game, Set<GamePlayer> players, Integer score) {
        this.team_ID = team_ID;
        this.game = game;
        this.players = players;
        this.score = score;
    }

    //Methods
    public double getTotalElo() {
        double sum = 0;
        for(GamePlayer gamePlayer: players) sum += gamePlayer.getElo();
        return sum;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
