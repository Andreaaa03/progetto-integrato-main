import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { division } from 'src/app/models/typeTeams';
import { GetApiServiceTeams } from 'src/app/services/getApiTeams.service';

@Component({
  selector: 'app-squadre-page',
  templateUrl: './squadre-page.component.html',
  styleUrls: ['./squadre-page.component.css']
})
export class SquadrePageComponent implements OnInit {

  isConferenceSelected: boolean=true;
  functionChangeConferenceSelected(){
    this.isConferenceSelected=!this.isConferenceSelected;
  }

  teams: division = {
    NorthWest: [],
    SouthWest: [],
    SoutHeast: [],
    Atlantic: [],
    Central: [],
    Pacific: [],
  };
  isMobile: boolean = false;

  @HostListener('window:resize')
  onResize(): void {
    this.checkIfMobile();
  }

  checkIfMobile(): void {
    this.isMobile = window.innerWidth < 768; // Cambia questo valore in base alle tue esigenze
  }

  constructor(private activatedRoute: ActivatedRoute){}
  ngOnInit(): void {
    this.checkIfMobile();
    this.activatedRoute.data.subscribe(
      ({ ResolveTeams }) => {
        this.teams.NorthWest=ResolveTeams.NorthWest;
        this.teams.SouthWest=ResolveTeams.SouthWest;
        this.teams.SoutHeast=ResolveTeams.SoutHeast;
        this.teams.Atlantic=ResolveTeams.Atlantic;
        this.teams.Central=ResolveTeams.Central;
        this.teams.Pacific=ResolveTeams.Pacific;
      })
  }
}