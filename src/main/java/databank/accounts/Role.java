package main.java.databank.accounts;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class Role {
    @ManyToMany
    private List<Account> accounts;

    @Id
    private String role;


    public Role(List<Account> accounts, String role) {
        this.accounts = accounts;
        this.role = role;
    }

    public Role() {}

    public Role(String name) {
        this.role = name;
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
