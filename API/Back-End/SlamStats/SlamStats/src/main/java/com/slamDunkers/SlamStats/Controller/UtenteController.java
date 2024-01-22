package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.Utente;
import com.slamDunkers.SlamStats.Payload.Request.SignupRequest;
import com.slamDunkers.SlamStats.Payload.Request.SinginRequest;
import com.slamDunkers.SlamStats.Payload.Request.UtenteArticoloRequest;
import com.slamDunkers.SlamStats.Payload.Request.UtenteTeamRequest;
import com.slamDunkers.SlamStats.Service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin
public class UtenteController {
	public final UtenteService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> save(@RequestBody @Valid SignupRequest request){
		return userService.save(request);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody @Valid SinginRequest request, HttpSession session){
		return userService.accesso(request, session);
	}

	@PostMapping("/teamPreferito")
	public ResponseEntity<?> teamPreferito(@RequestBody @Valid UtenteTeamRequest request){
		return userService.teamPreferito(request);
	}
	
	@PostMapping("/articoloPreferito")
	public ResponseEntity<?> articoloPreferito(@RequestBody @Valid UtenteArticoloRequest request){
		return userService.articoloPreferito(request);
	}
}
