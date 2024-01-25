import { Component, HostListener, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Classifica, SingleTeamStanding, StandingShow } from 'src/app/models/typeStanding';
import { ApiService } from 'src/app/services/api.service';
import { GetApiServiceProfilo } from 'src/app/services/getApiProfile.service';
import { GetApiServiceStanding } from 'src/app/services/getApiStending.service';

@Component({
  selector: 'app-classifica',
  templateUrl: './classifica.component.html',
  styleUrls: ['./classifica.component.css']
})
export class ClassificaComponent implements OnInit {
  @Input() isParziale!: boolean;

  constructor(private activatedRoute: ActivatedRoute, private apiService: ApiService, private getApiStanding: GetApiServiceStanding) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ ResolveStanding }) => {
        //aggiorno la classifica
        this.updateStanding(ResolveStanding);
      })
    this.cambiaTesto();
    // nella home mostro 5 righe, in classifica 15(tutte)
    if (this.isParziale === true)
      this.countForStanding = 5;
    else
      this.countForStanding = 15;
  }

  // mi salvo le 2 classifiche
  standings: Classifica = {
    allStanding: {
      eastConference: [],
      westConference: [],
    },
    favouriteStandings: {
      eastConference: [],
      westConference: [],
    }
  }
  // gli assegno la classifica che voglio mostrare
  standingToShow: StandingShow = {
    eastConference: [],
    westConference: [],
  }
  //righe da mostrare della classifica
  countForStanding: number = 5;

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.cambiaTesto();
  }

  // assegno ai vari array di oggetti le squadre aggiornate, principalmente per i preferiti
  /**
   * 
   * @param ResolveStanding : Classifica -->
   */
  updateStanding(ResolveStanding:Classifica){
    this.standings.allStanding.eastConference = this.bubbleSort(ResolveStanding.allStanding.eastConference, 'winPercentage', false);
    this.standings.allStanding.westConference = this.bubbleSort(ResolveStanding.allStanding.westConference, 'winPercentage', false);
    this.standings.favouriteStandings.eastConference = this.bubbleSort(ResolveStanding.favouriteStandings.eastConference, 'winPercentage', false);
    this.standings.favouriteStandings.westConference = this.bubbleSort(ResolveStanding.favouriteStandings.westConference, 'winPercentage', false);
    this.standingToShow.eastConference = this.standings.allStanding.eastConference;
    this.standingToShow.westConference = this.standings.allStanding.westConference;
  }

  /**
   * Aggiungo e rimuovo le squadre preferite, se il token non è valido svuoto la sessione
   * @param id : string
   */
  aggiungiRimuoviPreferitiTeams(id: string) {
    if(localStorage.getItem('authToken')){
    this.apiService.AddRemoveFavouriteTeams(localStorage.getItem('authToken') as string, id + "").subscribe(
      () => { },
      (err) => {
        if (err.status >= 200 && err.status <= 299) {
          this.getApiStanding.getSearchStanding().subscribe(
            (team) => {
              this.ToggleCheckedForReset();
              this.percentageStanding = true;
              this.nameStanding = false;
              this.crescente = false;
              this.winStanding = false;
              this.lossStanding = false;
              this.favouriteStanding = false;
              this.functionChangeFilterStanding(this.percentageStanding, this.nameStanding, this.winStanding, this.lossStanding, this.crescente);
              this.updateStanding(team);
            }
          )
        }
        console.log(err);
      }
    );
    }else{
      console.log("token non valido");
    }
  }

  // variabili per filtri
  percentageStanding: boolean = true;
  favouriteStanding: boolean = false;
  nameStanding: boolean = false;
  winStanding: boolean = false;
  lossStanding: boolean = false;
  crescente: boolean = false;

  /**
   * Funzione per creare filtri nella classifica, ogni variabile attiva disabilità le altre e mostro una classifica specificata ordinata in ordine crescente o decrescente
   * @param percentageStanding : boolean
   * @param nameStanding : boolean
   * @param winStanding : boolean
   * @param lossStanding : boolean
   * @param crescente : boolean
   */
  functionChangeFilterStanding(percentageStanding: boolean, nameStanding: boolean, winStanding: boolean, lossStanding: boolean, crescente: boolean) {
    if (this.favouriteStanding === false) {
      if (percentageStanding === true) {
        if (crescente === true) {
          this.standingToShow.eastConference = this.bubbleSortReverse(this.standings.allStanding.eastConference, 'winPercentage', false);
          this.standingToShow.westConference = this.bubbleSortReverse(this.standings.allStanding.westConference, 'winPercentage', false);
        } else if (crescente === false) {
          this.standingToShow.eastConference = this.bubbleSort(this.standings.allStanding.eastConference, 'winPercentage', false);
          this.standingToShow.westConference = this.bubbleSort(this.standings.allStanding.westConference, 'winPercentage', false);
        }
      }

      //nome
      if (nameStanding === true) {
        if (crescente === true) {
          this.standingToShow.eastConference = this.bubbleSortReverse(this.standings.allStanding.eastConference, 'team.teamName', true);
          this.standingToShow.westConference = this.bubbleSortReverse(this.standings.allStanding.westConference, 'team.teamName', true);
        } else if (crescente === false) {
          this.standingToShow.eastConference = this.bubbleSort(this.standings.allStanding.eastConference, 'team.teamName', true);
          this.standingToShow.westConference = this.bubbleSort(this.standings.allStanding.westConference, 'team.teamName', true);
        }
      }


      //win
      if (winStanding === true) {
        if (crescente === true) {
          this.standingToShow.eastConference = this.bubbleSortReverse(this.standings.allStanding.eastConference, 'totalWin', false);
          this.standingToShow.westConference = this.bubbleSortReverse(this.standings.allStanding.westConference, 'totalWin', false);
        } else if (crescente === false) {
          this.standingToShow.eastConference = this.bubbleSort(this.standings.allStanding.eastConference, 'totalWin', false);
          this.standingToShow.westConference = this.bubbleSort(this.standings.allStanding.westConference, 'totalWin', false);
        }
      }

      //loss
      if (lossStanding === true) {
        if (crescente === true) {
          this.standingToShow.eastConference = this.bubbleSortReverse(this.standings.allStanding.eastConference, 'totalLoss', false);
          this.standingToShow.westConference = this.bubbleSortReverse(this.standings.allStanding.westConference, 'totalLoss', false);
        } else if (crescente === false) {
          this.standingToShow.eastConference = this.bubbleSort(this.standings.allStanding.eastConference, 'totalLoss', false);
          this.standingToShow.westConference = this.bubbleSort(this.standings.allStanding.westConference, 'totalLoss', false);
        }
      }
    } else {
      if (percentageStanding === true) {
        if (crescente === true) {
          this.standingToShow.eastConference = this.bubbleSortReverse(this.standings.favouriteStandings.eastConference, 'winPercentage', false);
          this.standingToShow.westConference = this.bubbleSortReverse(this.standings.favouriteStandings.westConference, 'winPercentage', false);
        } else if (crescente === false) {
          this.standingToShow.eastConference = this.bubbleSort(this.standings.favouriteStandings.eastConference, 'winPercentage', false);
          this.standingToShow.westConference = this.bubbleSort(this.standings.favouriteStandings.westConference, 'winPercentage', false);
        }
      }

      //nome
      if (nameStanding === true) {
        if (crescente === true) {
          this.standingToShow.eastConference = this.bubbleSortReverse(this.standings.favouriteStandings.eastConference, 'team.teamName', true);
          this.standingToShow.westConference = this.bubbleSortReverse(this.standings.favouriteStandings.westConference, 'team.teamName', true);
        } else if (crescente === false) {
          this.standingToShow.eastConference = this.bubbleSort(this.standings.favouriteStandings.eastConference, 'team.teamName', true);
          this.standingToShow.westConference = this.bubbleSort(this.standings.favouriteStandings.westConference, 'team.teamName', true);
        }
      }


      //win
      if (winStanding === true) {
        if (crescente === true) {
          this.standingToShow.eastConference = this.bubbleSortReverse(this.standings.favouriteStandings.eastConference, 'totalWin', false);
          this.standingToShow.westConference = this.bubbleSortReverse(this.standings.favouriteStandings.westConference, 'totalWin', false);
        } else if (crescente === false) {
          this.standingToShow.eastConference = this.bubbleSort(this.standings.favouriteStandings.eastConference, 'totalWin', false);
          this.standingToShow.westConference = this.bubbleSort(this.standings.favouriteStandings.westConference, 'totalWin', false);
        }
      }

      //loss
      if (lossStanding === true) {
        if (crescente === true) {
          this.standingToShow.eastConference = this.bubbleSortReverse(this.standings.favouriteStandings.eastConference, 'totalLoss', false);
          this.standingToShow.westConference = this.bubbleSortReverse(this.standings.favouriteStandings.westConference, 'totalLoss', false);
        } else if (crescente === false) {
          this.standingToShow.eastConference = this.bubbleSort(this.standings.favouriteStandings.eastConference, 'totalLoss', false);
          this.standingToShow.westConference = this.bubbleSort(this.standings.favouriteStandings.westConference, 'totalLoss', false);
        }
      }
    }
  }

  /**
   * Cambia i dati da mostrare, in questa caso mostro i preferiti
   * @param favouriteStanding 
   */
  functionChangeStandingWithFavourite(favouriteStanding: boolean) {
    if (favouriteStanding === true) {
      this.standingToShow.eastConference = this.bubbleSort(this.standings.favouriteStandings.eastConference, 'winPercentage', false);
      this.standingToShow.westConference = this.bubbleSort(this.standings.favouriteStandings.westConference, 'winPercentage', false);
    } else {
      this.standingToShow.eastConference = this.bubbleSort(this.standings.allStanding.eastConference, 'winPercentage', false);
      this.standingToShow.westConference = this.bubbleSort(this.standings.allStanding.westConference, 'winPercentage', false);
    }
  }

  // per cambiare tra una conference e un altra
  selectedConference: boolean = true;
  functionSelectionConference(selectedConference: boolean) {
    this.selectedConference = selectedConference;
  }

  /**
   * ordina in base ai parametri che riceve in maniere crescente
   * @param array : SingleTeamStanding[] --> può accettare solo array di oggetti  di tipo SingleTeamStanding
   * @param key : string --> corrisponde alla chiave dell'oggetto 
   * @param isString : boolean --> per sapere se il contenuto della chiave(il valore) è una stringa o numero
   * @returns 
   */
  bubbleSort(array: SingleTeamStanding[], key: string, isString: boolean): SingleTeamStanding[] {
    for (let i = 0; i < array.length - 1; i++) {
      for (let j = 0; j < array.length - i - 1; j++) {
        //converto l'oggetto che arriva da "getNestedValue" in float
        if (isString === false) {
          const currentVal = parseFloat(this.getNestedValue(array[j], key));
          const nextVal = parseFloat(this.getNestedValue(array[j + 1], key));
          if (currentVal < nextVal) {
            const temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
          }
        } else {
          const currentVal = this.getNestedValue(array[j], key);
          const nextVal = this.getNestedValue(array[j + 1], key);
          if (currentVal < nextVal) {
            const temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
          }
        }
      }
    }
    return array;
  }

  /**
   * ordina in base ai parametri che riceve in maniere decrescente
   * @param array : SingleTeamStanding[] --> può accettare solo array di oggetti  di tipo SingleTeamStanding
   * @param key : string --> corrisponde alla chiave dell'oggetto 
   * @param isString : boolean --> per sapere se il contenuto della chiave(il valore) è una stringa o numero
   * @returns 
   */
  bubbleSortReverse(array: SingleTeamStanding[], key: string, isString: boolean): SingleTeamStanding[] {
    for (let i = 0; i < array.length - 1; i++) {
      for (let j = 0; j < array.length - i - 1; j++) {
        //converto l'oggetto che arriva da "getNestedValue" in float
        if (isString === false) {
          const currentVal = parseFloat(this.getNestedValue(array[j], key));
          const nextVal = parseFloat(this.getNestedValue(array[j + 1], key));
          if (currentVal > nextVal) {
            const temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
          }
        } else {
          const currentVal = this.getNestedValue(array[j], key);
          const nextVal = this.getNestedValue(array[j + 1], key);
          if (currentVal > nextVal) {
            const temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
          }
        }
      }
    }
    return array;
  }

  /**
   * prende il valore all'interno di un oggetto
   * @param obj 
   * @param key 
   * @returns 
   */
  getNestedValue(obj: any, key: string): any {
    const keys = key.split('.');
    let res = obj;
    // ciclo l'array di keys che contiene in questo caso win e percentage e prendo l'ultimo valore che corrisponde alla chiave percentage
    for (const value of keys) {
      //ogni volta assegno il nuovo obj a res cosi da continuare a cercare l'elemento d'interesse
      res = res[value];
    }
    return res;
  }

  vittore: string = "% Vittore";
  vinte: string = "Vinte";
  sconfitte: string = "Sconfitte";

  /**
   * Cambio testo a delle varibili per ottimizzare la visibilità da mobile
   */
  cambiaTesto() {
    const isMobile = window.innerWidth < 1024;

    this.vittore = isMobile ? "% V." : "% Vittore";
    this.vinte = isMobile ? "V." : "Vinte";
    this.sconfitte = isMobile ? "S." : "Sconfitte";
  }

  /**
   * Disattivo o attivo il cuore dei preferiti
   */
  toggleChecked() {
    let checkbox = document.getElementById('cuore') as HTMLInputElement;
    checkbox.checked = !checkbox.checked;
  }
  ToggleCheckedForReset() {
    let checkbox = document.getElementById('cuore') as HTMLInputElement;
    if(checkbox.checked==true) 
      checkbox.checked = !checkbox.checked;
  }

  /**
   * Controllo se il cuore è attivo o no
   * @returns 
   */
  ControlChecked():boolean {
    let checkbox = document.getElementById('cuore') as HTMLInputElement;
    if (checkbox.checked == true)
      return true;
    else
      return false;
  }
}
