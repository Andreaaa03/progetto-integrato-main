package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
/**
 * This method is used to find all Tag entities associated with a specific blog.
 * @param idBlog This is the id of the blog whose associated Tag entities are to be found.
 * @return List<Tag> This returns a list of Tag entities associated with the specified blog.
 */
@Query(value = "SELECT t.id , t.tag, t.blog FROM `tag` t WHERE `blog` = :idBlog ", nativeQuery = true)
List<Tag> findAllByBlog(@Param("idBlog") Integer idBlog);
}
