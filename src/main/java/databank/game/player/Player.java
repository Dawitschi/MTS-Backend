package main.java.databank.game.player;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import main.java.databank.accounts.Account;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.score.Score;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Players")
public class Player {
    private final double INITIAL_ELO = 1000.0d;

    @ManyToOne
    private Account account;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer player_ID;

    private double elo;

    private String name;

    private String nickname;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> scores;

    public Player() {}

    public Player(String name, String nickname) {
        this.elo = INITIAL_ELO;
        this.name = name;
        this.nickname = nickname;
        this.scores = new ArrayList<>();
    }

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
