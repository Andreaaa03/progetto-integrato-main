package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GamesRepository extends JpaRepository<Games,Integer> {
	@Override
	List<Games> findAll();

	List<Games> findByStartDateContainingOrderByStartDate(String startDate);

	List<Games> findByHomeTeamOrAwayTeamOrderByStartDate(Teams teamId, Teams teamId2);

	Games findById(int gameId);


//	List<Games> findFirst20ByOrderByStartDateDesc();

//	find by date containing  bitween 2 dates
	List<Games> findByStartDateBetween(String startDate, String endDate);

}
