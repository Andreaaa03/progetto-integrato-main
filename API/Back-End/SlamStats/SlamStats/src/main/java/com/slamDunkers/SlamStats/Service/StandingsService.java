package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.TeamStandings;
import com.slamDunkers.SlamStats.Payload.Response.TeamStandingsResponse;
import com.slamDunkers.SlamStats.Repository.StandingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandingsService {
	private final StandingsRepository repository;

	@Autowired
	public StandingsService(StandingsRepository repository){this.repository =repository;}

	public List<TeamStandingsResponse> selezionaTuttiTeams(){
		List<TeamStandings> teams = repository.findByOrderByWinPercentageDesc();
		List<TeamStandingsResponse> respons = new ArrayList<>();
		for (TeamStandings team : teams) {
			TeamStandingsResponse response = new TeamStandingsResponse();
			response.setTeam(team.getTeam().toTeamsResponse());
			response.setSeason(team.getSeason().getYear());
			response.setConferenceRank(team.getConferenceRank());
			response.setConferenceWin(team.getConferenceWin());
			response.setConferenceLoss(team.getConferenceLoss());
			response.setDivisionGamesBehind(team.getDivisionGamesBehind());
			response.setHomeWin(team.getHomeWin());
			response.setAwayWin(team.getAwayWin());
			response.setTotalWin(team.getTotalWin());
			response.setWinPercentage(team.getWinPercentage());
			response.setLastTenWin(team.getLastTenWin());
			response.setHomeLoss(team.getHomeLoss());
			response.setAwayLoss(team.getAwayLoss());
			response.setTotalLoss(team.getTotalLoss());
			response.setLastTenLoss(team.getLastTenLoss());
			response.setGamesBehind(team.getGamesBehind());

			respons.add(response);


		}

		return respons;
	}
}
