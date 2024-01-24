package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

/**
 * This method is used to find all Blog entities and order them by the 'creazione' field in descending order.
 * @return List<Blog> This returns a list of Blog entities ordered by 'creazione' in descending order.
 */
List<Blog> findByOrderByCreazioneDesc();

/**
 * This method is used to find a Blog entity by its id.
 * @param id This is the id of the Blog entity to be found.
 * @return Blog This returns the Blog entity with the given id.
 */
Blog findById(int id);

}
