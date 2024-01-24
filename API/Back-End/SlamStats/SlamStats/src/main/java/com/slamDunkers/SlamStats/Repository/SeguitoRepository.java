package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Seguiti;
import com.slamDunkers.SlamStats.Payload.Response.AmicoResponse;
import com.slamDunkers.SlamStats.Payload.Response.UtenteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeguitoRepository extends JpaRepository<Seguiti, Integer> {


    @Query(value = "SELECT * FROM `seguiti` WHERE `seguito` = :altroId AND `seguace` = :mioId", nativeQuery = true)
    Optional<Seguiti> findSeguito(int mioId, int altroId);

    @Query(value = "SELECT COUNT(*) FROM `seguiti` WHERE `seguito` = :mioId", nativeQuery = true)
    int numeroSeguaci(int mioId);

    @Query(value = "SELECT s.seguito, u.username, s.amico, r.role, " +
            "(SELECT COUNT(s1.id) FROM seguiti s1 WHERE s1.seguito = u.id ) as follower, " +
            "(SELECT COUNT(s1.id) FROM seguiti s1 WHERE s1.seguace = u.id ) as following " +
            "FROM seguiti s " +
            "JOIN utente u ON s.seguito = u.id " +
            "JOIN roles r ON r.id = u.role_id " +
            "WHERE s.seguito = :mioId ", nativeQuery = true)
    List<Object[]> daChiVengoSeguito(@Param("mioId") Integer mioId);


    @Query(value = "SELECT s.seguito, u.username, s.amico, r.role, " +
            "(SELECT COUNT(s1.id) FROM seguiti s1 WHERE s1.seguito = u.id ) as follower, " +
            "(SELECT COUNT(s1.id) FROM seguiti s1 WHERE s1.seguace = u.id ) as following " +
            "FROM seguiti s " +
            "JOIN utente u ON s.seguito = u.id " +
            "JOIN roles r ON r.id = u.role_id " +
            "WHERE s.seguace = :mioId ", nativeQuery = true)
    List<Object[]> chiSeguo(@Param("mioId") Integer mioId);

}
