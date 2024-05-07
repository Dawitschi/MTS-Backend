package main.java.databank.game.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findAllByDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
}
