package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.Blog;
import com.slamDunkers.SlamStats.Payload.Response.BlogCompleto;
import com.slamDunkers.SlamStats.Service.BlogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/simple")
    public List<Blog> getSimple() {
        return blogService.getBlog();
    }

    @GetMapping("/completo")
    public BlogCompleto getCompleto(Integer id) {
        return blogService.getBlogCompleto(id);
    }

}
