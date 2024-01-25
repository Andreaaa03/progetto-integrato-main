import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { cardArticle } from 'src/app/models/typeArticle';


@Component({
  selector: 'app-blog-page',
  templateUrl: './blog-page.component.html',
  styleUrls: ['./blog-page.component.css']
})
export class BlogPageComponent implements OnInit {
  article!: cardArticle;

  constructor(private activatedRoute: ActivatedRoute){}

  ngOnInit(): void {
    //Mi prendo tutti gli articoli e passo l'id degli articoli che voglio visualizzare
    this.activatedRoute.data.subscribe(
      ({ResolveAllArticle }) => {
        this.article=ResolveAllArticle;
      })
  }

}
