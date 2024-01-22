package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Entity.Utente;
import com.slamDunkers.SlamStats.Payload.Request.CommentiRequest;
import com.slamDunkers.SlamStats.Repository.BlogRepository;
import com.slamDunkers.SlamStats.Repository.CommentiRepository;
import com.slamDunkers.SlamStats.Repository.GamesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentiService {
    public final CommentiRepository commentiRepository;
    public final UtenteService utenteService;
    public final GamesRepository gamesRepository;
    public final BlogRepository blogRepository;
    public CommentiService(CommentiRepository commentiRepository, UtenteService utenteService, GamesRepository gamesRepository, BlogRepository blogRepository) {
        this.commentiRepository = commentiRepository;
        this.utenteService = utenteService;
        this.gamesRepository = gamesRepository;
        this.blogRepository = blogRepository;
    }



    public ResponseEntity<String> commenta(CommentiRequest commento) {

            Utente u = utenteService.getUtente(commento.getToken());
            try {


            Commenti c = new Commenti();
            c.setTesto(commento.getTesto());
            c.setId_utente(u);
                if (commento.getId_commento_padre() != null && commento.getId_commento_padre().isPresent()) {
                    Optional<Commenti> commento_padre = commentiRepository.findById(commento.getId_commento_padre().get());
                    commento_padre.ifPresent(c::setId_commento_padre);
                }

                if (commento.getId_games() != null && commento.getId_games().isPresent()) {
                    gamesRepository.findById(commento.getId_games().get()).ifPresent(c::setId_games);
                }

                if (commento.getId_blog() != null && commento.getId_blog().isPresent()) {
                    blogRepository.findById(commento.getId_blog().get()).ifPresent(c::setBlog);
                }

                commentiRepository.save(c);
                return ResponseEntity.ok("Commento aggiunto con successo");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Errore"+e.getMessage());
            }

    }
}
