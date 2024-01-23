package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Payload.Response.TeamStatisticsResponse;
import com.slamDunkers.SlamStats.Payload.Response.ToResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsStatisticsService {

    private final ToResponse toResponse;

    @Autowired
    public TeamsStatisticsService(ToResponse toResponse) {
        this.toResponse = toResponse;
    }

    /**
     * This method retrieves the statistics of a team by its ID.
     *
     * @param id The ID of the team whose statistics are to be retrieved.
     * @return A TeamStatisticsResponse object containing the team's statistics.
     * The method calls the toTeamsStatisticsResponse() method of the ToResponse object, passing the ID as a parameter.
     * The toTeamsStatisticsResponse() method retrieves the team's statistics from the database and converts them into a TeamStatisticsResponse format.
     */
    public TeamStatisticsResponse selezionaTeamsStatisticsById(Integer id) {
        return toResponse.toTeamsStatisticsResponse(id);
    }
}
