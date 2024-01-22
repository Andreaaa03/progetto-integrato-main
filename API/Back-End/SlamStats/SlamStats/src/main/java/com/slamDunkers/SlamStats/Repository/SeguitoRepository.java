package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Seguiti;
import com.slamDunkers.SlamStats.Payload.Response.UtenteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeguitoRepository extends JpaRepository<Seguiti, Integer> {


    @Query(value = "SELECT * FROM `seguiti` WHERE `seguito` = :altroId AND `seguace` = :mioId", nativeQuery = true)
    Optional<Seguiti> findSeguito(int mioId, int altroId);

    @Query(value = "SELECT COUNT(*) FROM `seguiti` WHERE `seguito` = :mioId", nativeQuery = true)
    int numeroSeguaci(int mioId);

    @Query(value = "SELECT * FROM `seguiti` WHERE `seguace` = :mioId", nativeQuery = true)
    Optional<UtenteResponse> chiSeguo(int mioId);

    @Query(value = "SELECT * FROM `seguiti` WHERE `seguito` = :mioId", nativeQuery = true)
    Optional<UtenteResponse> daChivengoSeguito(int mioId);

}
