package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Roles;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Entity.Utente;
import com.slamDunkers.SlamStats.Entity.UtenteTeam;
import com.slamDunkers.SlamStats.Payload.Request.SignupRequest;
import com.slamDunkers.SlamStats.Payload.Request.SinginRequest;
import com.slamDunkers.SlamStats.Payload.Request.UtenteTeamRequest;
import com.slamDunkers.SlamStats.Payload.Response.AuthResponse;
import com.slamDunkers.SlamStats.Repository.RolesRepository;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import com.slamDunkers.SlamStats.Repository.UtenteRepository;
import com.slamDunkers.SlamStats.Repository.Utente_TeamRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
	public final UtenteRepository utenteRepository;
	public final RolesRepository rolesRepository;
	public final TokenService tokenService;
	public final Utente_TeamRepository utente_teamRepository;
	public final TeamsRepository teamsRepository;
	public final HttpServletRequest request;

	public UtenteService(UtenteRepository utenteRepository, RolesRepository rolesRepository, TokenService tokenService, Utente_TeamRepository utente_teamRepository, TeamsRepository teamsRepository, HttpServletRequest request) {
		this.utenteRepository = utenteRepository;
		this.rolesRepository = rolesRepository;
		this.tokenService = tokenService;
		this.utente_teamRepository = utente_teamRepository;
		this.teamsRepository = teamsRepository;
		this.request = request;
	}
	public ResponseEntity<String> save(SignupRequest request){
		Optional<Object> u = utenteRepository.findByEmail(request.getEmail());
		if (u.isPresent()){
			return new ResponseEntity<>("Email già presente",HttpStatus.BAD_REQUEST);
		}
		else{
			utenteRepository.save(fromRequestToEntity(request));
			return new ResponseEntity<>("Utente creato con successo",HttpStatus.CREATED);
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
		Optional<Utente> u = utenteRepository.findByEmailOrUsernameAndPasswd(request.getEmail(), new DigestUtils("SHA3-256").digestAsHex(request.getPasswd()));
		if(u.isPresent()) {
			String token = tokenService.createToken(u.get().getId(), u.get().getRoleId().getRole());
			AuthResponse authenticatedUser = new AuthResponse(u.get().getId(), u.get().getFirstName(), u.get().getRoleId().getRole(), token);
			session.setAttribute("userRole", u.get().getRoleId().getRole());
			return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
		}
		else{
		return new ResponseEntity("Username o password errati", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> teamPreferito(UtenteTeamRequest request ) {
		Integer idUtente = tokenService.getUserIdFromToken(request.getToken()).getId();
		Optional<Utente> u = utenteRepository.findById(idUtente);
		Optional<Teams> t = teamsRepository.findById(request.getIdTeam());
		Optional<List<UtenteTeam>> ut5 = utente_teamRepository.findByIdUtente(u.get());

		if(ut5.get().size()>=5){
			return new ResponseEntity<>("Hai già raggiunto il numero massimo di team",HttpStatus.BAD_REQUEST);
		}

		if(!u.isPresent() && !t.isPresent()){
			return new ResponseEntity<>("Utente o Team non trovato",HttpStatus.BAD_REQUEST);
		}

		Optional<UtenteTeam> ut = utente_teamRepository.findByIdUtenteAndIdTeam(u.get(), t.get());
		if(ut.isPresent()){
			utente_teamRepository.delete(ut.get());
			return new ResponseEntity<>("Team rimosso con successo",HttpStatus.OK);
		}
		else{
			UtenteTeam utente_team = new UtenteTeam();
			utente_team.setIdUtente(u.get());
			utente_team.setIdTeam(t.get());
			utente_teamRepository.save(utente_team);
			return new ResponseEntity<>("Team aggiunto con successo",HttpStatus.CREATED);
		}
	}



}
