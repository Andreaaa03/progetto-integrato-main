import { Component, Input, OnInit } from '@angular/core';
import { commento } from 'src/app/models/typeComment';
import { ApiService } from 'src/app/services/api.service';



@Component({
  selector: 'app-card-commento',
  templateUrl: './card-commento.component.html',
  styleUrls: ['./card-commento.component.css']
})
export class CardCommentoComponent implements OnInit {
  constructor(private apiService: ApiService){}
  @Input() commento!: commento;
  ngOnInit(): void {
    // console.log(this.commento);
    this.functionChangeTestoCommenti();
  }
  
  showResponseComment: boolean = false;
  testoCommenti: string = "Mostra Commenti";
  /**
   * cambio testo
   */
  functionChangeTestoCommenti() {
    if (this.showResponseComment) {
      this.testoCommenti = "Nascondi Commenti"
    } else {
      this.testoCommenti = "Mostra Commenti";
    }
  }

}
