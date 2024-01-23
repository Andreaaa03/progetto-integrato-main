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
    public StandingsService(StandingsRepository repository) {
        this.repository = repository;
    }

    /**
     * This method retrieves all teams' standings.
     *
     * @return A list of TeamStandingsResponse objects, each representing a team's standings.
     * The TeamStandingsResponse object contains details such as the team's win percentage, conference rank,
     * conference win, conference loss, division games behind, home win, away win, total win, win percentage,
     * last ten win, home loss, away loss, total loss, last ten loss, and games behind.
     * The teams are ordered by their win percentage in descending order.
     * If no teams are found, the method returns an empty list.
     */
    public List<TeamStandingsResponse> selezionaTuttiTeams() {
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
