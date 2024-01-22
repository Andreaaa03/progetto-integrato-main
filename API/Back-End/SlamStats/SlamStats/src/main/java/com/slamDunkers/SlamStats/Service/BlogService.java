package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Blog;
import com.slamDunkers.SlamStats.Entity.Paragrafo;
import com.slamDunkers.SlamStats.Entity.Tag;
import com.slamDunkers.SlamStats.Payload.Response.BlogCompleto;
import com.slamDunkers.SlamStats.Payload.Response.ParagrafoRespone;
import com.slamDunkers.SlamStats.Payload.Response.TagResponse;
import com.slamDunkers.SlamStats.Repository.BlogRepository;
import com.slamDunkers.SlamStats.Repository.ParagrafoRepository;
import com.slamDunkers.SlamStats.Repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final ParagrafoRepository paragrafoRepository;
    private final TagRepository tagRepository;

    public BlogService(BlogRepository blogRepository, ParagrafoRepository paragrafoRepository, TagRepository tagRepository) {
        this.blogRepository = blogRepository;
        this.paragrafoRepository = paragrafoRepository;
        this.tagRepository = tagRepository;
    }

    public List<Blog> getBlog() {
        return blogRepository.findByOrderByCreazioneDesc();
    }

/**
 * This method retrieves a complete blog post by its ID.
 *
 * @param id The ID of the blog post to retrieve.
 * @return A BlogCompleto object that contains the full details of the blog post, including paragraphs and tags.
 * If no blog post with the given ID is found, this method returns null.
 */
public BlogCompleto getBlogCompleto(Integer id) {
    Blog blog = blogRepository.findById(id).orElse(null);
    if (blog == null) {
        return null;
    }
    BlogCompleto blogCompleto = new BlogCompleto();
    blogCompleto.setBlog(blogRepository.findById(id).orElse(null));
    blogCompleto.setParagrafi(toParagrafoRespone( paragrafoRepository.findByIdBlog(blog)) );
    List<Tag> allByBlog = tagRepository.findAllByBlog(id);
    blogCompleto.setTags(toListString (allByBlog));
    return blogCompleto;
}

/**
 * This method converts a list of Tag objects into a list of Strings.
 * Each Tag object has a 'tag' field which is added to the list of Strings.
 *
 * @param allByBlog A list of Tag objects to be converted.
 * @return A list of Strings representing the 'tag' field of each Tag object.
 */
private List<String> toListString(List<Tag> allByBlog) {
    List<String> tags = new ArrayList<>();
    for (Tag t: allByBlog) {
        tags.add(t.getTag());
    }
    return tags;
}

    /**
     * This method converts a list of Paragrafo objects into a list of ParagrafoRespone objects.
     * Each Paragrafo object has 'titolo', 'testo', and 'foto' fields which are set in the ParagrafoRespone object.
     *
     * @param paragrafo A list of Paragrafo objects to be converted.
     * @return A list of ParagrafoRespone objects representing the 'titolo', 'testo', and 'foto' fields of each Paragrafo object.
     */
    public List<ParagrafoRespone> toParagrafoRespone(List<Paragrafo> paragrafo){
        List<ParagrafoRespone> paragrafoRespone = new ArrayList<>();
        for (Paragrafo p: paragrafo) {
            ParagrafoRespone paragrafoRespone1 = new ParagrafoRespone();
            paragrafoRespone1.setTitoloParagrafo(p.getTitolo());
            paragrafoRespone1.setTestoParagrafo(p.getTesto());
            paragrafoRespone1.setFoto(p.getFoto());
            paragrafoRespone.add(paragrafoRespone1);
        }
        return paragrafoRespone;

    }

}
