package com.example.popolamento.Repository;

import com.example.popolamento.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

	Optional<Player> findById(int playerId);
}
