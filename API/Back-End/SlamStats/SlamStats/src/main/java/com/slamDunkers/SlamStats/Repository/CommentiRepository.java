package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Blog;
import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Entity.Games;
import com.slamDunkers.SlamStats.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentiRepository extends JpaRepository<Commenti,Integer> {
/**
 * This method is used to find all Commenti entities.
 * @return List<Commenti> This returns a list of all Commenti entities.
 */
@Override
List<Commenti> findAll();

/**
 * This method is used to find all Commenti entities by a specific user.
 * @param u This is the user whose comments are to be found.
 * @return List<Optional<Commenti>> This returns a list of comments made by the specified user.
 */
List<Optional<Commenti>> findAllByIdUtente(Utente u);

/**
 * This method is used to find all Commenti entities for a specific blog.
 * @param blog This is the blog whose comments are to be found.
 * @return List<Optional<Commenti>> This returns a list of comments made on the specified blog.
 */
List<Optional<Commenti>> findAllByBlog(Blog blog);

/**
 * This method is used to find all Commenti entities for a specific game.
 * @param games This is the game whose comments are to be found.
 * @return List<Optional<Commenti>> This returns a list of comments made on the specified game.
 */
List<Optional<Commenti>> findAllByIdGames(Games games);
}
