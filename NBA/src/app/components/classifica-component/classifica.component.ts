import { Component, HostListener, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Classifica, StandingShow } from 'src/app/models/typeStanding';

@Component({
  selector: 'app-classifica',
  templateUrl: './classifica.component.html',
  styleUrls: ['./classifica.component.css']
})
export class ClassificaComponent implements OnInit {
  @Input() isParziale!: boolean;

  constructor(private activatedRoute: ActivatedRoute) {

  }

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
  standingToShow: StandingShow = {
    eastConference: [],
    westConference: [],
  }
  countForStanding: number = 5;

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.cambiaTesto();
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ ResolveStanding }) => {
        this.standings.allStanding.eastConference = this.bubbleSort(ResolveStanding.allStanding.eastConference, 'winPercentage', false);
        this.standings.allStanding.westConference = this.bubbleSort(ResolveStanding.allStanding.westConference, 'winPercentage', false);
        this.standings.favouriteStandings.eastConference = this.bubbleSort(ResolveStanding.favouriteStandings.eastConference, 'winPercentage', false);
        this.standings.favouriteStandings.westConference = this.bubbleSort(ResolveStanding.favouriteStandings.westConference, 'winPercentage', false);
        this.standingToShow.eastConference = this.standings.allStanding.eastConference;
        this.standingToShow.westConference = this.standings.allStanding.westConference;
      })
    this.cambiaTesto();
    if (this.isParziale === true)
      this.countForStanding = 5;
    else
      this.countForStanding = 15;
  }

  percentageStanding: boolean = true;
  favouriteStanding: boolean = false;
  nameStanding: boolean = false;
  winStanding: boolean = false;
  lossStanding: boolean = false;
  crescente: boolean = false;

  functionChangeFilterStanding(percentageStanding: boolean, nameStanding: boolean, winStanding: boolean, lossStanding: boolean, crescente: boolean, event: Event) {
    event.preventDefault();

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

  functionChangeStandingWithFavourite(favouriteStanding: boolean, event: Event) {
    event.preventDefault();
    if (favouriteStanding === true) {
      this.standingToShow.eastConference = this.bubbleSort(this.standings.favouriteStandings.eastConference, 'winPercentage', false);
      this.standingToShow.westConference = this.bubbleSort(this.standings.favouriteStandings.westConference, 'winPercentage', false);
    } else {
      this.standingToShow.eastConference = this.bubbleSort(this.standings.allStanding.eastConference, 'winPercentage', false);
      this.standingToShow.westConference = this.bubbleSort(this.standings.allStanding.westConference, 'winPercentage', false);
    }
  }

  selectedConference: boolean = true;

  functionSelectionConference(selectedConference: boolean) {
    this.selectedConference = selectedConference;
  }

  //ordina in base ai parametri che riceve
  bubbleSort(array: any[], key: string, isString: boolean): any[] {
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

  bubbleSortReverse(array: any[], key: string, isString: boolean): any[] {
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

  //prende il valore all'interno di un oggetto
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

  private cambiaTesto() {
    const isMobile = window.innerWidth < 1024;

    this.vittore = isMobile ? "% V." : "% Vittore";
    this.vinte = isMobile ? "V." : "Vinte";
    this.sconfitte = isMobile ? "S." : "Sconfitte";
  }

  toggleChecked() {
    let checkbox = document.getElementById('cuore') as HTMLInputElement;
    checkbox.checked = !checkbox.checked;
    console.log(checkbox.checked);
  }
  ToggleCheckedForReset() {
    let checkbox = document.getElementById('cuore') as HTMLInputElement;
    if(checkbox.checked==true) 
      checkbox.checked = !checkbox.checked;
  }

  ControlChecked():boolean {
    let checkbox = document.getElementById('cuore') as HTMLInputElement;
    if (checkbox.checked == true)
      return true;
    else
      return false;
  }
}
