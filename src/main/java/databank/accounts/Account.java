package main.java.databank.accounts;

import jakarta.persistence.*;
import main.java.databank.game.Player;

import java.util.List;

@Entity
@Table(name = "Accounts") // Name of the Table
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer account_ID;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;


    private String password;

    private String token;


    private String username;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Account> friends;

    public Account() {}


    public Account(List<Player> players, String password, String token, String username, List<Account> friends) {
        this.players = players;
        this.password = password;
        this.token = token;
        this.username = username;
        this.friends = friends;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(Integer account_ID) {
        this.account_ID = account_ID;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Account> getFriends() {
        return friends;
    }

    public void setFriends(List<Account> friends) {
        this.friends = friends;
    }
}
