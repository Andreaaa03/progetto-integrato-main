package com.example.popolamento.Repository;

import com.example.popolamento.Entity.PlayerStatistics;
import com.example.popolamento.Entity.PlayerStatisticsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatRepository extends JpaRepository<PlayerStatistics, PlayerStatisticsId> {

	@Override
	PlayerStatistics save(PlayerStatistics playerStatistics);

}
