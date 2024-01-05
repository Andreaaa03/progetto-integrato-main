package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TeamsRepository extends JpaRepository<Teams,Integer>
{
	@Override
	List<Teams> findAll();

	@Override
	Optional<Teams> findById(Integer integer);

	Optional<Teams> findByTeamName(String name);

}
