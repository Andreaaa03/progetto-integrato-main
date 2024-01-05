package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.TeamsStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsStatisticsRepository extends JpaRepository<TeamsStatistics,Integer> {
	TeamsStatistics findByTeamId(int teamId);



}
