import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class ApiService {
    constructor(private http: HttpClient) { }

    baseURL = "http://localhost:8080/";
    headers = new HttpHeaders({
        'Content-Type': 'application/json',
    });

    //classifica
    SearchStanding() {
        return this.http.get(this.baseURL + "standings/All");
    }

    //tutti i team
    SearchTeams() {
        return this.http.get(this.baseURL + "teams/All");
    }

    //statistiche singolo team, calendario singolo team, giocatori per team
    SearchSingleTeamStatistics(id: string) {
        return this.http.get(this.baseURL + "teams/statistics?id=" + id);
    }
    SearchSingleTeamCalendar(id: string) {
        return this.http.get(this.baseURL + "games/teamId?teamId=" + id);
    }
    SearchSingleTeamPlayer(id: string) {
        return this.http.get(this.baseURL + "player/teamId?id=" + id);
    }

    //partite, 
    SearchMatch(id: string) {
        return this.http.get(this.baseURL + "games/gameId?id=" + id);
    }
    //statistiche della singola partita
    SearchMatchStats(id: string) {
        return this.http.get(this.baseURL + "games/partitaStat?idPartita=" + id);
    }
    //partite in una singolo giorno
    SearchMatchDate(date: string) {
        return this.http.get(this.baseURL + "games/date?date=" + date);
    }
    //ultime 20 partite da una data
    SearchMatchDateLast20(date: string) {
        return this.http.get(this.baseURL + "games/Last20?date=" + date);
    }

    //tutti gli articoli
    SearchAllArticle() {
        return this.http.get(this.baseURL + "blog/simple");
    }
    //dettaglio singolo articolo
    SearchArticle(id: string) {
        return this.http.get(this.baseURL + "blog/completo?id=" + id);
    }

    //invio dati login
    SendLogin(email: string, password: string) {
        return this.http.post(this.baseURL + "user/signin", { email: email, passwd: password }, { headers: this.headers });
    }
    //invio dati registrati
    SendSignup(first_name: string, last_name: string, birthDate: string, email: string, passwd: string, numeroTelefono: string, username: string, sesso: string) {
        return this.http.post(this.baseURL + "user/signup", {
            first_name: first_name, last_name: last_name, birthDate: birthDate, email: email, passwd: passwd, numeroTelefono: numeroTelefono, username: username, sesso: sesso
        }, { headers: this.headers });
    }
    // info utente loggato
    SearchInfoUser(token:string){
        return this.http.post(this.baseURL + "user/profilo", { token: token}, { headers: this.headers });
    }

    //squadre preferite
    SearchFavouriteTeams(token: string) {
        return this.http.post(this.baseURL + "user/getTeamPreferiti", { token: token }, { headers: this.headers });
    }
    //articoli preferiti
    SearchFavouriteArticle(token: string) {
        return this.http.post(this.baseURL + "user/getArticoliPreferiti", { token: token }, { headers: this.headers });
    }
    //aggiungi/rimuovi squadre preferiti
    AddRemoveFavouriteTeams(token: string, idTeam: string) {
        return this.http.post(this.baseURL + "user/teamPreferito", { token: token, idTeam: idTeam }, { headers: this.headers });
    }
    //aggiungi/rimuovi articoli preferiti
    AddRemoveFavouriteArticle(token: string, idArticolo: string) {
        return this.http.post(this.baseURL + "user/articoloPreferito", { token: token, idArticolo: idArticolo }, { headers: this.headers });
    }

    // tutti i commenti per articolo
    SearchCommentForArticle(id: string) {
        return this.http.get(this.baseURL + "blog/commentiBlog?id=" + id);
    }
    // tutti i commenti per partita
    SearchCommentForGame(id: string) {
        return this.http.get(this.baseURL + "games/commentiPartita?id=" + id);
    }
    // tutti i commenti di un utente
    SearchCommentForUser(token: string) {
        return this.http.post(this.baseURL + "commenti/commentiUtente", { token: token }, { headers: this.headers });
    }
    // aggiungi commento in un articolo
    AddCommentArticle(token: string, id_blog: string, testo: string) {
        return this.http.post(this.baseURL + "commenti/add", { token: token, id_blog: id_blog, testo: testo }, { headers: this.headers });
    }
    // aggiungi commento in un tabellino
    AddCommentGame(token: string, id_games: string, testo: string) {
        return this.http.post(this.baseURL + "commenti/add", { token: token, id_games: id_games, testo: testo }, { headers: this.headers });
    }
    //rispondi ad un commento
    AddCommentResponse(token: string, id_commento_padre: string, testo: string) {
        return this.http.post(this.baseURL + "commenti/add", { token: token, id_commento_padre: id_commento_padre, testo: testo }, { headers: this.headers });
    }
    
    // aggiungi/rimuovi utenti
    AddRemoveUser(token:string, idUtente:string){
        return this.http.post(this.baseURL + "user/seguiUtente", { token: token, idUtente: idUtente }, { headers: this.headers });
    }
    // elenco follower
    Follower(token:string){
        return this.http.post(this.baseURL + "user/followers", { token: token }, { headers: this.headers });
    }
    // elendo seguiti
    Seguiti(token:string){
        return this.http.post(this.baseURL + "user/seguiti", { token: token }, { headers: this.headers });
    }
}