package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Commenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentiRepository extends JpaRepository<Commenti,Integer> {
	@Override
	List<Commenti> findAll();


}
