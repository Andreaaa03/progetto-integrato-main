import { Component, Input, OnInit } from '@angular/core';
import { detailArticle } from 'src/app/models/typeArticle';
import { ApiService } from 'src/app/services/api.service';
import { GetApiServiceArticle } from 'src/app/services/getApiArticle.service';
import { GetApiServiceProfilo } from 'src/app/services/getApiProfile.service';

@Component({
  selector: 'app-post-articolo',
  templateUrl: './post-articolo.component.html',
  styleUrls: ['./post-articolo.component.css']
})


export class PostArticoloComponent implements OnInit {
  @Input() extended: boolean = true;
  @Input() artId: string = "";
  singleArticle!: detailArticle | any;
  constructor(private getApiServiceArticle: GetApiServiceArticle, private apiService: ApiService, private getApiProfile: GetApiServiceProfilo) { }
  ngOnInit(): void {
    this.functionOnInit();
  }

  functionOnInit() {
    // prendo le info generali del singolo articolo per la preview
    this.getApiServiceArticle.getSearchSingleArticle(this.artId).subscribe(
      (art) => {
        this.singleArticle = art;
        if (localStorage.getItem('authToken')) {
          this.getApiProfile.getSearchFavouriteArticle(localStorage.getItem('authToken') as string).subscribe(
            (article) => {
              this.favouriteArticles = article;
              this.favouriteArticles.forEach((article) => {
                if (article.blog.id == this.singleArticle.blog.id) {
                  this.singleArticle.blog.favourite = true;
                }
              })
            }
          )
        } else {
          console.log("token non valido");
        }
      }
    )
  }
          
  favouriteArticles!: detailArticle[];

  /**
   * Aggiungo e rimuovo gli articoli preferite, se il token non è valido svuoto la sessione
   * @param id : string
   */
  aggiungiRimuoviPreferitiArticles(id: string) {
    if (localStorage.getItem('authToken')) {
      this.apiService.AddRemoveFavouriteArticle(localStorage.getItem('authToken') as string, id + "").subscribe(
        () => { },
        (err) => {
          if (err.status >= 200 && err.status <= 299) {
            this.functionOnInit();
          }
          console.log(err);
        }
      );
    } else {
      console.log("token non valido");
    }
  }
}
