import { Component, OnInit } from '@angular/core';
import * as dayjs from 'dayjs';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  constructor(private location: Location, private router: Router, private apiService: ApiService) { }
  dateToday = dayjs().format('YYYY-MM-DD');
  isMobileMenuOpen: boolean = false;
  toggleMenu(isMobileMenuOpen: boolean) {
    this.isMobileMenuOpen = !isMobileMenuOpen;
  }
  confrontUrl() {
    const urlAttuale = this.location.path().split("/")[1]
    const urlDesiderato = 'profilo';
    if (urlAttuale === urlDesiderato) {
      this.modal = false
    } else {
      this.modal = true;
    }
  }
  
  token: string | null = null;
  ngOnInit(): void {
    this.token = localStorage.getItem('authToken');
  }
  
  //login e registrati
  modal: boolean = false;
  registrati: boolean = false;

  registratiView() {
    this.registrati = !this.registrati;
  }

  email: string = 'giorgio.modeo@gmail.com';
  password: string = 'sonoio';
  errorMessage: string = '';

  selectedOption: string = '';

  login(): void {
    this.apiService.SendLogin(this.email, this.password).subscribe(
      (risposta:any) => {
        // Gestisci la risposta dal server dopo il login
        this.token=risposta.token;
        console.log(this.token);
        localStorage.setItem('authToken', risposta.token);
        // const authToken = localStorage.getItem('authToken');
        // localStorage.removeItem('authToken');
        this.router.navigate(['/profilo']);4
      },
      (errore) => {
        // Gestisci gli errori durante il login
        console.error('Errore durante il login:', errore);
      }
    );
  }
}