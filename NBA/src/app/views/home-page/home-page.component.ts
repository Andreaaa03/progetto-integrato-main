import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  matchToday:number[]=[];
  constructor(private activatedRoute:ActivatedRoute){}
  ngOnInit(): void{
    this.activatedRoute.data.subscribe(
      ({ ResolveMatchData }) => {
        ResolveMatchData.forEach((singleMatch: any) => {
          this.matchToday.push(singleMatch.gameid);
        })
      })
  }
}
