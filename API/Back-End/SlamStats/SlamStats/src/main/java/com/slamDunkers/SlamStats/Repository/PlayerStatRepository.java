package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.PlayerStatistics;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerStatRepository extends JpaRepository<PlayerStatistics, Integer> {

/**
 * This method is used to find all PlayerStatistics entities associated with a specific player.
 * @param playerId This is the id of the player whose associated PlayerStatistics entities are to be found.
 * @return List<PlayerStatistics> This returns a list of PlayerStatistics entities associated with the specified player.
 */
List<PlayerStatistics> findByPlayerId(int playerId);

/**
 * This method is used to find the last 5 games for a specific player, ordered by the start date of the game in descending order.
 * @param playerId This is the id of the player whose last 5 games are to be found.
 * @return List<PlayerStatistics> This returns a list of the last 5 PlayerStatistics entities associated with the specified player, ordered by the start date of the game in descending order.
 */
@Query(value = "SELECT * FROM player_statistics ps INNER JOIN games g on g.id = ps.game_id WHERE ps.player_ID = :playerId ORDER by g.start_date DESC LIMIT 5", nativeQuery = true)
List<PlayerStatistics> findLast5Games(int playerId);

/**
 * This method is used to find all PlayerStatistics entities associated with a specific game and team.
 * @param game This is the game whose associated PlayerStatistics entities are to be found.
 * @param teamId This is the team whose associated PlayerStatistics entities are to be found.
 * @return List<PlayerStatistics> This returns a list of PlayerStatistics entities associated with the specified game and team.
 */
List<PlayerStatistics> findByGameAndTeam(Games game, Teams teamId);


}
