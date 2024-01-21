package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {
	private final TeamsRepository repository;

	@Autowired
	public TeamsService(TeamsRepository repository){this.repository=repository;}

	public List<Teams> selezionaTuttiTeams(){
		return repository.findAll();
	}

	public Optional<Teams> selezionaTeamById(int id) {
		return repository.findById(id);
	}
}
