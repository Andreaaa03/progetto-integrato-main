package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
/**
 * This method is used to find all Player entities.
 * @return List<Player> This returns a list of all Player entities.
 */
@Override
List<Player> findAll();

/**
 * This method is used to find a Player entity by its id.
 * @param id This is the id of the Player entity to be found.
 * @return Player This returns the Player entity with the given id.
 */
Player findById(int id);

/**
 * This method is used to find all Player entities associated with a specific team.
 * @param team This is the team whose associated Player entities are to be found.
 * @return List<Player> This returns a list of Player entities associated with the specified team.
 */
List<Player> findByTeam(Optional<Teams> team);


}
