package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Payload.Request.CommentiRequest;
import com.slamDunkers.SlamStats.Repository.CommentiRepository;
import com.slamDunkers.SlamStats.Service.CommentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



}
