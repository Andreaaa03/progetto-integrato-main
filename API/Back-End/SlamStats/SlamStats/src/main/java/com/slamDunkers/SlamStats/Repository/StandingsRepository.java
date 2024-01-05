package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.TeamStandings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandingsRepository extends JpaRepository<TeamStandings,Integer>{

	List<TeamStandings> findByOrderByWinPercentageDesc();
}
