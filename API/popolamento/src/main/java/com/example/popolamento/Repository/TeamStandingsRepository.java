package com.example.popolamento.Repository;

import com.example.popolamento.Entity.TeamStandings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamStandingsRepository extends JpaRepository<TeamStandings,Integer> {
	@Override
	TeamStandings save(TeamStandings teamStandings);
}
