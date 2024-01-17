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
	private final ScoreRepository scoreRepository;
	private final TeamsRepository teamsRepository;

	private final PlayerStatRepository playerStatRepository;

	public GamesService(GamesRepository repository, TeamsRepository teamsRepository, ScoreRepository scoreRepository, PlayerStatRepository playerStatRepository) {
		this.repository = repository;
		this.teamsRepository = teamsRepository;
		this.scoreRepository = scoreRepository;
		this.playerStatRepository = playerStatRepository;
	}
	public List<CalendarioDateResponse> getGameByDate(String date) {
		List<Games> games = repository.findByStartDateContainingOrderByStartDate(date);
		return toCalendarioDateResponse(games);

	}
	public List<CalendarioDateResponse> getGames(){
		List<Games> games = repository.findAll();
		return toCalendarioDateResponse(games);

	}
	public List<Games> getGameByTeam(int teamId) {
		Teams team = new Teams();
		team.setId(teamId);
		return repository.findByHomeTeamOrAwayTeamOrderByStartDate(team, team);
	}

	public List<CalendarioDateResponse> partiteGiocateSquadra(int idSquadra) {
		//prendere tutti i games della squadra
		Optional<Teams> optionalTeam = teamsRepository.findById(idSquadra);
		Teams team = optionalTeam.orElseThrow(() -> new NoSuchElementException("Team not found"));
		List<Games> games = repository.findByHomeTeamOrAwayTeamOrderByStartDate(team, team);
		return toCalendarioDateResponse(games);
	}

	public Optional<List<CalendarioDateResponse>> findById(Integer gameId) {
		Optional<Games> games = repository.findById(gameId);
//		convert game to list
		if (games.isPresent()) {
			List<Games> gameList = new ArrayList<>();
			gameList.add(games.get());
			return Optional.of(toCalendarioDateResponse(gameList));
		}
		return null;
	}
	public PartitaStatResponse partitaStatResponse(Integer gameid){
		Optional<Games> game = repository.findById(gameid);
		PartitaStatResponse partitaStatResponse = new PartitaStatResponse();

		if (game.isPresent()){
			partitaStatResponse.setCalendarioDateResponse(toCalendarioDateResponse(Collections.singletonList(game.get())).get(0));




			List<PlayerStatistics> playerstat = playerStatRepository.findByGameAndTeam(game, game.get().getHomeTeam());
			QaurtiScoreResponse qaurtiScoreResponse = new QaurtiScoreResponse();
			qaurtiScoreResponse.setQ1Score(game.get().getQ1h());
			qaurtiScoreResponse.setQ2Score(game.get().getQ2h());
			qaurtiScoreResponse.setQ3Score(game.get().getQ3h());
			qaurtiScoreResponse.setQ4Score(game.get().getQ4h());
			partitaStatResponse.setHomeTeam(toPartitaStatResponse(playerstat,qaurtiScoreResponse));


			playerstat = playerStatRepository.findByGameAndTeam(game, game.get().getAwayTeam());
			QaurtiScoreResponse qaurtiScoreResponse1 = new QaurtiScoreResponse();
			qaurtiScoreResponse1.setQ1Score(game.get().getQ1a());
			qaurtiScoreResponse1.setQ2Score(game.get().getQ2a());
			qaurtiScoreResponse1.setQ3Score(game.get().getQ3a());
			qaurtiScoreResponse1.setQ4Score(game.get().getQ4a());
			partitaStatResponse.setAwayTeam(toPartitaStatResponse(playerstat,qaurtiScoreResponse1));


			System.out.println(partitaStatResponse.awayTeam.qaurtiScoreResponse.q1Score);
			System.out.println(partitaStatResponse.awayTeam.qaurtiScoreResponse.q2Score);
			System.out.println(partitaStatResponse.awayTeam.qaurtiScoreResponse.q3Score);
			System.out.println(partitaStatResponse.awayTeam.qaurtiScoreResponse.q4Score);

			System.out.println(partitaStatResponse.homeTeam.qaurtiScoreResponse.q1Score);
			System.out.println(partitaStatResponse.homeTeam.qaurtiScoreResponse.q2Score);
			System.out.println(partitaStatResponse.homeTeam.qaurtiScoreResponse.q3Score);
			System.out.println(partitaStatResponse.homeTeam.qaurtiScoreResponse.q4Score);

			return partitaStatResponse;
		}
		return null;
	}

	public TeamStatGameResponse toPartitaStatResponse(List<PlayerStatistics> playerstat, QaurtiScoreResponse qaurtiScoreResponse){
		TeamStatGameResponse response = new TeamStatGameResponse();
		List<Integer> fgm = new ArrayList<>();
		List<Integer> fga = new ArrayList<>();
		List<Double> fgp = new ArrayList<>();
		List<Integer> ftm = new ArrayList<>();
		List<Integer> fta = new ArrayList<>();
		List<Double> ftp = new ArrayList<>();
		List<Integer> tpm = new ArrayList<>();
		List<Integer> tpa = new ArrayList<>();
		List<Double> tpp = new ArrayList<>();
		List<Integer> offReb = new ArrayList<>();
		List<Integer> defReb = new ArrayList<>();
		List<Integer> totReb = new ArrayList<>();
		List<Integer> assists = new ArrayList<>();
		List<Integer> pFouls = new ArrayList<>();
		List<Integer> steals = new ArrayList<>();
		List<Integer> turnovers = new ArrayList<>();
		List<Integer> blocks = new ArrayList<>();
		List<Integer> plusMinus = new ArrayList<>();

		List<PlayerStatisticsResponse> playersStatistics = new ArrayList<>();


		for (PlayerStatistics p : playerstat){
			fgm.add(p.getFgm());
			fga.add(p.getFga());
			fgp.add(p.getFgp());
			ftm.add(p.getFtm());
			fta.add(p.getFta());
			ftp.add(p.getFtp());
			tpm.add(p.getTpm());
			tpa.add(p.getTpa());
			tpp.add(p.getTpp());
			offReb.add(p.getOffReb());
			defReb.add(p.getDefReb());
			totReb.add(p.getTotReb());
			assists.add(p.getAssists());
			pFouls.add(p.getPFouls());
			steals.add(p.getSteals());
			turnovers.add(p.getTurnovers());
			blocks.add(p.getBlocks());
			plusMinus.add(p.getPlusMinus());
			playersStatistics.add(p.toPlayerStatisticsResponse());
		}



		response.setFga(sommaInt(fga));
		response.setFgm(sommaInt(fgm));
		response.setFgp(sommaDouble(fgp));
		response.setFta(sommaInt(fta));
		response.setFtm(sommaInt(ftm));
		response.setFtp(sommaDouble(ftp));
		response.setTpa(sommaInt(tpa));
		response.setTpm(sommaInt(tpm));
		response.setTpp(sommaDouble(tpp));
		response.setOffReb(sommaInt(offReb));
		response.setDefReb(sommaInt(defReb));
		response.setTotReb(sommaInt(totReb));
		response.setAssists(sommaInt(assists));
		response.setPFouls(sommaInt(pFouls));
		response.setSteals(sommaInt(steals));
		response.setTurnovers(sommaInt(turnovers));
		response.setBlocks(sommaInt(blocks));
		response.setPlusMinus(sommaInt(plusMinus));
		response.setPlayersStatistics(playersStatistics);
		response.setQaurtiScoreResponse(qaurtiScoreResponse);
		response.toMap();
		return response;
	}
	public int sommaInt(List<Integer> lista) {
		int somma = 0;
		for (Integer integer : lista) {
			somma += integer;
		}
		return somma;
	}
	public double sommaDouble(List<Double> lista) {
		double somma = 0;
		for (Double integer : lista) {
			somma += integer;
		}
		return somma;
	}

	public List<CalendarioDateResponse> toCalendarioDateResponse(List<Games> games){
		List<CalendarioDateResponse> calendarioResponseList = new ArrayList<>();
		for (Games game : games)
		{
			CalendarioDateResponse calendario = new CalendarioDateResponse() ;
			calendario.setGameid(game.getId());
			calendario.setGameStartDate(game.getStartDate());


			if (game.getHomeTeam() != null) {
				calendario.setTeamHomeName(game.getHomeTeam().getTeamName());
				calendario.setConferenceNameHome(game.getHomeTeam().getLeague().getConference());
				calendario.setDivisionNameHome(game.getHomeTeam().getLeague().getDivision());
				calendario.setTeamIdHome(game.getHomeTeam().getId());
				calendario.setAllStarHome(game.getHomeTeam().isAllStar());
				calendario.setNicknameHome(game.getHomeTeam().getNickname());
				calendario.setLogoHome(game.getHomeTeam().getLogo());
				calendario.setCityHome(game.getHomeTeam().getCity());
				calendario.setCodeHome(game.getHomeTeam().getCode());

				System.out.println(game.getHomeTeam().getTeamName());
				System.out.println(game.getId());
				if (scoreRepository.findByGameAndTeam(game, game.getHomeTeam()) != null) {
					calendario.setScoreTeamHome(scoreRepository.findByGameAndTeam(game, game.getHomeTeam()).getPoints());
				}
			}
			else { calendario.setTeamHomeName(" "); }


			if (game.getAwayTeam() != null) {
				calendario.setTeamAwayName(game.getAwayTeam().getTeamName());
				calendario.setConferenceNameAway(game.getAwayTeam().getLeague().getConference());
				calendario.setDivisionNameAway(game.getAwayTeam().getLeague().getDivision());
				calendario.setTeamIdAway(game.getAwayTeam().getId());
				calendario.setAllStarAway(game.getAwayTeam().isAllStar());
				calendario.setNicknameAway(game.getAwayTeam().getNickname());
				calendario.setLogoAway(game.getAwayTeam().getLogo());
				calendario.setCityAway(game.getAwayTeam().getCity());
				calendario.setCodeAway(game.getAwayTeam().getCode());
				if (scoreRepository.findByGameAndTeam(game, game.getAwayTeam()) != null){
					calendario.setScoreTeamAway(scoreRepository.findByGameAndTeam(game, game.getAwayTeam()).getPoints());
				}


			}
			else { calendario.setTeamHomeName(" "); }

			calendarioResponseList.add(calendario);
		}
		return calendarioResponseList;
	}


	public List<CalendarioDateResponse> getLast20FromDate(LocalDate date) {
		List<Games> games = repository.findByStartDateBetween(date.minusDays(20).toString(), date.toString());
		return toCalendarioDateResponse(games);

	}
}
