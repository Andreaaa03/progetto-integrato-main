package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass( SondaggioId.class)
public class Sondaggio {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id",referencedColumnName = "game", nullable = false)
    private Games game;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utente_id",referencedColumnName = "utente", nullable = false)
    private Utente utente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scommessa", nullable = false)
    private Teams scommessa;

}
