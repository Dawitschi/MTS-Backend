package main.java.databank.game.gameplayer;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import main.java.databank.game.team.Team;
import main.java.databank.game.player.Player;

@Entity
@Table(name = "Gameplayers")
public class GamePlayer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer gamesPlayer_ID;

    private int position;

    private double elo;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Team teams;

    public GamePlayer(){}

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
