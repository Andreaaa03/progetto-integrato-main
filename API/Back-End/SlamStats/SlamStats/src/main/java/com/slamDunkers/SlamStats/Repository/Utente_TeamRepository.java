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
			 "WHERE `utente` = :idUtente AND `articolo` IS NOT null " +
			"ORDER by `data`", nativeQuery = true)
	 	List<UtentePreferiti> findArticoliPreferiti(@Param("idUtente") int idUtente);

@Query(value = "SELECT * \n" +
    "FROM `utente_preferiti` ut\n" +
    "WHERE `utente` = :idUtente AND `team` IS NOT null " +
   "ORDER by `data`", nativeQuery = true)
List<UtentePreferiti> findTeamPreferiti(@Param("idUtente") int idUtente);


	 @Query(value = "SELECT COUNT(ut.id) \n" +
			 "FROM `utente_preferiti` ut\n" +
			 "join teams t on t.id = ut.team\n" +
			 "join league l on l.id = t.league_id\n" +
			 "join conference c on c.ID = l.conference_id\n" +
			 "WHERE `utente` = :idUtente AND `team` IS NOT null AND c.ID = :conference", nativeQuery = true)
	 Integer findByIdUtente(@Param("idUtente") int idUtente,@Param("conference") int conference);


}
