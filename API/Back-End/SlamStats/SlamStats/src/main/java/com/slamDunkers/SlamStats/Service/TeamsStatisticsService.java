package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.TeamsStatistics;
import com.slamDunkers.SlamStats.Repository.TeamsStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsStatisticsService {
	private TeamsStatisticsRepository Repository;

	@Autowired
	public TeamsStatisticsService(TeamsStatisticsRepository Repository){this.Repository =Repository;}

	public TeamsStatistics selezionaTeamsStatisticsById(int id) {
		return Repository.findByTeamId(id);
	}
}
