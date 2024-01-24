package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TeamsRepository extends JpaRepository<Teams,Integer>
{
/**
 * This method is used to find all Teams entities.
 * @return List<Teams> This returns a list of all Teams entities.
 */
@Override
List<Teams> findAll();

/**
 * This method is used to find a Teams entity by its id.
 * @param integer This is the id of the Teams entity to be found.
 * @return Optional<Teams> This returns the Teams entity with the given id, if it exists.
 */
@Override
Optional<Teams> findById(Integer integer);

/**
 * This method is used to find a Teams entity by its team name.
 * @param name This is the name of the Teams entity to be found.
 * @return Optional<Teams> This returns the Teams entity with the given name, if it exists.
 */
Optional<Teams> findByTeamName(String name);

}
