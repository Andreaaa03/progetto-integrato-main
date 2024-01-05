package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.Scores;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Payload.Response.CalendarioDateResponse;
import com.slamDunkers.SlamStats.Payload.Response.CalendarioResponse;
import com.slamDunkers.SlamStats.Repository.GamesRepository;
import com.slamDunkers.SlamStats.Repository.ScoreRepository;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GamesService {

	private final GamesRepository repository;
	private final ScoreRepository scoreRepository;
	private final TeamsRepository teamsRepository;

	public GamesService(GamesRepository repository,TeamsRepository teamsRepository, ScoreRepository scoreRepository) {
		this.repository = repository;
		this.teamsRepository = teamsRepository;
		this.scoreRepository = scoreRepository;
	}
	public List<CalendarioDateResponse> getGameByDate(String date) {

		List<CalendarioDateResponse> calendarioResponseList = new ArrayList<>();

		List<Games> games = repository.findByStartDateContaining(date);

		for(Games game : games ){
			CalendarioDateResponse calendario = new CalendarioDateResponse() ;
			calendario.setGameid(game.getId());
			calendario.setGameStartDate(game.getStartDate());
			calendario.setTeamHomeName(game.getHomeTeam().getTeamName());
			calendario.setTeamAwayName(game.getAwayTeam().getTeamName());
			calendario.setScoreTeamHome(scoreRepository.findByGameAndTeam(game, game.getHomeTeam()).getPoints());
			calendario.setScoreTeamAway(scoreRepository.findByGameAndTeam(game, game.getAwayTeam()).getPoints());
			calendario.setConferenceNameHome(game.getHomeTeam().getLeague().getConference());
			calendario.setConferenceNameAway(game.getAwayTeam().getLeague().getConference());
			calendario.setDivisionNameHome(game.getHomeTeam().getLeague().getDivision());
			calendario.setDivisionNameAway(game.getAwayTeam().getLeague().getDivision());
			calendario.setTeamIdHome(game.getHomeTeam().getId());
			calendario.setTeamIdAway(game.getAwayTeam().getId());
			calendario.setAllStarHome(game.getHomeTeam().isAllStar());
			calendario.setAllStarAway(game.getAwayTeam().isAllStar());
			calendario.setNicknameHome(game.getHomeTeam().getNickname());
			calendario.setNicknameAway(game.getAwayTeam().getNickname());
			calendario.setLogoHome(game.getHomeTeam().getLogo());
			calendario.setLogoAway(game.getAwayTeam().getLogo());
			calendario.setCityHome(game.getHomeTeam().getCity());
			calendario.setCityAway(game.getAwayTeam().getCity());
			calendario.setCodeHome(game.getHomeTeam().getCode());
			calendario.setCodeAway(game.getAwayTeam().getCode());


			calendarioResponseList.add(calendario);
		}
return calendarioResponseList;
	}

	public List<Games> getGameByTeam(int teamId) {
		Teams team = new Teams();
		team.setId(teamId);
		return repository.findByHomeTeamOrAwayTeam(team, team);
	}

	public List<CalendarioDateResponse> partiteGiocateSquadra(int idSquadra) {
		//prendere tutti i games della squadra
		Optional<Teams> optionalTeam = teamsRepository.findById(idSquadra);
		Teams team = optionalTeam.orElseThrow(() -> new NoSuchElementException("Team not found"));
		List<Games> games = repository.findByHomeTeamOrAwayTeam(team, team);

		List<CalendarioDateResponse> calendarioResponseList = new ArrayList<>();


		for (Games game : games)
		{
			CalendarioDateResponse calendario = new CalendarioDateResponse() ;
			calendario.setGameid(game.getId());
			calendario.setGameStartDate(game.getStartDate());
			calendario.setTeamHomeName(game.getHomeTeam().getTeamName());
			calendario.setTeamAwayName(game.getAwayTeam().getTeamName());
			calendario.setScoreTeamHome(scoreRepository.findByGameAndTeam(game, game.getHomeTeam()).getPoints());
			calendario.setScoreTeamAway(scoreRepository.findByGameAndTeam(game, game.getAwayTeam()).getPoints());
			calendario.setConferenceNameHome(game.getHomeTeam().getLeague().getConference());
			calendario.setConferenceNameAway(game.getAwayTeam().getLeague().getConference());
			calendario.setDivisionNameHome(game.getHomeTeam().getLeague().getDivision());
			calendario.setDivisionNameAway(game.getAwayTeam().getLeague().getDivision());
			calendario.setTeamIdHome(game.getHomeTeam().getId());
			calendario.setTeamIdAway(game.getAwayTeam().getId());
			calendario.setAllStarHome(game.getHomeTeam().isAllStar());
			calendario.setAllStarAway(game.getAwayTeam().isAllStar());
			calendario.setNicknameHome(game.getHomeTeam().getNickname());
			calendario.setNicknameAway(game.getAwayTeam().getNickname());
			calendario.setLogoHome(game.getHomeTeam().getLogo());
			calendario.setLogoAway(game.getAwayTeam().getLogo());
			calendario.setCityHome(game.getHomeTeam().getCity());
			calendario.setCityAway(game.getAwayTeam().getCity());
			calendario.setCodeHome(game.getHomeTeam().getCode());
			calendario.setCodeAway(game.getAwayTeam().getCode());

			calendarioResponseList.add(calendario);
		}
return calendarioResponseList;

//		//prendere id partita, data inizio, nomi delle squadre avversarie, punteggi squadra selezionata e squadra avversaria per ogni game
//		List<Integer> idPartita = new ArrayList<>();
//		List<String> dataInizio = new ArrayList<>();
//		List<String> awayTeam = new ArrayList<>();
//		List<Integer> scoreTeamSelect = new ArrayList<>();
//		List<Integer> scoreAwayTeam = new ArrayList<>();
//		for (Games game : games) {
//			idPartita.add(game.getId());
//			dataInizio.add(game.getStartDate());
//			scoreTeamSelect.add(scoreRepository.findByGameAndTeam(game, team).getPoints());
//
//			if(game.getHomeTeam().equals(team)){
//
//				awayTeam.add(game.getAwayTeam().getTeamName());
//				System.out.println("away team is not null: "+ game.getAwayTeam().getTeamName());
//				Teams awayTeam1 =  game.getAwayTeam();
//				Scores score = scoreRepository.findByGameAndTeam(game, awayTeam1);
//				scoreAwayTeam.add(score.getPoints());
//			}else {
//				if (game.getHomeTeam() == null) {
//					System.out.println("home team is null");
//					continue;
//				}
//				else {
//					System.out.println("home team is not null"+ game.getHomeTeam().toString());
//				}
//
//				awayTeam.add(game.getHomeTeam().getTeamName());
//				System.out.println("away team is not null: "+ game.getHomeTeam().getTeamName());
//				scoreAwayTeam.add(scoreRepository.findByGameAndTeam(game, game.getHomeTeam()).getPoints());
//			}
//		}
//		return new CalendarioResponse(idPartita, dataInizio, team.getTeamName(), awayTeam, scoreTeamSelect, scoreAwayTeam, team.getLeague().getConference(), team.getLeague().getDivision(), team.getTeamName(), team.getId(), team.isAllStar(), team.getNickname(), team.getLogo(), team.getCity(), team.getCode());

	}

}
