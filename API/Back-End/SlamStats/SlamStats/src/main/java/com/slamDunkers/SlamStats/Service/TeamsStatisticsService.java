package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.TeamStandings;
import com.slamDunkers.SlamStats.Entity.TeamsStatistics;
import com.slamDunkers.SlamStats.Payload.Response.TeamStandingsResponse;
import com.slamDunkers.SlamStats.Payload.Response.TeamStatisticsResponse;
import com.slamDunkers.SlamStats.Repository.StandingsRepository;
import com.slamDunkers.SlamStats.Repository.TeamsStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsStatisticsService {
	private final TeamsStatisticsRepository Repository;
	private final StandingsRepository standings;

	@Autowired
	public TeamsStatisticsService(TeamsStatisticsRepository Repository, StandingsRepository standings){this.Repository =Repository;
		this.standings = standings;
	}

	public TeamStatisticsResponse selezionaTeamsStatisticsById(Integer id) {
		TeamsStatistics teamsStatistics = Repository.findByTeamId(id);
		TeamStandings teamStandings = standings.findByTeamId(id);
		if (teamsStatistics != null) {

			TeamStatisticsResponse response = new TeamStatisticsResponse();
			response.setTeam(teamsStatistics.getTeam().toTeamsResponse());
			response.setSeason(teamsStatistics.getSeason().getYear());
			response.setGames(teamsStatistics.getGames());
			response.setFastBreakPoints(teamsStatistics.getFastBreakPoints());
			response.setPointsInPaint(teamsStatistics.getPointsInPaint());
			response.setBiggestLead(teamsStatistics.getBiggestLead());
			response.setSecondChancePoints(teamsStatistics.getSecondChancePoints());
			response.setPointsOffTurnover(teamsStatistics.getPointsOffTurnover());
			response.setPoints(teamsStatistics.getPoints());
			response.setFgm(teamsStatistics.getFgm());
			response.setFga(teamsStatistics.getFga());
			response.setFgp(teamsStatistics.getFgp());
			response.setFtm(teamsStatistics.getFtm());
			response.setFta(teamsStatistics.getFta());
			response.setFtp(teamsStatistics.getFtp());
			response.setTpm(teamsStatistics.getTpm());
			response.setTpa(teamsStatistics.getTpa());
			response.setTpp(teamsStatistics.getTpp());
			response.setOffReb(teamsStatistics.getOffReb());
			response.setDefReb(teamsStatistics.getDefReb());
			response.setTotReb(teamsStatistics.getTotReb());
			response.setAssists(teamsStatistics.getAssists());
			response.setSteals(teamsStatistics.getSteals());
			response.setTurnovers(teamsStatistics.getTurnovers());
			response.setBlocks(teamsStatistics.getBlocks());
			response.setPlusMinus(teamsStatistics.getPlusMinus());
			response.setPfouls(teamsStatistics.getPFouls());
			response.setConferenceRank(teamStandings.getConferenceRank());
			return response;
		}
		if (teamStandings != null) {
			TeamStatisticsResponse response = new TeamStatisticsResponse();
			response.setTeam(teamStandings.getTeam().toTeamsResponse());
			response.setSeason(teamStandings.getSeason().getYear());
			response.setGames(0);
			response.setFastBreakPoints(0);
			response.setPointsInPaint(0);
			response.setBiggestLead(0);
			response.setSecondChancePoints(0);
			response.setPointsOffTurnover(0);
			response.setPoints(0);
			response.setFgm(0);
			response.setFga(0);
			response.setFgp(0.0);
			response.setFtm(0);
			response.setFta(0);
			response.setFtp(0.0);
			response.setTpm(0);
			response.setTpa(0);
			response.setTpp(0.0);
			response.setOffReb(0);
			response.setDefReb(0);
			response.setTotReb(0);
			response.setAssists(0);
			response.setSteals(0);
			response.setTurnovers(0);
			response.setBlocks(0);
			response.setPlusMinus(0);
			response.setPfouls(0);
			response.setConferenceRank(teamStandings.getConferenceRank());
			return response;
		}
		return null;
	}
}
