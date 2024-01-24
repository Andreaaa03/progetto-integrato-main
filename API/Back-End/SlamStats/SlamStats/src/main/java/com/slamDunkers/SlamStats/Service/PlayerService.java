package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Payload.Response.PlayerResponse;
import com.slamDunkers.SlamStats.Payload.Response.ToResponse;
import com.slamDunkers.SlamStats.Repository.PlayerRepository;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamsRepository teamsRepository;
    private final ToResponse toResponse;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamsRepository teamsRepository, ToResponse toResponse) {
        this.playerRepository = playerRepository;
        this.teamsRepository = teamsRepository;
        this.toResponse = toResponse;
    }

    public List<Player> selezionaTuttiGiocatori() {
        return playerRepository.findAll();
    }

    /**
     * This method retrieves a player based on the provided player ID.
     *
     * @param playerId The ID of the player to be retrieved.
     * @return A PlayerResponse object containing the player's details.
     * If no player is found for the specified ID, the method returns null.
     */
    public PlayerResponse selezionaGiocatore(int playerId) {return toResponse.toPlayerResponse(playerRepository.findById(playerId));}

    /**
     * This method retrieves all players from a specific team.
     *
     * @param teamId The ID of the team whose players are to be retrieved.
     * @return A list of PlayerResponse objects, each representing a player from the specified team.
     * If no players are found for the specified team, the method returns an empty list.
     */
    public List<PlayerResponse> selezionaGiocatoriPerSquadra(int teamId) {
        Optional<Teams> teamFound = teamsRepository.findById(teamId);
        if (teamFound.isPresent()) {
            List<PlayerResponse> playerResponseList = new ArrayList<>();
            for (Player player : playerRepository.findByTeam(teamFound)) {
                playerResponseList.add(toResponse.toPlayerResponse(player));
            }
            return playerResponseList;
        } else {
            return Collections.emptyList();
        }
    }

}
