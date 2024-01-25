package com.example.popolamento.Repository;

import com.example.popolamento.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Teams, Integer> {

	Optional<Teams> findById(Integer integer);
}
