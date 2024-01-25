import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { cardArticle } from 'src/app/models/typeArticle';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  matchToday:number[]=[];
  article!: cardArticle;
  constructor(private activatedRoute:ActivatedRoute){}
  ngOnInit(): void{
    this.activatedRoute.data.subscribe(
      //Mi prendo i valori delle partite di oggi: ResolveMatchData e tutti gli articoli: ResolveAllArticle
      ({ ResolveMatchData, ResolveAllArticle }) => {
        ResolveMatchData.forEach((singleMatch: any) => {
          this.matchToday.push(singleMatch.gameid);
        });
        this.article=ResolveAllArticle;
      })
  }
}