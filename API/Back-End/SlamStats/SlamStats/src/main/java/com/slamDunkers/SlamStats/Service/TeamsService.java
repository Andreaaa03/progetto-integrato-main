package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {
	private TeamsRepository Repository;

	@Autowired
	public TeamsService(TeamsRepository Repository){this.Repository =Repository;}

	public List<Teams> selezzionaTuttiTeams(){
		return Repository.findAll();

	}

	public Optional<Teams> selezionaTeamById(int id) {
		return Repository.findById(id);
	}
}
