package com.slamDunkers.SlamStats.Payload.Response;

import com.slamDunkers.SlamStats.Entity.Blog;
import com.slamDunkers.SlamStats.Entity.Paragrafo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BlogCompleto {
    Blog blog;
    List<ParagrafoRespone> paragrafi;
    List<String> tags;
}
