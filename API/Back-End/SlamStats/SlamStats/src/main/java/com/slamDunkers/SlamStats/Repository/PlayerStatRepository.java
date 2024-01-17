package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.PlayerStatistics;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerStatRepository extends JpaRepository<PlayerStatistics, Integer> {

	List<PlayerStatistics> findByPlayerId(int playerId);
	List< PlayerStatistics> findByGameAndTeam(Optional<Games> game, Teams teamId);



}
