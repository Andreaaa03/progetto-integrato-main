package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.PlayerStatistics;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Payload.Response.*;
import com.slamDunkers.SlamStats.Repository.GamesRepository;
import com.slamDunkers.SlamStats.Repository.PlayerStatRepository;
import com.slamDunkers.SlamStats.Repository.ScoreRepository;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class GamesService {

	private final GamesRepository repository;
	private final TeamsRepository teamsRepository;
	private final ToResponse toResponse;


	public GamesService(GamesRepository repository, TeamsRepository teamsRepository, ToResponse toResponse) {
		this.repository = repository;
		this.teamsRepository = teamsRepository;

		this.toResponse = toResponse;
	}
	/**
	* This method retrieves a list of games that match the provided date.
	* The date is passed as a string and is used to query the GamesRepository.
	* The method findByStartDateContainingOrderByStartDate in the GamesRepository uses Spring Data JPA's method naming conventions to generate a query.
	* This query will find all Games entities where the startDate field contains the provided date string and order the results by startDate.
	* The list of Games entities returned by the repository is then passed to the toCalendarioDateResponse method.
	* The toCalendarioDateResponse method is responsible for converting the list of Games entities into a list of CalendarioDateResponse objects.
	* This method then returns this list of CalendarioDateResponse objects.
	*
	* @param date The date to search for in the format of a String.
	* @return A list of CalendarioDateResponse objects that match the provided date.
	*/
	public List<CalendarioDateResponse> getGameByDate(String date) {
	 List<Games> games = repository.findByStartDateContainingOrderByStartDate(date);
	 return toResponse.toCalendarioDateResponse(games);
	}

	/**
	 * This method retrieves all games from the repository.
	 * It uses the findAll method of the repository which returns a list of all Games entities.
	 * This list is then passed to the toCalendarioDateResponse method which converts the list of Games entities into a list of CalendarioDateResponse objects.
	 * The method then returns this list of CalendarioDateResponse objects.
	 *
	 * @return A list of CalendarioDateResponse objects representing all games.
	 */
	public List<CalendarioDateResponse> getGames(){
	 List<Games> games = repository.findAll();
	 return toResponse.toCalendarioDateResponse(games);
	}

	/**
	 * This method retrieves all games played by a specific team.
	 * It takes an integer parameter idSquadra which represents the id of the team.
	 * The method first retrieves the team entity from the TeamsRepository using the provided id.
	 * If the team is not found, it throws a NoSuchElementException.
	 * Then, it queries the GamesRepository to find all games where the provided team is either the home team or the away team.
	 * The games are ordered by their start date.
	 * The list of Games entities returned by the repository is then passed to the toCalendarioDateResponse method.
	 * The toCalendarioDateResponse method is responsible for converting the list of Games entities into a list of CalendarioDateResponse objects.
	 * This method then returns this list of CalendarioDateResponse objects.
	 *
	 * @param idSquadra The id of the team to search for.
	 * @return A list of CalendarioDateResponse objects that represent all games played by the team.
	 */
	public List<CalendarioDateResponse> partiteGiocateSquadra(int idSquadra) {
	 //prendere tutti i games della squadra
	 Optional<Teams> optionalTeam = teamsRepository.findById(idSquadra);
	 Teams team = optionalTeam.orElseThrow(() -> new NoSuchElementException("Team not found"));
	 List<Games> games = repository.findByHomeTeamOrAwayTeamOrderByStartDate(team, team);
	 return toResponse.toCalendarioDateResponse(games);
	}

	/**
	 * This method retrieves a game by its id.
	 * It takes an integer parameter gameId which represents the id of the game.
	 * The method first retrieves the game entity from the GamesRepository using the provided id.
	 * If the game is not found, it returns an empty list.
	 * If the game is found, it is added to a new list of Games entities.
	 * This list is then passed to the toCalendarioDateResponse method which converts the list of Games entities into a list of CalendarioDateResponse objects.
	 * The method then returns this list of CalendarioDateResponse objects wrapped in an Optional.
	 *
	 * @param gameId The id of the game to search for.
	 * @return An Optional containing a list of CalendarioDateResponse objects that represent the game with the provided id.
	 */
	public Optional<List<CalendarioDateResponse>> findById(Integer gameId) {
	 Optional<Games> games = repository.findById(gameId);
	 if (games.isPresent()) {
	  List<Games> gameList = new ArrayList<>();
	  gameList.add(games.get());
	  return Optional.of(toResponse.toCalendarioDateResponse(gameList));
	 }
	 return Optional.of(new ArrayList<>());
	}


	/**
	 * This method retrieves the statistics of a game by its id.
	 * It takes an integer parameter gameId which represents the id of the game.
	 * The method first retrieves the game entity from the GamesRepository using the provided id.
	 * If the game is not found, it returns null.
	 * If the game is found, it calls the toPartitaStatResponse method of the ToResponse object, passing the game entity as a parameter.
	 * The toPartitaStatResponse method is responsible for converting the game entity into a PartitaStatResponse object.
	 * This method then returns this PartitaStatResponse object, or null if the game was not found.
	 *
	 * @param gameId The id of the game to search for.
	 * @return A PartitaStatResponse object that represents the statistics of the game with the provided id, or null if the game is not found.
	 */
	public PartitaStatResponse partitaStatResponse(Integer gameId){
		Optional<Games> game = repository.findById(gameId);

		if (game.isPresent()){
			return toResponse.toPartitaStatResponse(game.get());
		}
		return null;
	}


	/**
	 * This method retrieves the last 20 games from a specific date.
	 * It takes a LocalDate parameter date which represents the date from which to retrieve the games.
	 * The method first converts the date to a string and appends " 23:59:59" to it to represent the end of the day.
	 * Then, it calls the findLast20Games method of the GamesRepository, passing the string representation of the date and time as a parameter.
	 * The findLast20Games method is responsible for retrieving the last 20 games that started before the provided date and time.
	 * The list of Games entities returned by the repository is then passed to the toCalendarioDateResponse method of the ToResponse object.
	 * The toCalendarioDateResponse method is responsible for converting the list of Games entities into a list of CalendarioDateResponse objects.
	 * This method then returns this list of CalendarioDateResponse objects.
	 *
	 * @param date The date from which to retrieve the last 20 games.
	 * @return A list of CalendarioDateResponse objects that represent the last 20 games from the provided date.
	 */
	public List<CalendarioDateResponse> getLast20FromDate(LocalDate date) {
	 String dataOra = date.toString() + " 23:59:59";
	 List<Games> games = repository.findLast20Games(dataOra);
	 return toResponse.toCalendarioDateResponse(games);
	}
}
