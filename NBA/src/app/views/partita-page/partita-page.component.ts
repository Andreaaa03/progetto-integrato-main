import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { detailArticle } from 'src/app/models/typeArticle';
import { commento } from 'src/app/models/typeComment';
import { matchStats } from 'src/app/models/typeMatch';
import { ApiService } from 'src/app/services/api.service';
import { GetApiServiceComment } from 'src/app/services/getApiComment.service';

export class newCommento {
  commento: string = '';
  id_articolo_padre: string = '';
}
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

  constructor(private activatedRoute: ActivatedRoute, private getApiCommento: GetApiServiceComment, private apiService: ApiService) { }
  ngOnInit(): void {

    this.activatedRoute.data.subscribe(
      ({ ResolveMatchStats, ResolveCommentForGame }) => {
        this.matchStats = ResolveMatchStats;
        for (let i = 0; i < this.matchStats.awayTeam.datiArray.length - 1; i++) {
          this.staticsToShow.push(i);
        }
        this.functionOnInit(ResolveCommentForGame);
      })
  }

  /**
   * prendo i valori della singola partita e mi calcolo la percentuale da usare nel width nella sezione confronto. 
   * è importante specificare quale lato ti riferisci perchè viene invocata nell'HTML; per lato si intende squadra di casa o trasferta
   * @param lato1 : number
   * @param lato2 : number
   * @param whichLato : boolean
   * @returns : number --> Rtiorna il valore che corrispondera alla percentuale del width nel div in HTML
   */
  functionCalculatePercentage(lato1: number, lato2: number, whichLato: boolean): number {
    const sum = lato1 + lato2;
    const percentage1 = Math.round((lato1 / sum) * 100);
    const percentage2 = Math.round((lato2 / sum) * 100);

    return whichLato ? percentage1 : percentage2;
  }

  functionOnInit(ResolveCommentForGame: commento[]): void {
    this.CommentForGame = ResolveCommentForGame;
  }

  CommentForGame!: commento[];
  newComment: newCommento = new newCommento();

  functionNewComment() {
    if (localStorage.getItem('authToken')) {
      this.apiService.AddCommentGame(localStorage.getItem('authToken') as string, this.matchStats.calendarioDateResponse.gameid as string, this.newComment.commento).subscribe(
        () => { },
        () => {
          this.getApiCommento.getSearchAllCommentsGame(this.matchStats.calendarioDateResponse.gameid as string).subscribe(
            (allComments: any) => {
              this.functionOnInit(allComments);
            }
          )
        }
      )
    } else {
      console.log("token non valido");
    }
  }
}
