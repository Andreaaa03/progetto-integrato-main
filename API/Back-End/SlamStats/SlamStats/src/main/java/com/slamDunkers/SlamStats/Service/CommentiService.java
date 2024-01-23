package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Entity.Utente;
import com.slamDunkers.SlamStats.Payload.Request.CommentiRequest;
import com.slamDunkers.SlamStats.Payload.Request.TokenRequest;
import com.slamDunkers.SlamStats.Payload.Response.CommentiResponse;
import com.slamDunkers.SlamStats.Payload.Response.ToResponse;
import com.slamDunkers.SlamStats.Repository.BlogRepository;
import com.slamDunkers.SlamStats.Repository.CommentiRepository;
import com.slamDunkers.SlamStats.Repository.GamesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CommentiService {
    public final CommentiRepository commentiRepository;
    public final UtenteService utenteService;
    public final GamesRepository gamesRepository;
    public final BlogRepository blogRepository;
    public final ToResponse toResponse;
    public CommentiService(CommentiRepository commentiRepository, UtenteService utenteService, GamesRepository gamesRepository, BlogRepository blogRepository, ToResponse toResponse) {
        this.commentiRepository = commentiRepository;
        this.utenteService = utenteService;
        this.gamesRepository = gamesRepository;
        this.blogRepository = blogRepository;
        this.toResponse = toResponse;
    }



/**
 * This method allows a user to comment on a blog or a game.
 *
 * @param commento The CommentiRequest object containing the details of the comment to be added.
 * The CommentiRequest object should contain the token of the user making the comment, the text of the comment,
 * and optionally the ID of the parent comment, the ID of the game, or the ID of the blog.
 * @return A ResponseEntity object containing a success message if the comment was added successfully.
 */
public ResponseEntity<String> commenta(CommentiRequest commento) {
    Utente u = utenteService.getUtente(commento.getToken());
    Commenti c = new Commenti();
    c.setTesto(commento.getTesto());
    c.setIdUtente(u);
    if (commento.getId_commento_padre() != null) {
        commentiRepository.findById(commento.getId_commento_padre().get()).ifPresent(commentoPadre ->{
            c.setIdCommentoPadre(commentoPadre);
            c.setBlog(commentoPadre.getBlog());
            c.setIdGames(commentoPadre.getIdGames());
        });
        commentiRepository.save(c);
        return ResponseEntity.ok("Commento aggiunto con successo");
    }
    if (commento.getId_games() != null) {
        gamesRepository.findById(commento.getId_games().get()).ifPresent(c::setIdGames);
    }
    if (commento.getId_blog() != null) {
        blogRepository.findById(commento.getId_blog().get()).ifPresent(c::setBlog);
    }
    commentiRepository.save(c);
    return ResponseEntity.ok("Commento aggiunto con successo");
}

/**
 * This method retrieves all comments made by a specific user.
 *
 * @param token The token of the user for which comments are to be retrieved.
 * @return A list of CommentiResponse objects, each representing a comment made by the specified user.
 * If no comments are found for the specified user, the method returns null.
 */
public List<CommentiResponse> commentiUtente(TokenRequest token) {
    Utente u = utenteService.getUtente(token.getToken());
    List<Optional<Commenti>> commenti = commentiRepository.findAllByIdUtente(u);
    if(commenti.isEmpty()) return null;
    List<CommentiResponse> commentiResponse = new ArrayList<>();
    for(Optional<Commenti> c : commenti){
        commentiResponse.add(toResponse.toCommentiResponse(c.get()));
    }
    return commentiResponse;
}

/**
 * This method retrieves all comments related to a specific blog.
 *
 * @param id_blog The ID of the blog for which comments are to be retrieved.
 * @return A list of CommentiResponse objects, each representing a comment related to the specified blog.
 * If no comments are found for the specified blog, the method returns null.
 */
public List<CommentiResponse> commentiBlog(Integer id_blog) {
    List<Optional<Commenti>> commenti = commentiRepository.findAllByBlog(blogRepository.findById(id_blog).get());
    if(commenti.isEmpty()) return null;
    List<CommentiResponse> commentiResponse = new ArrayList<>();
    for(Optional<Commenti> c : commenti){
        commentiResponse.add(toResponse.toCommentiResponse(c.get()));
    }
    return commentiResponse;
}

/**
 * This method retrieves all comments related to a specific game.
 *
 * @param idPartita The ID of the game for which comments are to be retrieved.
 * @return A list of CommentiResponse objects, each representing a comment related to the specified game.
 * If no comments are found for the specified game, the method returns null.
 */
public List<CommentiResponse> commentiPartita(Integer idPartita) {
    List<Optional<Commenti>> commenti = commentiRepository.findAllByIdGames(gamesRepository.findById(idPartita).get());
    if(commenti.isEmpty()) return null;
    List<CommentiResponse> commentiResponse = new ArrayList<>();
    for(Optional<Commenti> c : commenti){
        commentiResponse.add(toResponse.toCommentiResponse(c.get()));
    }
    return commentiResponse;
}
}

