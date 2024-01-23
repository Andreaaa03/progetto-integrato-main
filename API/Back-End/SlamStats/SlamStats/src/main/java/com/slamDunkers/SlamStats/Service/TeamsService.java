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
    public TeamsService(TeamsRepository repository) {
        this.repository = repository;
    }

    /**
     * This method retrieves all teams from the repository.
     *
     * @return A list of Teams objects. Each Teams object represents a team and includes details such as the team's ID, name, and statistics.
     * The method calls the findAll() method of the repository, which retrieves all Teams objects from the database.
     * If no teams are found, the method returns an empty list.
     */
    public List<Teams> selezionaTuttiTeams() {
        return repository.findAll();
    }

    /**
     * This method retrieves a team from the repository by its ID.
     *
     * @param id The ID of the team to be retrieved.
     * @return An Optional<Teams> object. If a team with the specified ID is found, the Optional contains the Teams object.
     * If no team is found, the Optional is empty.
     * The method calls the findById() method of the repository, passing the ID as a parameter.
     */
    public Optional<Teams> selezionaTeamById(int id) {
        return repository.findById(id);
    }
}
