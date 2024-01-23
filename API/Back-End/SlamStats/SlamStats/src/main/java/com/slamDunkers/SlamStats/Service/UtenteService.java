package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.*;
import com.slamDunkers.SlamStats.Payload.Request.*;
import com.slamDunkers.SlamStats.Payload.Response.*;
import com.slamDunkers.SlamStats.Repository.*;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
	private final UtenteRepository utenteRepository;

	private final TokenService tokenService;
	private final Utente_TeamRepository utenteTeamRepository;
	private final TeamsRepository teamsRepository;

	private final BlogRepository blogRepository;
	private final BlogService blogService;
	private final ToResponse toResponse;
	private final SeguitoRepository seguitoRepository;

	public UtenteService(UtenteRepository utenteRepository,TokenService tokenService, Utente_TeamRepository utenteTeamRepository, TeamsRepository teamsRepository, BlogRepository blogRepository, BlogService blogService, ToResponse toResponse, SeguitoRepository seguitoRepository) {
		this.utenteRepository = utenteRepository;
		this.tokenService = tokenService;
		this.utenteTeamRepository = utenteTeamRepository;
		this.teamsRepository = teamsRepository;
        this.blogRepository = blogRepository;
        this.blogService = blogService;
        this.toResponse = toResponse;
        this.seguitoRepository = seguitoRepository;
    }


/**
 * This method is used to save a new user to the database.
 * It first checks if the email, username, and phone number provided in the request are already present in the database.
 * If any of them are present, it returns a response indicating that the respective field is already present.
 * If none of them are present, it saves the new user to the database and returns a success message.
 *
 * @param request SignupRequest object containing the details of the user to be saved.
 * @return ResponseEntity with a message indicating the result of the operation and HTTP status.
 */
