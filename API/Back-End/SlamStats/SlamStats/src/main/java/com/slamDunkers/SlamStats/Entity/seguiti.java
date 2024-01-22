package com.slamDunkers.SlamStats.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class seguiti {

    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seguito", referencedColumnName = "id")
    private Utente seguito;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seguace", referencedColumnName = "id")
    private Utente seguace;

}
