package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
	Optional<Utente> findByEmailAndPasswd(String Email, String Password);

	Optional<Object> findByEmail(String email);
}
