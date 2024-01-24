package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Seguiti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeguitoRepository extends JpaRepository<Seguiti, Integer> {


/**
 * This method is used to find a Seguiti entity where the 'seguito' and 'seguace' match the provided ids.
 * @param mioId This is the id of the 'seguace'.
 * @param altroId This is the id of the 'seguito'.
 * @return Optional<Seguiti> This returns the Seguiti entity if it exists.
 */
@Query(value = "SELECT * FROM `seguiti` WHERE `seguito` = :altroId AND `seguace` = :mioId", nativeQuery = true)
Optional<Seguiti> findSeguito(int mioId, int altroId);

/**
 * This method is used to count the number of 'seguiti' where the 'seguito' matches the provided id.
 * @param mioId This is the id of the 'seguito'.
 * @return int This returns the count of 'seguiti'.
 */
@Query(value = "SELECT COUNT(*) FROM `seguiti` WHERE `seguito` = :mioId", nativeQuery = true)
int numeroSeguaci(int mioId);

/**
 * This method is used to find a list of users who follow the user with the provided id.
 * @param mioId This is the id of the user.
 * @return List<Object[]> This returns a list of users who follow the user with the provided id.
 */
@Query(value = "SELECT s.seguito, u.username, s.amico, r.role, " +
        "(SELECT COUNT(s1.id) FROM seguiti s1 WHERE s1.seguito = u.id ) as follower, " +
        "(SELECT COUNT(s1.id) FROM seguiti s1 WHERE s1.seguace = u.id ) as following " +
        "FROM seguiti s " +
        "JOIN utente u ON s.seguito = u.id " +
        "JOIN roles r ON r.id = u.role_id " +
        "WHERE s.seguito = :mioId ", nativeQuery = true)
List<Object[]> daChiVengoSeguito(@Param("mioId") Integer mioId);

/**
 * This method is used to find a list of users that the user with the provided id is following.
 * @param mioId This is the id of the user.
 * @return List<Object[]> This returns a list of users that the user with the provided id is following.
 */
@Query(value = "SELECT s.seguito, u.username, s.amico, r.role, " +
        "(SELECT COUNT(s1.id) FROM seguiti s1 WHERE s1.seguito = u.id ) as follower, " +
        "(SELECT COUNT(s1.id) FROM seguiti s1 WHERE s1.seguace = u.id ) as following " +
        "FROM seguiti s " +
        "JOIN utente u ON s.seguito = u.id " +
        "JOIN roles r ON r.id = u.role_id " +
        "WHERE s.seguace = :mioId ", nativeQuery = true)
List<Object[]> chiSeguo(@Param("mioId") Integer mioId);

}
