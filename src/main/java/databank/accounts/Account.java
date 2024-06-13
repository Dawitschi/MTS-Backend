package main.java.databank.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import main.java.databank.game.player.Player;
import main.java.services.DBServices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Accounts")
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    private Integer account_ID;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    @Size(max = 32, min = 8)
    private String password;

    @ManyToMany
    private List<Role> roles;

    private boolean expired;

    private boolean locked;

    private String creation_Token;

    @Size(max = 32, min = 8)
    private String username;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Account> friends;

    public Account() {}

    public Account(List<Player> players, String password, String creation_Token, String username, List<Account> friends, List<Role> roles) {
        this.roles = roles;
        this.players = players;
        this.password = password;
        this.creation_Token = creation_Token;
        this.username = username;
        this.friends = friends;
        this.expired = false;
        this.locked = false;
    }

    public Account(Integer account_ID, List<Player> players, String password, String creation_Token, String username, List<Account> friends, List<Role> roles, boolean expired, boolean locked) {
        this.roles = roles;
        this.account_ID = account_ID;
        this.players = players;
        this.password = password;
        this.creation_Token = creation_Token;
        this.username = username;
        this.friends = friends;
        this.expired = expired;
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_ID=" + account_ID +
                ", players=" + players +
                ", password='" + password + '\'' +
                ", creation_Token='" + creation_Token + '\'' +
                ", username='" + username + '\'' +
                ", friends=" + friends +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getCreation_Token() {
        return creation_Token;
    }

    public void setCreation_Token(String token) {
        this.creation_Token = token;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
