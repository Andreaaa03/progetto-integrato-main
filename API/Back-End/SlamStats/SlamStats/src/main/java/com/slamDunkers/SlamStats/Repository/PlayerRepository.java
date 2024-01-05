package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
	@Override
	List<Player> findAll();

	Player findById(int Id);

	List<Player> findByTeam(Optional<Teams> team);


}
