package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paragrafo {
//    Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Could not determine recommended JdbcType for Java type 'com.slamDunkers.SlamStats.Entity.Blog'
    @Id
    private Integer id;

    @Column(name = "titolo_paragrafo")
    private String titolo;

    @Column(name = "testo_paragrafo")
    private String testo;

    @Column(name = "foto")
    private String foto;

    @ManyToOne
    @JoinColumn(name = "id_blog")
    private Blog idBlog;
}
