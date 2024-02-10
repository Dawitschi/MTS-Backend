package main.java.databank.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository for the corresponding Entity, allowing saving and getting of data
 */
@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Integer> {}