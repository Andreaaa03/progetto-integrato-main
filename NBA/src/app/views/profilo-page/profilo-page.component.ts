import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { division } from 'src/app/models/typeTeams';

@Component({
  selector: 'app-profilo-page',
  templateUrl: './profilo-page.component.html',
  styleUrls: ['./profilo-page.component.css']
})
export class ProfiloPageComponent implements OnInit{
  menuSelected:string="amici";
  menuPreferitiSelected:string="tutti";
  showTeams: boolean=false;
  ripetiArray: any[] = new Array(10).fill({}); 
  constructor(private activatedRoute: ActivatedRoute) { }
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ ResolveTeams }) => {
        this.teams.NorthWest = ResolveTeams.NorthWest;
        this.teams.SouthWest = ResolveTeams.SouthWest;
        this.teams.SoutHeast = ResolveTeams.SoutHeast;
        this.teams.Atlantic = ResolveTeams.Atlantic;
        this.teams.Central = ResolveTeams.Central;
        this.teams.Pacific = ResolveTeams.Pacific;
      })
  }
  functionChangeManu(word:string){
    this.menuSelected=word;
  }
  functionChangeManuPreferiti(word:string){
    this.menuPreferitiSelected=word;
  }

  isConferenceSelected: boolean = true;
  functionChangeConferenceSelected() {
    this.isConferenceSelected = !this.isConferenceSelected;
  }

  teams: division = {
    NorthWest: [],
    SouthWest: [],
    SoutHeast: [],
    Atlantic: [],
    Central: [],
    Pacific: [],
  };

  removeToken(){
    localStorage.removeItem('authToken');
    window.location.replace('/home');
  }

}
