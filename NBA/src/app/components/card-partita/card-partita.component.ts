import { Component, Input, OnInit } from '@angular/core';
import { match } from 'src/app/models/typeMatch';
import { GetApiServiceMatch } from 'src/app/services/getApiMatch.service';

@Component({
  selector: 'app-card-partita',
  templateUrl: './card-partita.component.html',
  styleUrls: ['./card-partita.component.css']
})
export class CardPartitaComponent implements OnInit {
  constructor(private getApiService: GetApiServiceMatch) { }
  @Input() gameId!: string;

  ngOnInit(): void {
    this.functionGetMatch(this.gameId!);
  }

  match!: match;
  functionGetMatch(id: string) {
    if (this.gameId != undefined) {
      this.getApiService.getSearchMatch(id).subscribe(
        (match) => {
          this.match = match;
        }
      )
    }
  }
}
