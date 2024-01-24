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
/**
 * This method is used to save a UtentePreferiti entity.
 * @param utente_team This is the UtentePreferiti entity to be saved.
 * @return UtentePreferiti This returns the saved UtentePreferiti entity.
 */
@Override
UtentePreferiti save(UtentePreferiti utente_team);

/**
 * This method is used to find a UtentePreferiti entity by its user and team.
 * @param utente This is the user of the UtentePreferiti entity to be found.
 * @param teams This is the team of the UtentePreferiti entity to be found.
 * @return Optional<UtentePreferiti> This returns the UtentePreferiti entity with the given user and team, if it exists.
 */
Optional<UtentePreferiti> findByIdUtenteAndIdTeam(Utente utente, Teams teams);

/**
 * This method is used to find a UtentePreferiti entity by its user and article.
 * @param utente This is the user of the UtentePreferiti entity to be found.
 * @param idArticolo This is the article of the UtentePreferiti entity to be found.
 * @return Optional<UtentePreferiti> This returns the UtentePreferiti entity with the given user and article, if it exists.
 */
Optional<UtentePreferiti> findByIdUtenteAndIdArticolo(Utente utente, Blog idArticolo);

/**
 * This method is used to find a list of UtentePreferiti entities by user where the article is not null, ordered by date.
 * @param idUtente This is the id of the user.
 * @return List<UtentePreferiti> This returns a list of UtentePreferiti entities by the given user where the article is not null, ordered by date.
 */
@Query(value = "SELECT * \n" +
    "FROM `utente_preferiti` ut\n" +
    "WHERE `utente` = :idUtente AND `articolo` IS NOT null " +
   "ORDER by `data`", nativeQuery = true)
List<UtentePreferiti> findArticoliPreferiti(@Param("idUtente") int idUtente);

/**
 * This method is used to find a list of UtentePreferiti entities by user where the team is not null, ordered by date.
 * @param idUtente This is the id of the user.
 * @return List<UtentePreferiti> This returns a list of UtentePreferiti entities by the given user where the team is not null, ordered by date.
 */
@Query(value = "SELECT * \n" +
    "FROM `utente_preferiti` ut\n" +
    "WHERE `utente` = :idUtente AND `team` IS NOT null " +
   "ORDER by `data`", nativeQuery = true)
List<UtentePreferiti> findTeamPreferiti(@Param("idUtente") int idUtente);

/**
 * This method is used to count the number of UtentePreferiti entities by user where the team is not null and the conference matches the provided id.
 * @param idUtente This is the id of the user.
 * @param conference This is the id of the conference.
 * @return Integer This returns the count of UtentePreferiti entities by the given user where the team is not null and the conference matches the provided id.
 */
@Query(value = "SELECT COUNT(ut.id) \n" +
    "FROM `utente_preferiti` ut\n" +
    "join teams t on t.id = ut.team\n" +
    "join league l on l.id = t.league_id\n" +
    "join conference c on c.ID = l.conference_id\n" +
    "WHERE `utente` = :idUtente AND `team` IS NOT null AND c.ID = :conference", nativeQuery = true)
Integer findByIdUtente(@Param("idUtente") int idUtente,@Param("conference") int conference);

}
