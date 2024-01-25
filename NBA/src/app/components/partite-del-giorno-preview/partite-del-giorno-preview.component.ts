import { Component, HostListener, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-partite-del-giorno-preview',
  templateUrl: './partite-del-giorno-preview.component.html',
  styleUrls: ['./partite-del-giorno-preview.component.css']
})
export class PartiteDelGiornoPreviewComponent implements OnInit {
  @Input() cards: any[] = [];
  ngOnInit(): void {
    this.checkIfMobile();
  }
  // controllo c se siamo su mobile o no
  isMobile: boolean = false;
  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    this.checkIfMobile();
  }
  checkIfMobile(): void {
    this.isMobile = window.innerWidth < 768;
  }


  currentIndexAfter: number = 2;
  currentIndex: number = 1;
  currentIndexBefore: number = 0;
  /**
   * cambio card nel carosello
   * @param increment 
   */
  functionCambiaCard(increment: number) {
    console.log(increment);
    this.currentIndex = (this.currentIndex + increment + this.cards.length) % this.cards.length;
    const lastIndex = this.cards.length - 1;
    this.currentIndexAfter = (this.currentIndex + 1) % this.cards.length;
    this.currentIndexBefore = (this.currentIndex - 1 + this.cards.length) % this.cards.length;
  }

  /**
   * determino l'ordine delle card all'interno del carosello
   * @param index 
   * @returns 
   */
  calculateOrder(index: number): number {
    if (index === this.currentIndex) {
      return 1;
    } else if (index === this.currentIndexAfter) {
      return 2;
    } else if (index === this.currentIndexBefore) {
      return 0;
    } else {
      return -1;
    }
  }
}