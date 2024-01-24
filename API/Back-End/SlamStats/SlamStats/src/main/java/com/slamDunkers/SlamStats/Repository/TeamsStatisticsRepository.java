package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.TeamsStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsStatisticsRepository extends JpaRepository<TeamsStatistics,Integer> {
/**
 * This method is used to find a TeamsStatistics entity by its team id.
 * @param teamId This is the id of the team whose associated TeamsStatistics entity is to be found.
 * @return TeamsStatistics This returns the TeamsStatistics entity associated with the specified team id.
 */
TeamsStatistics findByTeamId(int teamId);



}
