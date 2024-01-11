package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.PlayerStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerStatRepository extends JpaRepository<PlayerStatistics, Integer> {
	List<PlayerStatistics> findByPlayerId(int playerId);



}
