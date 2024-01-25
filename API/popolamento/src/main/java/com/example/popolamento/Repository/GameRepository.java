package com.example.popolamento.Repository;

import com.example.popolamento.Entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Games,Integer> {
	@Override
	Games save(Games games);

	Games findByGameId(Integer gameId);
}
