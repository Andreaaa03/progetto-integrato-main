import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as dayjs from 'dayjs';
import { matchDate } from 'src/app/models/typeMatch';
import { Location } from '@angular/common';
import { GetApiServiceMatch } from 'src/app/services/getApiMatch.service';

@Component({
  selector: 'app-calendario-page',
  templateUrl: './calendario-page.component.html',
  styleUrls: ['./calendario-page.component.css']
})
export class CalendarioPageComponent implements OnInit {

  constructor(private router: Router, private location: Location, private activatedRoute: ActivatedRoute, private getApiServiceMatch: GetApiServiceMatch) { }
  ngOnInit(): void {
    this.initDate(); //capisco su che data devo lavorare
    // in base alla data mi preparo il calendario da visualizzare
    this.getNoOfDaysLeft(); 
    this.getNoOfDaysCentral();
    this.getNoOfDaysRight();
    this.remainDay();
    // setto la data che devo far risaltare nel calendario da confrontare nel HTML
    this.isToday(this.currentYearCentral, this.currentMonthCentral, this.daySelected);

    // prendo i dati per le partite di una data variabile e le ultime 20 partite a partire da ieri.
    this.activatedRoute.data.subscribe(
      ({ ResolveMatchData, ResolveMatchDataLast20 }) => {
        ResolveMatchData.forEach((singleMatch: any) => {
          this.matchToday.push(singleMatch.gameid);
        })
        ResolveMatchDataLast20.forEach((singleMatch: any) => {
          singleMatch.gameStartDate = dayjs(singleMatch.gameStartDate).format("YYYY-MM-DD HH:mm");
        })
        this.matchTodayLast20 = ResolveMatchDataLast20;
      })
    this.cardToShow = this.matchToday.length;
  }

  matchToday: number[] = []; //partite di oggi che ogni volta cambiano in base al giorno
  matchTodayLast20!: matchDate; //ultimi 20 partite a partire da ieri

  //numero di card da mostrare, inizialmente erano solo 3 che potevano diventare tutti. Per praticità li mostriamo direttamente tutti
  cardToShow: number = 0;
  changeCardToShow(cardToShow: number): void {
    if (cardToShow <= 3)
      this.cardToShow = this.matchToday.length;
    else
      this.cardToShow = this.matchToday.length;
  }

  //per gestire il calendario 
  MONTH_NAMES = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  DAYS = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
  daySelected: number = 0;
  currentMonthCentral: number = 0;
  currentYearCentral: number = 2023;
  currentMonthLeft: number = 0;
  currentYearLeft: number = 2023;
  currentMonthRight: number = 0;
  currentYearRight: number = 2023;
  isTodayYear: number = 2023;
  isTodayMonth: number = 0;
  isTodayDay: number = 0;
  finalDay: string = "";
  // per una formattazione carina calcolo quanti giorni ha un mese, e i giorni prima per partire nella cella giusta
  no_of_days_central: number[] = [];
  no_of_days_left: number[] = [];
  no_of_days_right: number[] = [];
  blankdays_central: number[] = [];
  blankdays_left: number[] = [];
  blankdays_right: number[] = [];
  ripetiArray = new Array(10).fill(null);

  dateURL = "";
  /**
   * prendo l'url della pagina e mi prendo solo la data, successivamente la setto nel calendario centrale e adatto i calendari esterni
   * (Da mobile vedo solo un calendario)
   * @returns void
   */
  initDate(): void {
    this.dateURL = this.location.path().split('/')[2];

    this.currentMonthCentral = Number(this.dateURL.split('-')[1]) - 1;
    this.currentYearCentral = Number(this.dateURL.split('-')[0]);
    this.daySelected = Number(this.dateURL.split('-')[2]);
    this.currentMonthLeft = this.currentMonthCentral - 1;

    if (this.currentMonthCentral + 1 > 11) {
      this.currentYearRight = this.currentYearCentral + 1;
      this.currentMonthRight = 0;
    } else {
      this.currentYearRight = this.currentYearCentral;
      this.currentMonthRight = this.currentMonthCentral + 1;
    }

    if (this.currentMonthLeft < 0) {
      this.currentYearLeft = this.currentYearCentral - 1;
      this.currentMonthLeft = 11;
    } else {
      this.currentYearLeft = this.currentYearCentral;
      this.currentMonthLeft = this.currentMonthCentral - 1;
    }

    if (this.currentMonthRight < 0) {
      this.currentYearRight = this.currentYearCentral + 1;
      this.currentMonthRight = 11;
    } else if (this.currentMonthRight > 11) {
      this.currentYearRight = this.currentYearCentral + 1;
      this.currentMonthRight = 0;
    }
  };


