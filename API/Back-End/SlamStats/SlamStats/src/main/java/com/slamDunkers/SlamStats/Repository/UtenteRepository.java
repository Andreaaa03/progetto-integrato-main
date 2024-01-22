package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	@Query("SELECT u FROM Utente u WHERE u.email = :email OR u.username = :email OR u.numeroTelefono = :email AND u.passwd = :password")
	Optional<Utente> findByEmailOrUsernameOrNumeroTelefonoAndPasswd(String email, String password);


	Optional<Object> findByEmail(String email);

	Optional<Object> findByUsername(String username);

	Optional<Object> findByNumeroTelefono(String numeroTelefono);
}
