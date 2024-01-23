package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Blog;
import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentiRepository extends JpaRepository<Commenti,Integer> {
	@Override
	List<Commenti> findAll();


    List<Optional<Commenti>> findAllByIdUtente(Utente u);

    List<Optional<Commenti>> findAllByBlog(Blog blog);
}
