package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Payload.Request.SignupRequest;
import com.slamDunkers.SlamStats.Service.UtenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UtenteController {
	public final UtenteService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> save(@RequestBody @Valid SignupRequest request){
		return userService.save(request);
	}

}
