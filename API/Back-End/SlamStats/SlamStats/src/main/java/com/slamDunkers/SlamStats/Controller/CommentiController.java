package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Repository.CommentiRepository;
import com.slamDunkers.SlamStats.Service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
