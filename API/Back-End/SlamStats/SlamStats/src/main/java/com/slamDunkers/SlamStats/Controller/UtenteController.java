package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Payload.Request.*;
import com.slamDunkers.SlamStats.Payload.Response.*;
import com.slamDunkers.SlamStats.Service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
	@PostMapping("/getArticoliPreferiti")
	public List<BlogCompleto> getArticoliPreferiti(@RequestBody @Valid TokenRequest request){
		return userService.getArticoliPreferiti(request);
	}

	@PostMapping("/getTeamPreferiti")
	public List<TeamsResponse> getTeamPreferiti(@RequestBody @Valid TokenRequest request){
		return userService.getTeamPreferiti(request);
	}
	@PostMapping("/seguiUtente")
	public ResponseEntity<String> seguiUtente(@RequestBody @Valid SeguiRequest request){
		return userService.segui(request);
	}

	@PostMapping("/profilo")
	public UtenteResponse profilo(@RequestBody @Valid TokenRequest request){
		return userService.profilo(request);
	}

	@PostMapping("/teamArticoliPreferiti")
	public TeamArticoliPreferitiResponse teamArticoliPreferiti(@RequestBody @Valid TokenRequest request){
		return userService.teamArticoliPreferiti(request);
	}

	@PostMapping("/addAmico")
	public ResponseEntity<String> addAmico(@RequestBody @Valid SeguiRequest request){
		return userService.addAmico(request);
	}

	@PostMapping("/getAmici")
	public List<AmicoResponse> getAmici(@RequestBody @Valid TokenRequest request){
		return userService.getAmici(request);
	}

}
