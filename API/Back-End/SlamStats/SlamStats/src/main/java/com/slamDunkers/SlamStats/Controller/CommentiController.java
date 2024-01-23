package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Payload.Request.CommentiRequest;
import com.slamDunkers.SlamStats.Payload.Request.TokenRequest;
import com.slamDunkers.SlamStats.Service.CommentiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commenti")
@CrossOrigin
public class CommentiController {

	private final CommentiService commentiService;
	public CommentiController(CommentiService commentiService) {
        this.commentiService = commentiService;
	}
	@PostMapping("/add")
	public ResponseEntity<String> addCommento(@RequestBody CommentiRequest commento) {
		return commentiService.commenta(commento);
	}
	@PostMapping("/commentiUtente")
	public List<Optional<Commenti>> commentiUtente(@RequestBody TokenRequest token) {
		return commentiService.commentiUtente(token);
	}



}
