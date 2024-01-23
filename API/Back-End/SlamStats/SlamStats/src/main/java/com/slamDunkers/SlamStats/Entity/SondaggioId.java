package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class SondaggioId implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partita ", nullable = false)
    private Games game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utente", nullable = false)
    private Utente utente;
}
