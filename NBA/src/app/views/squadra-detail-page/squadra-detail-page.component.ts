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
  cardToShow: number = 10;
  squadraCalendario: boolean = true;
  statisticToShow: number[] = [];
  

  selectedTeam(squadraCalendario: boolean) {
    this.squadraCalendario = squadraCalendario;
  }
  showMore() {
    if (this.cardToShow < this.matchCalendar.totalMatch.length)
      this.cardToShow += 10;
  }

  teamStatistics?: teamStatistic;
  matchCalendar: matchCalendar = {
    previousMatch: [],
    nextMatch: [],
    totalMatch: [],
  }
  teamsPlayer!: teamPlayer;
  ripetiArray: Array<any> = [] ;
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ ResolveSingleTeamStatistics, ResolveSingleTeamCalendar, ResolveSingleTeamPlayer }) => {
        this.teamStatistics = ResolveSingleTeamStatistics;
        this.matchCalendar.totalMatch = ResolveSingleTeamCalendar.totalMatch;
        this.teamsPlayer = ResolveSingleTeamPlayer;
        console.log(this.teamsPlayer[0].statisticsArray[0]);
        let i = 0;
        this.teamsPlayer.forEach(() => {
          if(i >= 2){
            this.statisticToShow.push(i);
          }
            i++;
        })
        console.log(this.statisticToShow.length);
        this.ripetiArray=new Array(this.matchCalendar.totalMatch.length).fill(null);
      })
  }

  
}