public ResponseEntity<String> save(SignupRequest request){
    Optional<Object> u = utenteRepository.findByEmail(request.getEmail());
    Optional<Object> u2 = utenteRepository.findByUsername(request.getUsername());
    Optional<Object> u3 = utenteRepository.findByNumeroTelefono(request.getNumeroTelefono());
    if (u.isPresent()){
        return new ResponseEntity<>("Email già presente",HttpStatus.OK);
    }
    else if(u2.isPresent()){
        return new ResponseEntity<>("Username già presente",HttpStatus.OK);
    }
    else if(u3.isPresent()){
        return new ResponseEntity<>("Numero di telefono già presente",HttpStatus.OK);
    }
    else{
        utenteRepository.save(fromRequestToEntity(request));
        return new ResponseEntity<>("Utente creato con successo",HttpStatus.OK);
    }
}

	private Utente fromRequestToEntity(SignupRequest request) {
		Utente u = new Utente();
		u.setFirstName(request.getFirst_name());
		u.setLastName(request.getLast_name());
		u.setBirthDate(request.getBirthDate());
		u.setUsername(request.getUsername());
		u.setEmail(request.getEmail());
		u.setSesso(request.getSesso());
		u.setPasswd(new DigestUtils("SHA3-256").digestAsHex(request.getPasswd()));
		u.setNumeroTelefono(request.getNumeroTelefono());
		if (request.getEmail().contains("@edu.itspiemonte.it")) {
			u.setRoleId(new Roles(3,"blogger"));
		} else {
			u.setRoleId(new Roles(2,"user"));
		}
		return u;
	}


	public ResponseEntity<AuthResponse> accesso(SinginRequest request, HttpSession session) {
		Optional<Utente> u = utenteRepository.findByEmailOrUsernameOrNumeroTelefonoAndPasswd(request.getEmail(), new DigestUtils("SHA3-256").digestAsHex(request.getPasswd()));
		if(u.isPresent()) {
			String token = tokenService.createToken(u.get().getId(), u.get().getRoleId().getRole());
			AuthResponse authenticatedUser = new AuthResponse(u.get().getId(), u.get().getFirstName(), u.get().getRoleId().getRole(), token);
			session.setAttribute("userRole", u.get().getRoleId().getRole());
			return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
		}
		else{
		return new ResponseEntity("Username o password errati", HttpStatus.OK);
		}
	}

	public ResponseEntity<String> teamPreferito(UtenteTeamRequest request) {
		Utente u = getUtente(request.getToken());
		Optional<Teams> t = teamsRepository.findById(request.getIdTeam());

		int ut5 = utenteTeamRepository.findByIdUtente(u.getId());
		if(ut5 >= 5){
			return new ResponseEntity<>("Hai già raggiunto il limite di 5 squadre preferite",HttpStatus.OK);
		}
		return SorRteamPreferiti(u,t.get());
	}

	public ResponseEntity<String> SorRteamPreferiti(Utente u,Teams t) {
		Optional<UtentePreferiti> ut = utenteTeamRepository.findByIdUtenteAndIdTeam(u, t);
		if(ut.isPresent()){
			utenteTeamRepository.delete(ut.get());
			return new ResponseEntity<>("Team rimosso con successo",HttpStatus.OK);
		}
		else{
			UtentePreferiti utente_team = new UtentePreferiti();
			utente_team.setIdUtente(u);
			utente_team.setIdTeam(t);
			utenteTeamRepository.save(utente_team);
			return new ResponseEntity<>("Team aggiunto con successo",HttpStatus.OK);
		}
	}

	public ResponseEntity<String> articoloPreferito(UtenteArticoloRequest request) {
		Utente u = getUtente(request.getToken());

		Blog b = blogRepository.findById(request.getIdArticolo());
		if (b == null) {
			return new ResponseEntity<>("Articolo non trovato", HttpStatus.OK);
		}
		return SorRarticoloPreferiti(u, b);

	}

	public ResponseEntity<String> SorRarticoloPreferiti(Utente u, Blog b) {
		Optional<UtentePreferiti> ub = utenteTeamRepository.findByIdUtenteAndIdArticolo(u, b);

		if(ub.isPresent()){
			utenteTeamRepository.delete(ub.get());
			return new ResponseEntity<>("Articolo rimosso con successo",HttpStatus.OK);
		}else{
			UtentePreferiti utente_team = new UtentePreferiti();
			utente_team.setIdUtente(u);
			utente_team.setIdArticolo(b);
			utenteTeamRepository.save(utente_team);
			return new ResponseEntity<>("Articolo aggiunto con successo",HttpStatus.OK);
		}
	}
	public List<BlogCompleto> getArticoliPreferiti(TokenRequest request) {
		Utente u = getUtente(request.getToken());

		List<UtentePreferiti> ub = utenteTeamRepository.findArticoliPreferiti (u.getId());
		if (ub == null) {
			return null;
		}
		List<BlogCompleto> blogCompleto = new ArrayList<>();
		for (UtentePreferiti utentePreferiti : ub) {
			blogCompleto.add(blogService.getBlogCompleto(utentePreferiti.getIdArticolo().getId()));
		}
		return blogCompleto;

	}


	public List<TeamsResponse> getTeamPreferiti(TokenRequest request) {
		Utente u = getUtente(request.getToken());

		List<UtentePreferiti> ut = utenteTeamRepository.findTeamPreferiti(u.getId());
		if (ut == null) {
			return null;
		}
		List<TeamsResponse> teams = new ArrayList<>();
		for (UtentePreferiti utentePreferiti : ut) {
			teams.add(toResponse.toTeamsResponse(utentePreferiti.getIdTeam()));
		}
		return teams;
	}

	public ResponseEntity<String> segui(SeguiRequest request){
		Utente u = getUtente(request.getToken());
		Optional<Utente> u2 = utenteRepository.findById(request.getIdUtente());
		if (!u2.isPresent()) {
			return new ResponseEntity<>("Utente non trovato",HttpStatus.OK);
		}
		Optional<Seguiti> s = seguitoRepository.findSeguito(u.getId(), u2.get().getId());
		if (s.isEmpty()){
			Seguiti seguito = new Seguiti();
			seguito.setSeguito(u2.get());
			seguito.setSeguace(u);
			seguitoRepository.save(seguito);
			return new ResponseEntity<>("Utente seguito con successo",HttpStatus.OK);
		}
		else{
			seguitoRepository.delete(s.get());
			return new ResponseEntity<>("Utente rimosso con successo",HttpStatus.OK);
		}
	}
	public UtenteResponse profilo(TokenRequest request) {
		Utente u = getUtente(request.getToken());
		return profiloUtente(u.getId());
	}

	public UtenteResponse profiloUtente(Integer id){
		Optional<Utente> u = utenteRepository.findById(id);
		if (!u.isPresent()) {
			throw new RuntimeException("Utente non trovato");
		}

		UtenteResponse ur = new UtenteResponse();
		
		ur.setId(u.get().getId());
		ur.setEmail(u.get().getEmail());
		ur.setNumeroTelefono(u.get().getNumeroTelefono());
		ur.setUsername(u.get().getUsername());
		ur.setSesso(u.get().getSesso());
		List<UtentePreferiti> lut = utenteTeamRepository.findTeamPreferiti(u.get().getId());
		List<TeamsResponse> teams = new ArrayList<>();
		for (UtentePreferiti utentePreferiti : lut) {
			teams.add(toResponse.toTeamsResponse(utentePreferiti.getIdTeam()));
		}

		return ur;
	}

	public Utente getUtente(String request){
		Integer utente = tokenService.getUserIdFromToken(request).getId();
		Optional<Utente> u = utenteRepository.findById(utente);
		if (!u.isPresent()) {
			throw new RuntimeException("Utente non trovato");
		}
		return u.get();
	}


}
