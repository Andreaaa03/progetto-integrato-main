package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Repository.CommentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commenti")
@CrossOrigin
public class CommentiController {

	@Autowired
	private CommentiRepository repository;
	@Autowired
	public CommentiController(CommentiRepository repository) {
		this.repository = repository;
	}
	@GetMapping("/All")
	public List<Commenti> getCommenti() {
		return repository.findAll();
	}

	@PostMapping("/add")
	public String addCommento(@RequestBody Commenti commento) {
		repository.save(commento);
		return "Commento aggiunto con successo";
	}



}
