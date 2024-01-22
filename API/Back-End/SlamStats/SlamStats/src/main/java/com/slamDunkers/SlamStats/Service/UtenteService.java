package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.*;
import com.slamDunkers.SlamStats.Payload.Request.SignupRequest;
import com.slamDunkers.SlamStats.Payload.Request.SinginRequest;
import com.slamDunkers.SlamStats.Payload.Request.UtenteArticoloRequest;
import com.slamDunkers.SlamStats.Payload.Request.UtenteTeamRequest;
import com.slamDunkers.SlamStats.Payload.Response.AuthResponse;
import com.slamDunkers.SlamStats.Repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UtenteService {
	public final UtenteRepository utenteRepository;
	public final RolesRepository rolesRepository;
	public final TokenService tokenService;
	public final Utente_TeamRepository utente_teamRepository;
	public final TeamsRepository teamsRepository;
	public final HttpServletRequest request;
	public final BlogRepository blogRepository;

	public UtenteService(UtenteRepository utenteRepository, RolesRepository rolesRepository, TokenService tokenService, Utente_TeamRepository utente_teamRepository, TeamsRepository teamsRepository, HttpServletRequest request, BlogRepository blogRepository) {
		this.utenteRepository = utenteRepository;
		this.rolesRepository = rolesRepository;
		this.tokenService = tokenService;
		this.utente_teamRepository = utente_teamRepository;
		this.teamsRepository = teamsRepository;
		this.request = request;
        this.blogRepository = blogRepository;
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

	public ResponseEntity<String> teamPreferito(UtenteTeamRequest request) {
		Integer idUtente = tokenService.getUserIdFromToken(request.getToken()).getId();
		Optional<Utente> u = utenteRepository.findById(idUtente);
		if(!u.isPresent()){
			return new ResponseEntity<>("Utente non trovato",HttpStatus.BAD_REQUEST);
		}
		Optional<Teams> t = teamsRepository.findById(request.getIdTeam());

		int ut5 = utente_teamRepository.findByIdUtente(u.get().getId());
		if(ut5 >= 5){
			return new ResponseEntity<>("Hai già raggiunto il limite di 5 squadre preferite",HttpStatus.BAD_REQUEST);
		}

		return SorRteamPreferiti(u.get(),t.get());
	}

	public ResponseEntity<String> SorRteamPreferiti(Utente u,Teams t) {
		Optional<UtentePreferiti> ut = utente_teamRepository.findByIdUtenteAndIdTeam(u, t);
		if(ut.isPresent()){
			utente_teamRepository.delete(ut.get());
			return new ResponseEntity<>("Team rimosso con successo",HttpStatus.OK);
		}
		else{
			UtentePreferiti utente_team = new UtentePreferiti();
			utente_team.setIdUtente(u);
			utente_team.setIdTeam(t);
			utente_teamRepository.save(utente_team);
			return new ResponseEntity<>("Team aggiunto con successo",HttpStatus.CREATED);
		}
	}

	public ResponseEntity<String> articoloPreferito(UtenteArticoloRequest request) {
		System.out.println(request.getToken());
		Integer idUtente = tokenService.getUserIdFromToken(request.getToken()).getId();
		Optional<Utente> u = utenteRepository.findById(idUtente);

		if (!u.isPresent()) {
			return new ResponseEntity<>("Utente non trovato", HttpStatus.BAD_REQUEST);
		}
		Blog b = blogRepository.findById(request.getIdArticolo());
		if (b == null) {
			return new ResponseEntity<>("Articolo non trovato", HttpStatus.BAD_REQUEST);
		}
		return SorRarticoloPreferiti(u.get(), b);

	}

	public ResponseEntity<String> SorRarticoloPreferiti(Utente u, Blog b) {
		Optional<UtentePreferiti> ub = utente_teamRepository.findByIdUtenteAndIdArticolo(u, b);

		if(ub.isPresent()){
			utente_teamRepository.delete(ub.get());
			return new ResponseEntity<>("Articolo rimosso con successo",HttpStatus.OK);
		}else{
			UtentePreferiti utente_team = new UtentePreferiti();
			utente_team.setIdUtente(u);
			utente_team.setIdArticolo(b);
			utente_teamRepository.save(utente_team);
			return new ResponseEntity<>("Articolo aggiunto con successo",HttpStatus.CREATED);
		}
	}





}
