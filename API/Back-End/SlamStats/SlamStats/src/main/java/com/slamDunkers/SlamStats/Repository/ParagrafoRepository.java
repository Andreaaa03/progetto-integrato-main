package com.slamDunkers.SlamStats.Repository;

import com.slamDunkers.SlamStats.Entity.Blog;
import com.slamDunkers.SlamStats.Entity.Paragrafo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParagrafoRepository extends JpaRepository<Paragrafo,Integer> {
    List<Paragrafo> findByIdBlog(Blog idBlog);

}
