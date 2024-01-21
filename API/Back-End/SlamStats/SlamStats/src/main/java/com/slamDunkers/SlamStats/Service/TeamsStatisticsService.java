package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Payload.Response.TeamStatisticsResponse;
import com.slamDunkers.SlamStats.Payload.Response.ToResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsStatisticsService {

	private final ToResponse toResponse;
	@Autowired
	public TeamsStatisticsService( ToResponse toResponse){
		this.toResponse = toResponse;
	}

	public TeamStatisticsResponse selezionaTeamsStatisticsById(Integer id) {
		return toResponse.toTeamsStatisticsResponse(id);
	}
}