  /**
   * Funzione per formattare bene la data, per aggiungere 0 al mese o no
   * @param Year : number
   * @param Month : number
   * @param Day : number
   */
  functionDateSelected(Year: number, Month: number, Day: number): string {
    let date: string;
    if (Month + 1 >= 1 && Month + 1 <= 9)
      date = Year + "-0" + (Month + 1) + "-" + Day;
    else
      date = Year + "-" + (Month + 1) + "-" + Day;
    return date;
  }

  /**
   * Invocata nel HTML per cambiare la data. Mi formatto la data per aggiornare l'url e mi faccio ottenere le partite del giorno stesso
   * @param Year : number
   * @param Month : number
   * @param Day : number
   */
  changeDate(Year: number, Month: number, Day: number): void {
    const url = `/calendario/${this.functionDateSelected(Year, Month, Day)}`;
    this.getApiServiceMatch.getSearchMatchDate(this.functionDateSelected(Year, Month, Day)).subscribe(match => {
      this.router.navigateByUrl(url, { replaceUrl: true });
      this.matchToday = [];
      match.forEach((singleMatch: any) => {
        this.matchToday.push(singleMatch.gameid);
      })
    });
    this.cardToShow = this.matchToday.length;
  };

  /**
   * Con questa funzione setto data scelta dall'utente da evidenziare nel calendario, inizialmente è la data di oggi.
   * @param Year : number
   * @param Month : number
   * @param Day : number
   */
  isToday(Year: number, Month: number, Day: number) {
    if (Month + 1 >= 1 && Month + 1 <= 9)
      this.finalDay = Year + "-0" + (Month + 1) + "-" + Day;
    else
      this.finalDay = Year + "-" + (Month + 1) + "-" + Day;
    this.isTodayYear = Year;
    this.isTodayMonth = Month;
    this.isTodayDay = Day;
  };
  /**
   * Ogni volta li viene passata una data del calendario, quando questa data corrisponde alla data setta nella funzione isToday(Year, Month, Day) allora ritorno true
   * @param Year : number
   * @param Month : number
   * @param Day : number
   * @returns : boolean
   */
  controlForIsToday(Year: number, Month: number, Day: number): boolean {
    let res: boolean = false;
    if (
      this.isTodayYear === Year &&
      this.isTodayMonth === Month &&
      this.isTodayDay === Day
    ) {
      res = true;
    }
    else
      res = false;
    return res;
  }

  /**
   * Mi calcolo quanti giorni ci sono prima di partire col mese in questo calendario(left) 
   * per mostrare il primo del mese nel giorno giusto(lun, mar, mer, ecc..).
   */
  getNoOfDaysLeft() {
    let daysInMonthLeft = new Date(this.currentYearLeft, this.currentMonthLeft + 1, 0).getDate();

    // find where to start calendar day of week
    let dayOfWeekLeft = new Date(this.currentYearLeft, this.currentMonthLeft).getDay();

    let blankdaysArrayLeft = [];
    for (let i = 1; i < dayOfWeekLeft; i++) {
      blankdaysArrayLeft.push(i);
    }

    let daysArrayLeft = [];
    for (let i = 1; i <= daysInMonthLeft; i++) {
      daysArrayLeft.push(i);
    }

    this.blankdays_left = blankdaysArrayLeft;
    this.no_of_days_left = daysArrayLeft;
  }
  /**
   * Mi calcolo quanti giorni ci sono prima di partire col mese in questo calendario(left) 
   * per mostrare il primo del mese nel giorno giusto(lun, mar, mer, ecc..).
   */
  getNoOfDaysCentral() {
    let daysInMonthCentral = new Date(this.currentYearCentral, this.currentMonthCentral + 1, 0).getDate();

    let dayOfWeekCentral = new Date(this.currentYearCentral, this.currentMonthCentral).getDay();

    let blankdaysArrayCentral = [];
    for (let i = 1; i < dayOfWeekCentral; i++) {
      blankdaysArrayCentral.push(i);
    }

    let daysArrayCentral = [];
    for (let i = 1; i <= daysInMonthCentral; i++) {
      daysArrayCentral.push(i);
    }

    this.blankdays_central = blankdaysArrayCentral;
    this.no_of_days_central = daysArrayCentral;
  }
  /**
   * Mi calcolo quanti giorni ci sono prima di partire col mese in questo calendario(left) 
   * per mostrare il primo del mese nel giorno giusto(lun, mar, mer, ecc..).
   */
  getNoOfDaysRight() {
    let daysInMonthRight = new Date(this.currentYearRight, this.currentMonthRight + 1, 0).getDate();

    // find where to start calendar day of week
    let dayOfWeekRight = new Date(this.currentYearRight, this.currentMonthRight).getDay();

    let blankdaysArrayRight = [];
    for (let i = 1; i < dayOfWeekRight; i++) {
      blankdaysArrayRight.push(i);
    }

    let daysArrayRight = [];
    for (let i = 1; i <= daysInMonthRight; i++) {
      daysArrayRight.push(i);
    }

    this.blankdays_right = blankdaysArrayRight;
    this.no_of_days_right = daysArrayRight;
    this.remainDay();
  }

