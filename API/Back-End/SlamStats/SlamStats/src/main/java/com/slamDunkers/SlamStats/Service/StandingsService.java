package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.TeamStandings;
import com.slamDunkers.SlamStats.Repository.StandingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandingsService {
	private StandingsRepository Repository;

	@Autowired
	public StandingsService(StandingsRepository Repository){this.Repository =Repository;}

	public List<TeamStandings> selezionaTuttiTeams(){
		return Repository.findByOrderByWinPercentageDesc();
	}
}
