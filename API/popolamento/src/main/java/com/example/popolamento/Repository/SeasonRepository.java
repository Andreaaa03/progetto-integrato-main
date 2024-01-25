package com.example.popolamento.Repository;

import com.example.popolamento.Entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
	Season findByYear(int year);
	Season findById(int id);
}

