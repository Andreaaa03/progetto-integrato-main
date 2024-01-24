package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Blog;
import com.slamDunkers.SlamStats.Entity.Paragrafo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParagrafoRepository extends JpaRepository<Paragrafo,Integer> {
    /**
 * This method is used to find all Paragrafo entities associated with a specific Blog.
 * @param idBlog This is the Blog entity whose associated Paragrafo entities are to be found.
 * @return List<Paragrafo> This returns a list of Paragrafo entities associated with the specified Blog.
 */
List<Paragrafo> findByIdBlog(Blog idBlog);

}
