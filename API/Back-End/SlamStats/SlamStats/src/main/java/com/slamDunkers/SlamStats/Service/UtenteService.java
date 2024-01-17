package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Roles;
import com.slamDunkers.SlamStats.Entity.Utente;
import com.slamDunkers.SlamStats.Payload.Request.SignupRequest;
import com.slamDunkers.SlamStats.Payload.Request.SinginRequest;
import com.slamDunkers.SlamStats.Payload.Response.AuthResponse;
import com.slamDunkers.SlamStats.Repository.RolesRepository;
import com.slamDunkers.SlamStats.Repository.UtenteRepository;
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
	public final TockenService tokenService;

	public UtenteService(UtenteRepository utenteRepository, RolesRepository rolesRepository, TockenService tokenService) {
		this.utenteRepository = utenteRepository;
		this.rolesRepository = rolesRepository;
		this.tokenService = tokenService;
	}
	public ResponseEntity<?> save(SignupRequest request){
		Optional<Object> u = utenteRepository.findByEmail(request.getEmail());
		if (u.isPresent()){
			return new ResponseEntity("Email già in uso", HttpStatus.BAD_REQUEST);
		}
		else{
			return new ResponseEntity(utenteRepository.save(fromRequestToEntity(request)), HttpStatus.CREATED);
		}
	}

	private Utente fromRequestToEntity(SignupRequest request) {
		Utente u = new Utente();
		u.setFirstName(request.getFirst_name());
		u.setLastName(request.getLast_name());
		u.setBirthDate(request.getBirthDate());
		u.setEmail(request.getEmail());
		u.setPasswd(new DigestUtils("SHA3-256").digestAsHex(request.getPasswd()));
		u.setNumeroTelefono(request.getNumeroTelefono());
		u.setDataIscrizione(java.time.LocalDate.now());
		if (request.getEmail().contains("@edu.itspiemonte.it")) {
			u.setRoleId(new Roles(3,"blogger"));
		} else {
			u.setRoleId(new Roles(2,"user"));
		}
		return u;
	}


	public ResponseEntity<?> accesso(SinginRequest request, HttpSession session) {
		Optional<Utente> u = utenteRepository.findByEmailAndPasswd(request.getEmail(), new DigestUtils("SHA3-256").digestAsHex(request.getPasswd()));

		if(u.isPresent()) {
			String token = tokenService.createToken(u.get().getId(), u.get().getRoleId().getRole());
			AuthResponse authenticadedUser = new AuthResponse(u.get().getId(), u.get().getFirstName(), u.get().getRoleId().getRole(), token);
			session.setAttribute("userRole", u.get().getRoleId().getRole());
			return new ResponseEntity(authenticadedUser, HttpStatus.OK);
		}
		else{
		return new ResponseEntity("Username o password errati", HttpStatus.BAD_REQUEST);
		}
	}
}
