import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-partite-del-giorno-preview',
  templateUrl: './partite-del-giorno-preview.component.html',
  styleUrls: ['./partite-del-giorno-preview.component.css']
})
export class PartiteDelGiornoPreviewComponent implements OnInit {
  @Input() cards: any[] = [
    1,2,3,4,5,6
  ];

  ngOnInit(): void {
  }
  currentIndexAfter: number = 2;
  currentIndex: number = 1;
  currentIndexBefore: number = 0;

  functionCambiaCard(increment: number) {
    this.currentIndex = (this.currentIndex + increment + this.cards.length) % this.cards.length;
    const lastIndex = this.cards.length - 1;

    this.currentIndexAfter = (this.currentIndex + 1) % this.cards.length;
    this.currentIndexBefore = (this.currentIndex - 1 + this.cards.length) % this.cards.length;
  }

  calculateOrder(index: number): number {
    if (index === this.currentIndex) {
      return 1;
    } else if (index === this.currentIndexAfter) {
      return 2;
    } else if (index === this.currentIndexBefore) {
      return 0;
    } else {
      return -1; // o qualsiasi altro valore che mantenga l'ordine corretto
    }
  }



}
