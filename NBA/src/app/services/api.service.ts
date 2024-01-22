import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class ApiService {
    constructor(private http: HttpClient) { }

    baseURL ="http://localhost:8080/";

    //classifica
    SearchStanding(){
        return this.http.get(this.baseURL + "standings/All");
    }
    
    //tutti i team
    SearchTeams(){
        return this.http.get(this.baseURL + "teams/All");
    }

    //statistiche singolo team, calendario singolo team, giocatori per team
    SearchSingleTeamStatistics(id:string){
        return this.http.get(this.baseURL + "teams/statistics?id="+ id);
    }
    SearchSingleTeamCalendar(id:string){
        return this.http.get(this.baseURL + "games/teamId?teamId="+ id);
    }
    SearchSingleTeamPlayer(id:string){
        return this.http.get(this.baseURL + "player/teamId?id="+ id);
    }

    SearchMatch(id:string){
        return this.http.get(this.baseURL + "games/gameId?id="+ id);
    }
    SearchMatchStats(id:string){
        return this.http.get(this.baseURL + "games/partitaStat?idPartita="+ id);
    }
    SearchMatchDate(date:string){
        return this.http.get(this.baseURL + "games/date?date="+ date);
    }
    SearchMatchDateLast20(date:string){
        return this.http.get(this.baseURL + "games/Last20?date="+ date);
    }
    
    SendLogin(email:string, password:string){
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });
        return this.http.post(this.baseURL + "user/signin", { email: email, passwd: password }, {headers});
    }
}