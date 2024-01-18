package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface GamesRepository extends JpaRepository<Games,Integer> {
	@Override
	List<Games> findAll();

	List<Games> findByStartDateContainingOrderByStartDate(String startDate);

	List<Games> findByHomeTeamOrAwayTeamOrderByStartDate(Teams teamId, Teams teamId2);

	Games findById(int gameId);


//  query to get last 20 games from date
	@Query(value = "SELECT * FROM `games` WHERE start_date < :date ORDER BY start_date DESC LIMIT 20;", nativeQuery = true)
	List<Games> findLast20Games(String date);

}
