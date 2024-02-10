package main.java.databank.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * The repository for the corresponding Entity, allowing saving and getting of data
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findBytimeOfGameBetween(Date from, Date to);
}
