package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface GamesRepository extends JpaRepository<Games,Integer> {
/**
 * This method is used to find all Games entities.
 * @return List<Games> This returns a list of all Games entities.
 */
@Override
List<Games> findAll();

/**
 * This method is used to find all Games entities that contain the specified start date in their 'startDate' field and order them by 'startDate'.
 * @param startDate This is the start date to be searched for in the 'startDate' field of the Games entities.
 * @return List<Games> This returns a list of Games entities that contain the specified start date in their 'startDate' field, ordered by 'startDate'.
 */
List<Games> findByStartDateContainingOrderByStartDate(String startDate);

/**
 * This method is used to find all Games entities where either the home team or the away team is the specified team, and order them by 'startDate'.
 * @param teamId This is the team to be searched for in the 'homeTeam' and 'awayTeam' fields of the Games entities.
 * @param teamId2 This is the second team to be searched for in the 'homeTeam' and 'awayTeam' fields of the Games entities.
 * @return List<Games> This returns a list of Games entities where either the home team or the away team is one of the specified teams, ordered by 'startDate'.
 */
List<Games> findByHomeTeamOrAwayTeamOrderByStartDate(Teams teamId, Teams teamId2);

/**
 * This method is used to find a Games entity by its id.
 * @param gameId This is the id of the Games entity to be found.
 * @return Games This returns the Games entity with the given id.
 */
Games findById(int gameId);

/**
 * This method is used to find the last 20 Games entities that started before the specified date, and order them by 'startDate' in descending order.
 * @param date This is the date to be compared with the 'startDate' field of the Games entities.
 * @return List<Games> This returns a list of the last 20 Games entities that started before the specified date, ordered by 'startDate' in descending order.
 */
@Query(value = "SELECT * FROM `games` WHERE start_date < :date ORDER BY start_date DESC LIMIT 20;", nativeQuery = true)
List<Games> findLast20Games(String date);

}
