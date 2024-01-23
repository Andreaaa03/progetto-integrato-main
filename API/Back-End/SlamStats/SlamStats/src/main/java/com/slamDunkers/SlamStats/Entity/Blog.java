package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "titolo")
    private String titolo;
    @Column(name = "riassunto")
    private String riassunto;
    @Column(name = "foto")
    private String foto;

    @Column(name ="creazione", columnDefinition = "TIMESTAMP")
    private LocalDateTime creazione;

    @Column(name ="numero_foto")
    private Integer numeroFoto;

    @Column(name ="tipo")
    private String tipo;
    @Column(name ="colori")
    private String colori;


}
