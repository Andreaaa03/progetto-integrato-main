import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-commento',
  templateUrl: './card-commento.component.html',
  styleUrls: ['./card-commento.component.css']
})
export class CardCommentoComponent implements OnInit {
  countCommenti: number[] = [1,2,3,4,5];
  showResponseComment: boolean = false;
  testoCommenti:string="Mostra Commenti";
  ngOnInit(): void {
    this.functionChangeTestoCommenti();
  }
    functionChangeTestoCommenti() {
      if(this.showResponseComment){
        this.testoCommenti="Nascondi Commenti"
      }else{
        this.testoCommenti="Mostra Commenti";
      }
    }
}
