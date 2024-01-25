import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { allPlayer, matchCalendar, teamPlayer, teamStatistic, teamCalendar } from 'src/app/models/typeSingleTeam';



@Component({
  selector: 'app-squadra-detail-page',
  templateUrl: './squadra-detail-page.component.html',
  styleUrls: ['./squadra-detail-page.component.css']
})
export class SquadraDetailPageComponent implements OnInit {
  constructor(private activatedRoute: ActivatedRoute) { }
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ ResolveSingleTeamStatistics, ResolveSingleTeamCalendar, ResolveSingleTeamPlayer }) => {
        this.teamStatistics = ResolveSingleTeamStatistics;
        this.matchCalendar.totalMatch = ResolveSingleTeamCalendar.totalMatch;
        this.teamsPlayer = ResolveSingleTeamPlayer;
        // per non mostrare il primi 2 dati di teamsPlayer uso una variabile i per saltare i primi 2 campi
        let i = 0;
        this.teamsPlayer.forEach(() => {
          if (i >= 2) {
            this.statisticToShow.push(i);
          }
          i++;
        })
        this.ripetiArray = new Array(this.matchCalendar.totalMatch.length).fill(null);
      })
  }
  
  cardToShow: number = 10;
  squadraCalendario: boolean = true; // per selezionare la pagina squadra&calendario o Giocatori
  statisticToShow: number[] = [];
  teamStatistics?: teamStatistic; // dati statistici di una squadra
  // tutte le partite del calendario divise in 3 array già pronte per una modifica futura
  matchCalendar: matchCalendar = {
    previousMatch: [],
    nextMatch: [],
    totalMatch: [],
  }
  teamsPlayer!: teamPlayer; //elenco giocatori
  ripetiArray: Array<any> = [];

  /**
   * cambio pagina scelta
   * @param squadraCalendario : boolean
   */
  selectedTeam(squadraCalendario: boolean) {
    this.squadraCalendario = squadraCalendario;
  }
  /**
   * Ogni volta che invoco questa funzione aggiungo 10 partite da visuallizare al calendario fin quando non arrivo alla fine
   */
  showMore() {
    if (this.cardToShow <= this.matchCalendar.totalMatch.length)
      this.cardToShow += 10;
    else
      this.cardToShow = this.matchCalendar.totalMatch.length;
  }

}
