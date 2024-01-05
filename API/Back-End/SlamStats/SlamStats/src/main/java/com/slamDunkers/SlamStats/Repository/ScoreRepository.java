package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.Scores;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.geo.Polygon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Scores,Integer> {
	Scores findByTeam(Teams team);


	Scores findByGameAndTeam(Games game, Teams team);

	Scores findByGame(Games games);
}
