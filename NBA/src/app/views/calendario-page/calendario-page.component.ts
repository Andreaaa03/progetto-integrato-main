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

  matchToday: number[] = [];
  matchTodayLast20!: matchDate;
  cardToShow: number = 3;

  changeCardToShow(cardToShow: number): void {
    if (cardToShow <= 3)
      this.cardToShow = this.matchToday.length;
    else
      this.cardToShow = 3;
  }
  MONTH_NAMES = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  DAYS = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];

  daySelected: number = 0;
  currentMonthCentral: number = 0;
  currentYearCentral: number = 2023;
  currentMonthLeft: number = 0;
  currentYearLeft: number = 2023;
  currentMonthRight: number = 0;
  currentYearRight: number = 2023;
  no_of_days_central: number[] = [];
  no_of_days_left: number[] = [];
  no_of_days_right: number[] = [];
  blankdays_central: number[] = [];
  blankdays_left: number[] = [];
  blankdays_right: number[] = [];
  isTodayYear: number = 2023;
  isTodayMonth: number = 0;
  isTodayDay: number = 0;
  finalDay: string = "";
  ripetiArray = new Array(10).fill(null);

  dateURL="";
  constructor(private router:Router,private location: Location, private activatedRoute: ActivatedRoute, private getApiServiceMatch: GetApiServiceMatch){}
  ngOnInit(): void {
    this.initDate();
    this.getNoOfDaysLeft();
    this.getNoOfDaysCentral();
    this.getNoOfDaysRight();
    this.isToday(this.currentYearCentral, this.currentMonthCentral, this.daySelected);
    this.remainDay();

    this.activatedRoute.data.subscribe(
      ({ ResolveMatchData, ResolveMatchDataLast20 }) => {
        ResolveMatchData.forEach((singleMatch:any)=>{
          this.matchToday.push(singleMatch.gameid);
        })
        ResolveMatchDataLast20.forEach((singleMatch:any)=>{
          singleMatch.gameStartDate=dayjs(singleMatch.gameStartDate).format("YYYY-MM-DD HH:mm");
        })
        this.matchTodayLast20=ResolveMatchDataLast20;
      })
  }

  initDate() {
    this.dateURL = this.location.path().split('/')[2];
    console.log(this.dateURL);

    this.currentMonthCentral = Number(this.dateURL.split('-')[1])-1;
    this.currentYearCentral = Number(this.dateURL.split('-')[0]);
    this.daySelected = Number(this.dateURL.split('-')[2]);
    this.currentMonthLeft = this.currentMonthCentral - 1;

    if (this.currentMonthCentral + 1 >= 11) {
      this.currentYearRight = this.currentYearCentral + 1;
      this.currentMonthRight = 0;
    } else {
      this.currentYearRight = this.currentYearCentral;
      this.currentMonthRight = this.currentMonthCentral + 1;
    }

    console.log(this.currentYearLeft, this.currentMonthLeft)
    if (this.currentMonthLeft < 0) {
      this.currentYearLeft = this.currentYearCentral - 1;
      this.currentMonthLeft = 11;
    } else {
      this.currentYearLeft = this.currentYearCentral;
      this.currentMonthLeft = this.currentMonthCentral - 1;
    }
    console.log(this.currentYearLeft, this.currentMonthLeft)
  };


  functionDateSelected(Year: number, Month: number, Day: number) {
    let date: string;
    if (Month + 1 >= 1 && Month + 1 <= 9)
      date = Year + "-0" + (Month + 1) + "-" + Day;
    else
      date = Year + "-" + (Month + 1) + "-" + Day;
    return date;
  }
  changeDate(Year: number, Month: number, Day: number): void {
    const url = `/calendario/${this.functionDateSelected(Year,Month,Day)}`;
    this.getApiServiceMatch.getSearchMatchDate(this.functionDateSelected(Year, Month, Day)).subscribe(match => {
      this.router.navigateByUrl(url, { replaceUrl: true });
      this.matchToday=[];
      match.forEach((singleMatch: any) => {
        this.matchToday.push(singleMatch.gameid);
      })
    })
  };
  isToday(Year: number, Month: number, Day: number) {
    if (Month + 1 >= 1 && Month + 1 <= 9)
      this.finalDay = Year + "-0" + (Month + 1) + "-" + Day;
    else
      this.finalDay = Year + "-" + (Month + 1) + "-" + Day;
    this.isTodayYear = Year;
    this.isTodayMonth = Month;
    this.isTodayDay = Day;
  };
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
  getNoOfDaysCentral() {
    let daysInMonthCentral = new Date(this.currentYearCentral, this.currentMonthCentral + 1, 0).getDate();

    // find where to start calendar day of week
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

    this.getNoOfDaysLeft();
    this.getNoOfDaysCentral();
    this.getNoOfDaysRight();
  }
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

    this.getNoOfDaysLeft();
    this.getNoOfDaysCentral();
    this.getNoOfDaysRight();
  }
}
