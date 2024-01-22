import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { matchStats } from 'src/app/models/typeMatch';

@Component({
  selector: 'app-partita-page',
  templateUrl: './partita-page.component.html',
  styleUrls: ['./partita-page.component.css']
})
export class PartitaPageComponent implements OnInit {
  quarti: number[] = [1, 2, 3, 4];
  tabsSelected: string = "statics";
  staticsToShow: number[] = [];

  matchStats!: matchStats;

  constructor(private activatedRoute: ActivatedRoute) { }
  ngOnInit(): void {

    this.activatedRoute.data.subscribe(
      ({ ResolveMatchStats }) => {
        this.matchStats = ResolveMatchStats;
        for(let i=0; i<this.matchStats.awayTeam.datiArray.length-1; i++) {
          this.staticsToShow.push(i);
        } 
      })
  }

  functionCalculatePercentage(lato1: number, lato2: number, whichLato: boolean): number {
    const sum = lato1 + lato2;
    const percentage1 = Math.round((lato1 / sum) * 100);
    const percentage2 = Math.round((lato2 / sum) * 100);

    return whichLato ? percentage1 : percentage2;
  }
}
