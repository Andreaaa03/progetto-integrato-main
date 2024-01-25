import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { detailArticle } from 'src/app/models/typeArticle';
import { commento } from 'src/app/models/typeComment';
import { otherUser, profilo } from 'src/app/models/typeProfilo';
import { team } from 'src/app/models/typeStanding';
import { division } from 'src/app/models/typeTeams';
import { ApiService } from 'src/app/services/api.service';
import { GetApiServiceProfilo } from 'src/app/services/getApiProfile.service';
import { GetApiServiceTeams } from 'src/app/services/getApiTeams.service';


@Component({
  selector: 'app-profilo-page',
  templateUrl: './profilo-page.component.html',
  styleUrls: ['./profilo-page.component.css']
})
export class ProfiloPageComponent implements OnInit {
  constructor(private activatedRoute: ActivatedRoute, private apiService: ApiService, private getApiProfile: GetApiServiceProfilo, private getApiTeams: GetApiServiceTeams) { }
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ ResolveTeams, ResolveFavouriteTeams, ResolveFavouriteArticle, ResolveCommentForUser, ResolveInfoUser, ResolveSeguiti }) => {
        this.updateTeams(ResolveTeams, ResolveFavouriteTeams);
        this.favouriteArticles = ResolveFavouriteArticle;
        this.commenti = ResolveCommentForUser;
        this.infoUser = ResolveInfoUser;
        this.seguiti = ResolveSeguiti;
        console.log(this.commenti);
      })
  }

  infoUser!: profilo;
  commenti!: commento[];
  seguiti!: otherUser[];
  stringFollowForCard: boolean = true;
  showCard: boolean = false;

  menuSelected: string = "interazioni";
  menuPreferitiSelected: string = "squadre";
  showTeams: boolean = false;
  ripetiArray: any[] = new Array(10).fill({});
  favouriteTeams!: team[];
  totalTeamWest: number = 0;
  totalTeamEast: number = 0;
  favouriteArticles!: detailArticle[];

  teams: division = {
    NorthWest: [],
    SouthWest: [],
    SoutHeast: [],
    Atlantic: [],
    Central: [],
    Pacific: [],
  };

  /**
   * Aggiorno le squadre dopo averne aggiunta/rimossa qualcuna dalla lista preferiti per vedere l'elenco in tempo reale. 
   * Funzione invocata o nell'onInit o solo quando viene aggiunta/rimossoa una squadra
   * @param ResolveTeams : division
   * @param ResolveFavouriteTeams : team[]
   */
  updateTeams(ResolveTeams: division, ResolveFavouriteTeams: team[]): void {
    this.totalTeamEast = 0;
    this.totalTeamWest = 0;
    for (let i = 0; i < ResolveTeams.NorthWest.length; i++) {
      for (let j = 0; j < ResolveFavouriteTeams.length; j++) {
        if (ResolveTeams.NorthWest[i].id == ResolveFavouriteTeams[j].id) {
          ResolveTeams.NorthWest[i].favourite = true;
          this.totalTeamWest++;
        }
      }
    }
    this.teams.NorthWest = ResolveTeams.NorthWest;
    for (let i = 0; i < ResolveTeams.SouthWest.length; i++) {
      for (let j = 0; j < ResolveFavouriteTeams.length; j++) {
        if (ResolveTeams.SouthWest[i].id == ResolveFavouriteTeams[j].id) {
          ResolveTeams.SouthWest[i].favourite = true;
          this.totalTeamWest++;
        }
      }
    }
    this.teams.SouthWest = ResolveTeams.SouthWest;
    for (let i = 0; i < ResolveTeams.SoutHeast.length; i++) {
      for (let j = 0; j < ResolveFavouriteTeams.length; j++) {
        if (ResolveTeams.SoutHeast[i].id == ResolveFavouriteTeams[j].id) {
          ResolveTeams.SoutHeast[i].favourite = true;
          this.totalTeamEast++;
        }
      }
    }
    this.teams.SoutHeast = ResolveTeams.SoutHeast;
    for (let i = 0; i < ResolveTeams.Atlantic.length; i++) {
      for (let j = 0; j < ResolveFavouriteTeams.length; j++) {
        if (ResolveTeams.Atlantic[i].id == ResolveFavouriteTeams[j].id) {
          ResolveTeams.Atlantic[i].favourite = true;
          this.totalTeamEast++;
        }
      }
    }
    this.teams.Atlantic = ResolveTeams.Atlantic;
    for (let i = 0; i < ResolveTeams.Central.length; i++) {
      for (let j = 0; j < ResolveFavouriteTeams.length; j++) {
        if (ResolveTeams.Central[i].id == ResolveFavouriteTeams[j].id) {
          ResolveTeams.Central[i].favourite = true;
          this.totalTeamEast++;
        }
      }
    }
    this.teams.Central = ResolveTeams.Central;
    for (let i = 0; i < ResolveTeams.Pacific.length; i++) {
      for (let j = 0; j < ResolveFavouriteTeams.length; j++) {
        if (ResolveTeams.Pacific[i].id == ResolveFavouriteTeams[j].id) {
          ResolveTeams.Pacific[i].favourite = true;
          this.totalTeamWest++;
        }
      }
    }
    this.teams.Pacific = ResolveTeams.Pacific;
    this.favouriteTeams = ResolveFavouriteTeams;
  }

  /**
   * in base alla parola che mi arriva la assegno ad una variabile che poi controllo per accedere ad un menu del profilo
   * @param word :string
   */
  functionChangeManu(word: string) {
    this.menuSelected = word;
  }
  /**
   * in base alla parola che mi arriva la assegno ad una variabile che poi controllo per accedere ad un menu dei preferiti
   * @param word : string
   */
  functionChangeManuPreferiti(word: string) {
    this.menuPreferitiSelected = word;
  }

  //per le squadre in mobile, ancora non implementata ma pronta
  isConferenceSelected: boolean = true;
  functionChangeConferenceSelected() {
    this.isConferenceSelected = !this.isConferenceSelected;
  }

  /**
   * Rimuove il token dalla sessione
   */
  removeToken() {
    localStorage.removeItem('authToken');
    window.location.replace('/home');
  }


  /**
   * Aggiungo e rimuovo le squadre preferite, se il token non è valido svuoto la sessione
   * @param id : string
   */
  aggiungiRimuoviPreferitiTeams(id: string) {
    if (localStorage.getItem('authToken')) {
      this.apiService.AddRemoveFavouriteTeams(localStorage.getItem('authToken') as string, id + "").subscribe(
        () => { },
        (err) => {
          if (err.status >= 200 && err.status <= 299) {
            this.getApiProfile.getSearchFavouriteTeams(localStorage.getItem('authToken') as string).subscribe(
              (team) => {
                this.favouriteTeams = team;
                this.getApiTeams.getSearchTeams().subscribe(
                  allTeam => {
                    this.updateTeams(allTeam, this.favouriteTeams);
                  }
                )
              }
            )
          }else
            console.log(err);
        }
      );
    } else {
      console.log("non sei loggato");
      this.removeToken();
    }
  }

  /**
   * Aggiungo e rimuovo gli articoli preferite, se il token non è valido svuoto la sessione
   * @param id : string
   */
  aggiungiRimuoviPreferitiArticles(id: string) {
    if (localStorage.getItem('authToken')) {
      this.apiService.AddRemoveFavouriteArticle(localStorage.getItem('authToken') as string, id + "").subscribe(
        () => { },
        (err) => {
          if (err.status >= 200 && err.status <= 299) {
            this.getApiProfile.getSearchFavouriteArticle(localStorage.getItem('authToken') as string).subscribe(
              (article) => {
                this.favouriteArticles = article;
              }
            )
          }else
            console.log(err);
        }
      );
    } else {
      console.log("token non valido");
      this.removeToken();
    }
  }

  /**
   * Aggiungo e rimuovo gli utenti dai miei seguiti, se il token non è valido svuoto la sessione
   * @param id 
   */
  aggiungiRimuoviUtenti(id: string){
    if(localStorage.getItem('authToken')){
      this.apiService.AddRemoveUser(localStorage.getItem('authToken') as string, id).subscribe(
        ()=>{},
        (err)=>{
          if (err.status >= 200 && err.status <= 299) {
            console.log("aggiornato");
          }else
            console.log(err);
        }
      )
    }else{
      console.log("token non valido");
      this.removeToken();
    }
  }

}
