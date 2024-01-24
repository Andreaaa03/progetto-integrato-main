package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.TeamStandings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandingsRepository extends JpaRepository<TeamStandings,Integer>{

/**
 * This method is used to find all TeamStandings entities ordered by win percentage in descending order.
 * @return List<TeamStandings> This returns a list of all TeamStandings entities ordered by win percentage in descending order.
 */
List<TeamStandings> findByOrderByWinPercentageDesc();

/**
 * This method is used to find a TeamStandings entity by its team id.
 * @param id This is the id of the team whose associated TeamStandings entity is to be found.
 * @return TeamStandings This returns the TeamStandings entity associated with the specified team id.
 */
TeamStandings findByTeamId(int id);
}
