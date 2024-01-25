import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { detailArticle } from 'src/app/models/typeArticle';
import { commento } from 'src/app/models/typeComment';
import { ApiService } from 'src/app/services/api.service';
import { GetApiServiceComment } from 'src/app/services/getApiComment.service';

export class newCommento {
  commento: string = '';
  id_articolo_padre:string='';
}
@Component({
  selector: 'app-articolo-detail-page',
  templateUrl: './articolo-detail-page.component.html',
  styleUrls: ['./articolo-detail-page.component.css']
})
export class ArticoloDetailPageComponent implements OnInit {
  constructor(private activatedRoute: ActivatedRoute, private getApiCommento: GetApiServiceComment, private apiService: ApiService) { }
  ngOnInit(): void {
    //mi prendo i dati del singolo articolo
    this.activatedRoute.data.subscribe(
      ({ ResolveArticle, ResolveCommentForArticle }) => {
        this.functionOnInit(ResolveCommentForArticle);
        this.article = ResolveArticle;
      })
  }
  functionOnInit(ResolveCommentForArticle:commento[]): void {
    this.CommentForArticle = ResolveCommentForArticle;
  }
  CommentForArticle!: commento[];
  article!: detailArticle;
  newComment: newCommento = new newCommento();

  functionNewComment(){
    if(localStorage.getItem('authToken')){
      this.apiService.AddCommentArticle(localStorage.getItem('authToken') as string, this.article.blog.id, this.newComment.commento).subscribe(
        ()=>{},
        ()=>{
          this.getApiCommento.getSearchAllCommentsArticle(this.article.blog.id).subscribe(
            (allComments:any)=>{
              this.functionOnInit(allComments);
            }
          )
        }
      )
    }else{
      console.log("token non valido");
    }
  }
}