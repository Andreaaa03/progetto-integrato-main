package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.*;
import com.slamDunkers.SlamStats.Payload.Request.*;
import com.slamDunkers.SlamStats.Payload.Response.*;
import com.slamDunkers.SlamStats.Repository.*;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;

    private final TokenService tokenService;
    private final Utente_TeamRepository utenteTeamRepository;
    private final TeamsRepository teamsRepository;
    private final BlogRepository blogRepository;
    private final BlogService blogService;
    private final ToResponse toResponse;
    private final SeguitoRepository seguitoRepository;

    public UtenteService(UtenteRepository utenteRepository, TokenService tokenService, Utente_TeamRepository utenteTeamRepository, TeamsRepository teamsRepository, BlogRepository blogRepository, BlogService blogService, ToResponse toResponse, SeguitoRepository seguitoRepository) {
        this.utenteRepository = utenteRepository;
        this.tokenService = tokenService;
        this.utenteTeamRepository = utenteTeamRepository;
        this.teamsRepository = teamsRepository;
        this.blogRepository = blogRepository;
        this.blogService = blogService;
        this.toResponse = toResponse;
        this.seguitoRepository = seguitoRepository;
    }


    /**
     * This method is used to save a new user to the database.
     * It first checks if the email, username, and phone number provided in the request are already present in the database.
     * If any of them are present, it returns a response indicating that the respective field is already present.
     * If none of them are present, it saves the new user to the database and returns a success message.
     *
     * @param request SignupRequest object containing the details of the user to be saved.
     * @return ResponseEntity with a message indicating the result of the operation and HTTP status.
     */
    public ResponseEntity<String> save(SignupRequest request) {
        Optional<Object> u = utenteRepository.findByEmail(request.getEmail());
        Optional<Object> u2 = utenteRepository.findByUsername(request.getUsername());
        Optional<Object> u3 = utenteRepository.findByNumeroTelefono(request.getNumeroTelefono());
        if (u.isPresent()) {
            return new ResponseEntity<>("Email già presente", HttpStatus.OK);
        } else if (u2.isPresent()) {
            return new ResponseEntity<>("Username già presente", HttpStatus.OK);
        } else if (u3.isPresent()) {
            return new ResponseEntity<>("Numero di telefono già presente", HttpStatus.OK);
        } else {
            utenteRepository.save(fromRequestToEntity(request));
            return new ResponseEntity<>("Utente creato con successo", HttpStatus.OK);
        }
    }

    /**
     * This method converts a SignupRequest object into a Utente object.
     *
     * @param request SignupRequest object containing the details of the user to be saved.
     * @return Utente object with the details from the SignupRequest object.
     * The method creates a new Utente object and sets its fields with the details from the SignupRequest object.
     * The password from the SignupRequest object is hashed using SHA3-256 before being set in the Utente object.
     * The method checks if the email from the SignupRequest object contains "@edu.itspiemonte.it".
     * If it does, the method sets the role of the Utente object to "blogger".
     * If it does not, the method sets the role of the Utente object to "user".
     * The method then returns the Utente object.
     */
    private Utente fromRequestToEntity(SignupRequest request) {
        Utente u = new Utente();
        u.setFirstName(request.getFirst_name());
        u.setLastName(request.getLast_name());
        u.setBirthDate(request.getBirthDate());
        u.setUsername(request.getUsername());
        u.setEmail(request.getEmail());
        u.setSesso(request.getSesso());
        u.setPasswd(new DigestUtils("SHA3-256").digestAsHex(request.getPasswd()));
        u.setNumeroTelefono(request.getNumeroTelefono());
        if (request.getEmail().contains("@edu.itspiemonte.it")) {
            u.setRoleId(new Roles(3, "blogger"));
        } else {
            u.setRoleId(new Roles(2, "user"));
        }
        return u;
    }


    /**
     * This method allows a user to log in to their account.
     *
     * @param request SinginRequest object containing the email and password of the user.
     * @param session HttpSession object representing the current session.
     * @return ResponseEntity with an AuthResponse object if the login is successful, or a message indicating an error and HTTP status if the login is unsuccessful.
     * The method first calls the findByEmailOrUsernameOrNumeroTelefonoAndPasswd() method of the UtenteRepository, passing the email and hashed password from the request as parameters.
     * This method checks if a user with the provided email and password exists in the database.
     * If the user exists, the method creates a new token for the user by calling the createToken() method of the TokenService, passing the user's ID and role as parameters.
     * The method then creates a new AuthResponse object, sets its fields with the user's details and the token, sets the user's role in the session, and returns a ResponseEntity with the AuthResponse object and HTTP status OK.
     * If the user does not exist, the method returns a ResponseEntity with a message indicating that the username or password is incorrect and HTTP status OK.
     */
    public ResponseEntity<AuthResponse> accesso(SinginRequest request, HttpSession session) {
        Optional<Utente> u = utenteRepository.findByEmailOrUsernameOrNumeroTelefonoAndPasswd(request.getEmail(), new DigestUtils("SHA3-256").digestAsHex(request.getPasswd()));
        if (u.isPresent()) {
            String token = tokenService.createToken(u.get().getId(), u.get().getRoleId().getRole());
            AuthResponse authenticatedUser = new AuthResponse(u.get().getId(), u.get().getFirstName(), u.get().getRoleId().getRole(), token);
            session.setAttribute("userRole", u.get().getRoleId().getRole());
            return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity("Username o password errati", HttpStatus.OK);
        }
    }

    /**
     * This method allows a user to add a preferred team to their profile.
     *
     * @param request UtenteTeamRequest object containing the token of the user and the ID of the team to be added.
     * @return ResponseEntity with a message indicating the result of the operation and HTTP status.
     * The method first calls the getUtente() method, passing the token from the request as a parameter.
     * This method retrieves the user associated with the token.
     * The method then calls the findById() method of the TeamsRepository, passing the ID of the team as a parameter.
     * This method retrieves the team from the database.
     * The method then checks if the user has already added 5 teams to their preferred teams by calling the findByIdUtente() method of the UtenteTeamRepository and comparing the result with 5.
     * If the user has already added 5 teams, the method returns a response indicating that the user has reached the limit of 5 preferred teams.
     * If the user has not added 5 teams, the method calls the SorRteamPreferiti() method, passing the user and the team as parameters.
     * This method checks if the user has already added the team to their preferred teams.
     * If the user has already added the team, the method deletes the UtentePreferiti object from the database and returns a response indicating that the team has been successfully removed.
     * If the user has not added the team, the method creates a new UtentePreferiti object, sets the user and the team, saves the UtentePreferiti object to the database, and returns a response indicating that the team has been successfully added.
     */
    public ResponseEntity<String> teamPreferito(UtenteTeamRequest request) {
        Utente u = getUtente(request.getToken());
        Optional<Teams> t = teamsRepository.findById(request.getIdTeam());


        return SorRteamPreferiti(u, t.get());
    }

    /**
     * This method allows a user to add or remove a preferred team.
     *
     * @param u Utente object representing the user.
     * @param t Teams object representing the team.
     * @return ResponseEntity with a message indicating the result of the operation and HTTP status.
     * The method first calls the findByIdUtenteAndIdTeam() method of the UtenteTeamRepository, passing the user and the team as parameters.
     * This method checks if the user has already added the team to their preferred teams.
     * If the user has already added the team, the method deletes the UtentePreferiti object from the database and returns a response indicating that the team has been successfully removed.
     * If the user has not added the team, the method creates a new UtentePreferiti object, sets the user and the team, saves the UtentePreferiti object to the database, and returns a response indicating that the team has been successfully added.
     */
    public ResponseEntity<String> SorRteamPreferiti(Utente u, Teams t) {
        Optional<UtentePreferiti> ut = utenteTeamRepository.findByIdUtenteAndIdTeam(u, t);
        int ut10 = utenteTeamRepository.findByIdUtente(u.getId(), t.getLeague().getConferenceId().getId());


        if (ut.isPresent()) {
            utenteTeamRepository.delete(ut.get());
            return new ResponseEntity<>("Team rimosso con successo", HttpStatus.OK);
        }
        if (ut10 >= 5) {
            return new ResponseEntity<>("Hai già raggiunto il limite di 5 squadre preferite per league: "+t.getLeague().getConferenceId().getName() , HttpStatus.OK);
        }


        else {
            UtentePreferiti utente_team = new UtentePreferiti();
            utente_team.setIdUtente(u);
            utente_team.setIdTeam(t);
            utenteTeamRepository.save(utente_team);
            return new ResponseEntity<>("Team aggiunto con successo", HttpStatus.OK);
        }
    }

    /**
     * This method allows a user to add or remove a preferred article.
     *
     * @param request UtenteArticoloRequest object containing the token of the user and the ID of the article to be added/removed.
     * @return ResponseEntity with a message indicating the result of the operation and HTTP status.
     * The method first calls the getUtente() method, passing the token from the request as a parameter.
     * This method retrieves the user associated with the token.
     * The method then calls the findById() method of the BlogRepository, passing the ID of the article as a parameter.
     * This method retrieves the article from the database.
     * If the article is not found, the method returns a response indicating that the article was not found.
     * The method then calls the SorRarticoloPreferiti() method, passing the user and the article as parameters.
     * This method checks if the user has already added the article to their preferred articles.
     * If the user has already added the article, the method deletes the UtentePreferiti object from the database and returns a response indicating that the article has been successfully removed.
     * If the user has not added the article, the method creates a new UtentePreferiti object, sets the user and the article, saves the UtentePreferiti object to the database, and returns a response indicating that the article has been successfully added.
     */
    public ResponseEntity<String> articoloPreferito(UtenteArticoloRequest request) {
        Utente u = getUtente(request.getToken());

        Blog b = blogRepository.findById(request.getIdArticolo());
        if (b == null) {
            return new ResponseEntity<>("Articolo non trovato", HttpStatus.OK);
        }
        return SorRarticoloPreferiti(u, b);

    }

    /**
     * This method allows a user to add or remove a preferred article.
     *
     * @param u Utente object representing the user.
     * @param b Blog object representing the article.
     * @return ResponseEntity with a message indicating the result of the operation and HTTP status.
     * The method first calls the findByIdUtenteAndIdArticolo() method of the UtenteTeamRepository, passing the user and the article as parameters.
     * This method checks if the user has already added the article to their preferred articles.
     * If the user has already added the article, the method deletes the UtentePreferiti object from the database and returns a response indicating that the article has been successfully removed.
     * If the user has not added the article, the method creates a new UtentePreferiti object, sets the user and the article, saves the UtentePreferiti object to the database, and returns a response indicating that the article has been successfully added.
     */
    public ResponseEntity<String> SorRarticoloPreferiti(Utente u, Blog b) {
        Optional<UtentePreferiti> ub = utenteTeamRepository.findByIdUtenteAndIdArticolo(u, b);

        if (ub.isPresent()) {
            utenteTeamRepository.delete(ub.get());
            return new ResponseEntity<>("Articolo rimosso con successo", HttpStatus.OK);
        } else {
            UtentePreferiti utente_team = new UtentePreferiti();
            utente_team.setIdUtente(u);
            utente_team.setIdArticolo(b);
            utenteTeamRepository.save(utente_team);
            return new ResponseEntity<>("Articolo aggiunto con successo", HttpStatus.OK);
        }
    }

    /**
     * This method retrieves the preferred articles of a user.
     *
     * @param request TokenRequest object containing the token of the user.
     * @return A list of BlogCompleto objects representing the preferred articles of the user.
     * The method first calls the getUtente() method, passing the token from the request as a parameter.
     * This method retrieves the user associated with the token.
     * The method then calls the findArticoliPreferiti() method of the UtenteTeamRepository, passing the user's ID as a parameter.
     * This method retrieves the preferred articles of the user from the database.
     * If the user has no preferred articles, the method returns null.
     * The method then creates a new list of BlogCompleto objects and for each preferred article, it calls the getBlogCompleto() method of the BlogService object, passing the article's ID as a parameter.
     * This method retrieves the complete details of the article.
     * The method then adds the BlogCompleto object to the list and returns the list.
     */
    public List<BlogCompleto> getArticoliPreferiti(TokenRequest request) {
        Utente u = getUtente(request.getToken());

        List<UtentePreferiti> ub = utenteTeamRepository.findArticoliPreferiti(u.getId());
        if (ub == null) {
            return null;
        }
        List<BlogCompleto> blogCompleto = new ArrayList<>();
        for (UtentePreferiti utentePreferiti : ub) {
            blogCompleto.add(blogService.getBlogCompleto(utentePreferiti.getIdArticolo().getId()));
        }
        return blogCompleto;

    }


    /**
     * This method retrieves the preferred teams of a user.
     *
     * @param request TokenRequest object containing the token of the user.
     * @return A list of TeamsResponse objects representing the preferred teams of the user.
     * The method first calls the getUtente() method, passing the token from the request as a parameter.
     * This method retrieves the user associated with the token.
     * The method then calls the findTeamPreferiti() method of the UtenteTeamRepository, passing the user's ID as a parameter.
     * This method retrieves the preferred teams of the user from the database.
     * If the user has no preferred teams, the method returns null.
     * The method then creates a new list of TeamsResponse objects and for each preferred team, it calls the toTeamsResponse() method of the ToResponse object, passing the team as a parameter.
     * This method converts the team to a TeamsResponse object.
     * The method then adds the TeamsResponse object to the list and returns the list.
     */
    public List<TeamsResponse> getTeamPreferiti(TokenRequest request) {
        Utente u = getUtente(request.getToken());

        List<UtentePreferiti> ut = utenteTeamRepository.findTeamPreferiti(u.getId());
        if (ut == null) {
            return null;
        }
        List<TeamsResponse> teams = new ArrayList<>();
        for (UtentePreferiti utentePreferiti : ut) {
            teams.add(toResponse.toTeamsResponse(utentePreferiti.getIdTeam()));
        }
        return teams;
    }

    /**
     * This method allows a user to follow or unfollow another user.
     *
     * @param request SeguiRequest object containing the token of the user and the ID of the user to be followed/unfollowed.
     * @return ResponseEntity with a message indicating the result of the operation and HTTP status.
     * The method first calls the getUtente() method, passing the token from the request as a parameter.
     * This method retrieves the user associated with the token.
     * The method then calls the findById() method of the UtenteRepository, passing the ID of the user to be followed/unfollowed as a parameter.
     * This method retrieves the user to be followed/unfollowed from the database.
     * If the user to be followed/unfollowed is not found, the method returns a response indicating that the user was not found.
     * The method then calls the findSeguito() method of the SeguitoRepository, passing the IDs of the two users as parameters.
     * This method checks if the user is already following the other user.
     * If the user is not already following the other user, the method creates a new Seguiti object, sets the follower and the followed users, saves the Seguiti object to the database, and returns a response indicating that the user has successfully followed the other user.
     * If the user is already following the other user, the method deletes the Seguiti object from the database and returns a response indicating that the user has successfully unfollowed the other user.
     */
    public ResponseEntity<String> segui(SeguiRequest request) {
        Utente u = getUtente(request.getToken());
        Optional<Utente> u2 = utenteRepository.findById(request.getIdUtente());
        if (!u2.isPresent()) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.OK);
        }
        Optional<Seguiti> s = seguitoRepository.findSeguito(u.getId(), u2.get().getId());
        if (s.isEmpty()) {
            Seguiti seguito = new Seguiti();
            seguito.setSeguito(u2.get());
            seguito.setSeguace(u);
            seguitoRepository.save(seguito);
            return new ResponseEntity<>("Utente seguito con successo", HttpStatus.OK);
        } else {
            seguitoRepository.delete(s.get());
            return new ResponseEntity<>("Utente rimosso con successo", HttpStatus.OK);
        }
    }

    /**
     * This method retrieves the profile of a user based on a provided token.
     *
     * @param request TokenRequest object containing the token of the user.
     * @return An UtenteResponse object representing the user's profile.
     * The method first calls the getUtente() method, passing the token from the request as a parameter.
     * This method retrieves the user associated with the token.
     * The method then calls the profiloUtente() method, passing the user's ID as a parameter.
     * This method retrieves the user's profile and returns it as an UtenteResponse object.
     */
    public UtenteResponse profilo(TokenRequest request) {
        Utente u = getUtente(request.getToken());
        return profiloUtente(u.getId());
    }

    /**
     * This method retrieves the profile of a user based on a provided user ID.
     *
     * @param id An Integer representing the ID of the user.
     * @return An UtenteResponse object representing the user's profile.
     * The method first calls the findById() method of the UtenteRepository, passing the user's ID as a parameter.
     * This method retrieves the user from the database.
     * If the user is not found, the method throws a RuntimeException.
     * The method then creates a new UtenteResponse object and sets its fields with the user's details.
     * The method also retrieves the user's preferred teams by calling the findTeamPreferiti() method of the UtenteTeamRepository and the toTeamsResponse() method of the ToResponse object.
     * The method then returns the UtenteResponse object.
     */
    public UtenteResponse profiloUtente(Integer id) {
        Optional<Utente> u = utenteRepository.findById(id);
        if (!u.isPresent()) {
            throw new RuntimeException("Utente non trovato");
        }

        UtenteResponse ur = new UtenteResponse();

        ur.setId(u.get().getId());
        ur.setEmail(u.get().getEmail());
        ur.setNumeroTelefono(u.get().getNumeroTelefono());
        ur.setUsername(u.get().getUsername());
        ur.setSesso(u.get().getSesso());
        ur.setFollower(u.get().getFollower());
        ur.setDataIscrizione(u.get().getDataIscrizione());
        LocalDate dataNascita = u.get().getBirthDate();

        int annoNascita = dataNascita.getYear();
        int meseNascita = dataNascita.getMonthValue();
        int giornoNascita = dataNascita.getDayOfMonth();

        LocalDate oggi = LocalDate.now();
        int eta = oggi.getYear() - annoNascita;
        if (oggi.getMonthValue() < meseNascita || (oggi.getMonthValue() == meseNascita && oggi.getDayOfMonth() < giornoNascita)) {
            eta--; 
        }
        ur.setEta(eta);
        ur.setRuolo(u.get().getRoleId().getRole());
        List<UtentePreferiti> lut = utenteTeamRepository.findTeamPreferiti(u.get().getId());
        List<TeamsResponse> teams = new ArrayList<>();
        for (UtentePreferiti utentePreferiti : lut) {
            teams.add(toResponse.toTeamsResponse(utentePreferiti.getIdTeam()));
        }

        return ur;
    }

    /**
     * This method retrieves a user based on a provided token.
     *
     * @param request A string representing the token of the user.
     * @return An Utente object representing the user.
     * The method first calls the getUserIdFromToken() method of the TokenService, passing the request as a parameter.
     * This method decodes the token and retrieves the user's ID.
     * The method then calls the findById() method of the UtenteRepository, passing the user's ID as a parameter.
     * This method retrieves the user from the database.
     * If the user is not found, the method throws a RuntimeException.
     */
    public Utente getUtente(String request) {
        Integer utente = tokenService.getUserIdFromToken(request).getId();
        Optional<Utente> u = utenteRepository.findById(utente);
        if (!u.isPresent()) {
            throw new RuntimeException("Utente non trovato");
        }
        return u.get();
    }


    /**
     * This method retrieves the preferred teams and articles of a user.
     *
     * @param request TokenRequest object containing the token of the user.
     * @return A TeamArticoliPreferitiResponse object containing the preferred teams and articles of the user.
     * The method calls the getTeamPreferiti() and getArticoliPreferiti() methods, passing the request as a parameter.
     * These methods retrieve the preferred teams and articles of the user from the database.
     * The method then sets these values in a new TeamArticoliPreferitiResponse object and returns it.
     */
    public TeamArticoliPreferitiResponse teamArticoliPreferiti(TokenRequest request) {
        TeamArticoliPreferitiResponse tapr = new TeamArticoliPreferitiResponse();
        tapr.setTeams(getTeamPreferiti(request));
        tapr.setArticoli(getArticoliPreferiti(request));
        return tapr;
    }

    public ResponseEntity<String> addAmico(SeguiRequest request) {
        Utente u = getUtente(request.getToken());

        Optional<Utente> u2 = utenteRepository.findById(request.getIdUtente());

        if (!u2.isPresent()) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.OK);
        }

        Optional<Seguiti> s = seguitoRepository.findSeguito(u.getId(), u2.get().getId());
        if (s.isEmpty()) {
            Seguiti seguito = new Seguiti();
            seguito.setSeguito(u2.get());
            seguito.setSeguace(u);
            seguito.setAmico(true);
            seguitoRepository.save(seguito);
            return new ResponseEntity<>(u2.get().getUsername() +" e diventato tuo amico ", HttpStatus.OK);
        } else {
            seguitoRepository.delete(s.get());
            return new ResponseEntity<>("Utente rimosso con successo", HttpStatus.OK);
        }
    }

    public List<AmicoResponse> seguiti(TokenRequest request) {
        Utente u = getUtente(request.getToken());
        List<Object[]> seguiti = seguitoRepository.chiSeguo(u.getId());
        if (seguiti == null) return null;

        List<AmicoResponse> utenti = new ArrayList<>();
        for (Object[] seguito : seguiti) {

            utenti.add(toResponse.toAmicoresponse(seguito));
        }

        return  utenti;
    }

    public List<AmicoResponse> getAmici(TokenRequest request) {
        Utente u = getUtente(request.getToken());

        List<Object[]> seguiti = seguitoRepository.chiSeguo(u.getId());
        if (seguiti == null) return null;


        List<AmicoResponse> utenti = new ArrayList<>();
        for (Object[] seguito : seguiti) {
            if (!(Boolean) seguito[2]){
                continue;
            }
            utenti.add(toResponse.toAmicoresponse(seguito));
        }

        return  utenti;

    }


    public List<AmicoResponse> followers(TokenRequest request) {
        Utente u = getUtente(request.getToken());
        List<Object[]> seguiti = seguitoRepository.daChiVengoSeguito(u.getId());
        if (seguiti == null) return null;

        List<AmicoResponse> utenti = new ArrayList<>();
        for (Object[] seguito : seguiti) {
            utenti.add(toResponse.toAmicoresponse(seguito));
        }

        return utenti;
    }
}
