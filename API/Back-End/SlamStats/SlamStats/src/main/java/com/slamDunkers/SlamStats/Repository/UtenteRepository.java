package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

/**
 * This method is used to find a Utente entity by its email, username or phone number and password.
 * @param email This is the email, username or phone number of the Utente entity to be found.
 * @param password This is the password of the Utente entity to be found.
 * @return Optional<Utente> This returns the Utente entity with the given email, username or phone number and password, if it exists.
 */
@Query("SELECT u FROM Utente u WHERE u.email = :email OR u.username = :email OR u.numeroTelefono = :email AND u.passwd = :password")
Optional<Utente> findByEmailOrUsernameOrNumeroTelefonoAndPasswd(String email, String password);

/**
 * This method is used to find a Utente entity by its email.
 * @param email This is the email of the Utente entity to be found.
 * @return Optional<Object> This returns the Utente entity with the given email, if it exists.
 */
Optional<Object> findByEmail(String email);

/**
 * This method is used to find a Utente entity by its username.
 * @param username This is the username of the Utente entity to be found.
 * @return Optional<Object> This returns the Utente entity with the given username, if it exists.
 */
Optional<Object> findByUsername(String username);

/**
 * This method is used to find a Utente entity by its phone number.
 * @param numeroTelefono This is the phone number of the Utente entity to be found.
 * @return Optional<Object> This returns the Utente entity with the given phone number, if it exists.
 */
Optional<Object> findByNumeroTelefono(String numeroTelefono);
}
