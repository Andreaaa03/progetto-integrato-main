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
    @Query(value = "SELECT t.id , t.tag, t.blog FROM `tag` t WHERE `blog` = :idBlog ", nativeQuery = true)
    List<Tag> findAllByBlog(@Param("idBlog") Integer idBlog);
}
