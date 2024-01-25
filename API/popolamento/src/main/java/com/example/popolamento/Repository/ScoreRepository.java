package com.example.popolamento.Repository;


import com.example.popolamento.Entity.Scores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ScoreRepository extends JpaRepository<Scores, Integer>{

// native query per l'inserimento di score( games_ID teams_ID 	win 	loss 	series_win 	series_loss 	points)
	@Modifying
	@Query(value = "INSERT INTO scores (games_ID, teams_ID, win, loss, series_win, series_loss, points) VALUES (:games_ID, :teams_ID, :win, :loss, :series_win, :series_loss, :points)", nativeQuery = true)
	void insertScore(@Param("games_ID") int games_ID, @Param("teams_ID") int teams_ID, @Param("win") int win, @Param("loss") int loss, @Param("series_win") int series_win, @Param("series_loss") int series_loss, @Param("points") int points);

}
