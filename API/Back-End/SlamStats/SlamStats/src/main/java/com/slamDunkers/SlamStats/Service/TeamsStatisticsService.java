package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.TeamsStatistics;
import com.slamDunkers.SlamStats.Payload.Response.TeamStandingsResponse;
import com.slamDunkers.SlamStats.Payload.Response.TeamStatisticsResponse;
import com.slamDunkers.SlamStats.Repository.TeamsStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsStatisticsService {
	private TeamsStatisticsRepository Repository;

	@Autowired
	public TeamsStatisticsService(TeamsStatisticsRepository Repository){this.Repository =Repository;}

	public TeamStatisticsResponse selezionaTeamsStatisticsById(Integer id) {
		TeamsStatistics teamsStatistics = Repository.findByTeamId(id);
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
			return response;




		}
		return null;
	}
}
