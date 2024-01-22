package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Blog;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Entity.Utente;
import com.slamDunkers.SlamStats.Entity.UtentePreferiti;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Utente_TeamRepository extends JpaRepository<UtentePreferiti, Integer> {
	@Override
    UtentePreferiti save(UtentePreferiti utente_team);

	Optional<UtentePreferiti> findByIdUtenteAndIdTeam(Utente utente, Teams teams);

	Optional<UtentePreferiti> findByIdUtenteAndIdArticolo(Utente utente, Blog idArticolo);

	@Query(value = "SELECT * \n" +
			 "FROM `utente_preferiti` ut\n" +
			 "WHERE `utente` = :idUtente AND `articolo` IS NOT null;", nativeQuery = true)
	 	List<UtentePreferiti> findArticoliPreferiti(@Param("idUtente") int idUtente);

	@Query(value = "SELECT * \n" +
			 "FROM `utente_preferiti` ut\n" +
			 "WHERE `utente` = :idUtente AND `team` IS NOT null;", nativeQuery = true)
	 	List<UtentePreferiti> findTeamPreferiti(@Param("idUtente") int idUtente);


	 @Query(value = "SELECT COUNT(ut.id) \n" +
			 "FROM `utente_preferiti` ut\n" +
			 "WHERE `utente` = :idUtente AND `team` IS NOT null;", nativeQuery = true)
	 Integer findByIdUtente(@Param("idUtente") int idUtente);


}