  remain_days_left: number[] = [];
  remain_days_central: number[] = [];
  remain_days_right: number[] = [];
  /**
   * In base a quanti giorni ha un mese e quanti giorni ci sono prima di inizare col mese mi 
   * calcolo quanti giorni rimangono per finire di compilare la tabella nell HTML
   */
  remainDay() {
    this.remain_days_left = [];
    this.remain_days_central = [];
    this.remain_days_right = [];
    for (let i = this.blankdays_left.length + this.no_of_days_left.length; i < 35; i++) {
      this.remain_days_left.push(i);
    }
    for (let i = this.blankdays_central.length + this.no_of_days_central.length; i < 35; i++) {
      this.remain_days_central.push(i);
    }
    for (let i = this.blankdays_right.length + this.no_of_days_right.length; i < 35; i++) {
      this.remain_days_right.push(i);
    }
  }
  /**
   * incrementa il mese di un mese per volta, controllo quindi i casi di fine e inizio anno
   * @param currentMonthLeft : number
   * @param currentMonthCentral : number
   * @param currentMonthRight : number
   */
  incrementMonth(currentMonthLeft: number, currentMonthCentral: number, currentMonthRight: number) {
    if (this.currentMonthLeft + 1 > 11) {
      this.currentMonthLeft = 0;
      this.currentYearLeft++;
    } else
      this.currentMonthLeft = currentMonthLeft + 1;

    if (currentMonthCentral + 1 > 11) {
      this.currentMonthCentral = 0;
      this.currentYearCentral++;
    } else
      this.currentMonthCentral = currentMonthCentral + 1;

    if (currentMonthRight + 1 > 11) {
      this.currentMonthRight = 0;
      this.currentYearRight++;
    } else
      this.currentMonthRight = currentMonthRight + 1;

    //invoco queste funzioni per ricalcolare immediatamente quanti giorni ci sono in quel mese e formattare bene la tabella
    this.getNoOfDaysLeft();
    this.getNoOfDaysCentral();
    this.getNoOfDaysRight();
  }
  /**
   * decremento il mese di un mese per volta, controllo quindi i casi di fine e inizio anno
   * @param currentMonthLeft : number
   * @param currentMonthCentral : number
   * @param currentMonthRight : number
   */
  decrementMonth(currentMonthLeft: number, currentMonthCentral: number, currentMonthRight: number) {
    if (this.currentMonthLeft - 1 < 0) {
      this.currentMonthLeft = 11;
      this.currentYearLeft--;
    } else
      this.currentMonthLeft = currentMonthLeft - 1;

    if (currentMonthCentral - 1 < 0) {
      this.currentMonthCentral = 11;
      this.currentYearCentral--;
    } else
      this.currentMonthCentral = currentMonthCentral - 1;

    if (currentMonthRight - 1 < 0) {
      this.currentMonthRight = 11;
      this.currentYearRight--;
    } else
      this.currentMonthRight = currentMonthRight - 1;

    //invoco queste funzioni per ricalcolare immediatamente quanti giorni ci sono in quel mese e formattare bene la tabella
    this.getNoOfDaysLeft();
    this.getNoOfDaysCentral();
    this.getNoOfDaysRight();
  }
}
