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
/**
 * This method is used to find a Scores entity associated with a specific team.
 * @param team This is the team whose associated Scores entity is to be found.
 * @return Scores This returns the Scores entity associated with the specified team.
 */
Scores findByTeam(Teams team);

/**
 * This method is used to find a Scores entity associated with a specific game and team.
 * @param game This is the game whose associated Scores entity is to be found.
 * @param team This is the team whose associated Scores entity is to be found.
 * @return Scores This returns the Scores entity associated with the specified game and team.
 */
Scores findByGameAndTeam(Games game, Teams team);

/**
 * This method is used to find a Scores entity associated with a specific game.
 * @param games This is the game whose associated Scores entity is to be found.
 * @return Scores This returns the Scores entity associated with the specified game.
 */
Scores findByGame(Games games);
}
