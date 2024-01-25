import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { team } from 'src/app/models/typeStanding';
import { division } from 'src/app/models/typeTeams';
import { ApiService } from 'src/app/services/api.service';
import { GetApiServiceProfilo } from 'src/app/services/getApiProfile.service';
import { GetApiServiceTeams } from 'src/app/services/getApiTeams.service';

@Component({
  selector: 'app-squadre-page',
  templateUrl: './squadre-page.component.html',
  styleUrls: ['./squadre-page.component.css']
})
export class SquadrePageComponent implements OnInit {
  constructor(private activatedRoute: ActivatedRoute, private apiService: ApiService, private getApiProfile: GetApiServiceProfilo, private getApiTeams: GetApiServiceTeams) { }
  ngOnInit(): void {
    this.checkIfMobile(); //controllo se siamo in mobile o desktop
    // prendo i dati di tutte le squadre
    this.activatedRoute.data.subscribe(
      ({ ResolveTeams }) => {
        //se l'utente è loggato mostro i suoi preferiti
        if (localStorage.getItem('authToken')){
          this.functionGetAllFavouriteTeams();
        }else
          this.updateTeams(ResolveTeams, this.favouriteTeams);
      })
  }

  favouriteTeams!: team[];

  teams: division = {
    NorthWest: [],
    SouthWest: [],
    SoutHeast: [],
    Atlantic: [],
    Central: [],
    Pacific: [],
  };
  isMobile: boolean = false;

  // al cambio di viewport rendo true una variabile
  @HostListener('window:resize')
  onResize(): void {
    this.checkIfMobile();
  }
  checkIfMobile(): void {
    this.isMobile = window.innerWidth < 768; // Cambia questo valore in base alle tue esigenze
  }

  // per selezionare una conference o l'altra
  isConferenceSelected: boolean = true;
  functionChangeConferenceSelected() {
    this.isConferenceSelected = !this.isConferenceSelected;
  }

  /**
   * Aggiungo e rimuovo i preferiti di un utente se loggato
   * @param id : string --> l'id della squadra scelta
   */
  aggiungiRimuoviPreferitiTeams(id: string) {
    if (localStorage.getItem('authToken')) {
      this.apiService.AddRemoveFavouriteTeams(localStorage.getItem('authToken') as string, id + "").subscribe(
        () => { },
        (err) => {
          if (err.status >= 200 && err.status <= 299) {
            this.functionGetAllFavouriteTeams();
          }
          console.log(err);
        }
      );
    } else {
      console.log("devi accedere, token non valido");
    }
  }

  /**
   * ottengo la lista di tutte le squadre preferiti dell'utente
   */
  functionGetAllFavouriteTeams() {
    if (localStorage.getItem('authToken')) {
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
    } else {
      console.log("token non valido");
    }
  }

  /**
   * Aggiorno le squadre dopo averne aggiunta/rimossa qualcuna dalla lista preferiti per vedere l'elenco in tempo reale. 
   * Funzione invocata o nell'onInit o solo quando viene aggiunta/rimossoa una squadra
   * @param ResolveTeams : division
   * @param ResolveFavouriteTeams : team[]
   */
  updateTeams(ResolveTeams: division, ResolveFavouriteTeams: team[]): void {
    let lunghezzaMax: number = 10;
    if (localStorage.getItem('authToken')) {
      lunghezzaMax = ResolveFavouriteTeams.length;

      for (let i = 0; i < ResolveTeams.NorthWest.length; i++) {
        for (let j = 0; j < lunghezzaMax; j++) {
          if (ResolveTeams.NorthWest[i].id == ResolveFavouriteTeams[j].id) {
            ResolveTeams.NorthWest[i].favourite = true;
          }
        }
      }
      this.teams.NorthWest = ResolveTeams.NorthWest;
      for (let i = 0; i < ResolveTeams.SouthWest.length; i++) {
        for (let j = 0; j < lunghezzaMax; j++) {
          if (ResolveTeams.SouthWest[i].id == ResolveFavouriteTeams[j].id) {
            ResolveTeams.SouthWest[i].favourite = true;
          }
        }
      }
      this.teams.SouthWest = ResolveTeams.SouthWest;
      for (let i = 0; i < ResolveTeams.SoutHeast.length; i++) {
        for (let j = 0; j < lunghezzaMax; j++) {
          if (ResolveTeams.SoutHeast[i].id == ResolveFavouriteTeams[j].id) {
            ResolveTeams.SoutHeast[i].favourite = true;
          }
        }
      }
      this.teams.SoutHeast = ResolveTeams.SoutHeast;
      for (let i = 0; i < ResolveTeams.Atlantic.length; i++) {
        for (let j = 0; j < lunghezzaMax; j++) {
          if (ResolveTeams.Atlantic[i].id == ResolveFavouriteTeams[j].id) {
            ResolveTeams.Atlantic[i].favourite = true;
          }
        }
      }
      this.teams.Atlantic = ResolveTeams.Atlantic;
      for (let i = 0; i < ResolveTeams.Central.length; i++) {
        for (let j = 0; j < lunghezzaMax; j++) {
          if (ResolveTeams.Central[i].id == ResolveFavouriteTeams[j].id) {
            ResolveTeams.Central[i].favourite = true;
          }
        }
      }
      this.teams.Central = ResolveTeams.Central;
      for (let i = 0; i < ResolveTeams.Pacific.length; i++) {
        for (let j = 0; j < lunghezzaMax; j++) {
          if (ResolveTeams.Pacific[i].id == ResolveFavouriteTeams[j].id) {
            ResolveTeams.Pacific[i].favourite = true;
          }
        }
      }
      this.teams.Pacific = ResolveTeams.Pacific;
      this.favouriteTeams = ResolveFavouriteTeams;
    } else {
      this.teams.NorthWest = ResolveTeams.NorthWest;
      this.teams.SouthWest = ResolveTeams.SouthWest;
      this.teams.SoutHeast = ResolveTeams.SoutHeast;
      this.teams.Atlantic = ResolveTeams.Atlantic;
      this.teams.Central = ResolveTeams.Central;
      this.teams.Pacific = ResolveTeams.Pacific;
    }
  }
}