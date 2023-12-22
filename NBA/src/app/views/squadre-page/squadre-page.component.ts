import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { division } from 'src/app/models/typeTeams';
import { GetApiServiceTeams } from 'src/app/services/getApiTeams.service';

@Component({
  selector: 'app-squadre-page',
  templateUrl: './squadre-page.component.html',
  styleUrls: ['./squadre-page.component.css']
})
export class SquadrePageComponent implements OnInit {

  teams: division = {
    NorthWest: [],
    SouthWest: [],
    SoutHeast: [],
    Atlantic: [],
    Central: [],
    Pacific: [],
  };

  constructor(private activatedRoute: ActivatedRoute){}
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ ResolveTeams }) => {
        console.log(ResolveTeams);
        this.teams.NorthWest=ResolveTeams.NorthWest;
        this.teams.SouthWest=ResolveTeams.SouthWest;
        this.teams.SoutHeast=ResolveTeams.SoutHeast;
        this.teams.Atlantic=ResolveTeams.Atlantic;
        this.teams.Central=ResolveTeams.Central;
        this.teams.Pacific=ResolveTeams.Pacific;
      })
  }
}