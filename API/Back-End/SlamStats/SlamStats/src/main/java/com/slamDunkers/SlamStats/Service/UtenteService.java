package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Roles;
import com.slamDunkers.SlamStats.Entity.Utente;
import com.slamDunkers.SlamStats.Payload.Request.SignupRequest;
import com.slamDunkers.SlamStats.Repository.RolesRepository;
import com.slamDunkers.SlamStats.Repository.UtenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {
	public final UtenteRepository utenteRepository;
	public final RolesRepository rolesRepository;





	public UtenteService(UtenteRepository utenteRepository, RolesRepository rolesRepository) {
		this.utenteRepository = utenteRepository;
		this.rolesRepository = rolesRepository;

	}

	public ResponseEntity<?> save(SignupRequest request){
		return new ResponseEntity(utenteRepository.save(fromRequestToEntity(request)), HttpStatus.CREATED);
	}

	private Utente fromRequestToEntity(SignupRequest request) {
		Utente u = new Utente();
		Optional<Roles> role = rolesRepository.findByRole("utente");
		if(role.isPresent()) {
			return new Utente(request.getFirst_name(), request.getLast_name(), request.getBirth_date(), request.getEmail(), request.getPswd(), role.get());
		}
		return u;
	}



}
