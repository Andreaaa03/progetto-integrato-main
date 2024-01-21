package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.PlayerStatistics;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerStatRepository extends JpaRepository<PlayerStatistics, Integer> {

	List<PlayerStatistics> findByPlayerId(int playerId);

//	@Query("SELECT * 			"FROM `player_statistics` ps " +
//			"INNER JOIN games g on g.id = ps.game_id " +
//			"WHERE ps.player_ID = :playerId " +
//			"ORDER by g.start_date DESC " +
//			"LIMIT 5;", nativeQuery = true)
	@Query(value = "SELECT * FROM player_statistics ps INNER JOIN games g on g.id = ps.game_id WHERE ps.player_ID = :playerId ORDER by g.start_date DESC LIMIT 5", nativeQuery = true)
	List<PlayerStatistics> findLast5Games(int playerId);


	List< PlayerStatistics> findByGameAndTeam(Optional<Games> game, Teams teamId);



}
