package main.java.databank.game.table;

import main.java.databank.game.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
    @Query("select g from Table t join Game g on t.table_ID = g.table.table_ID where t.table_ID = ?1 and g.gameFinished = true ")
    Game findCurrentGame(int table_ID);
}
