package main.java.databank.game.table;


import jakarta.persistence.*;
import main.java.databank.game.game.Game;

import java.util.ArrayList;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "Tables")
public class Table {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer table_ID;

    @OneToMany
    private List<Game> games;

    private String name;

    private long longitude;

    private long latitude;

    public Table() {

    }

    public Table(String name, long longitude, long latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.games = new ArrayList<>();
    }


    public List<Game> getGames() {
        return games;
    }

    public Integer getTable_ID() {
        return table_ID;
    }

    public void setTable_ID(Integer table_ID) {
        this.table_ID = table_ID;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}
