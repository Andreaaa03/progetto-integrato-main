import { Component, OnInit } from '@angular/core';
import * as dayjs from 'dayjs';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';

export class SignupForm {
  mail: string = '';
  ripetiPassword: string = '';
  first_name: string = "";
  last_name: string = "";
  birthDate: string = "";
  passwd: string = "";
  numeroTelefono: string = "";
  username: string = "";
  sesso: string = "";
}
export class SigninForm {
  mail: string = '';
  password: string = '';
}


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  constructor(private location: Location, private router: Router, private apiService: ApiService) { }
  ngOnInit(): void {
    this.token = localStorage.getItem('authToken');
  }

  dateToday = dayjs().format('YYYY-MM-DD'); //per settare la data di oggi quando entro nella pagina calendario

  // per gestire la navbar da mobile e da desktop
  isMobileMenuOpen: boolean = false;
  toggleMenu(isMobileMenuOpen: boolean) {
    this.isMobileMenuOpen = !isMobileMenuOpen;
  }

  token: string | null = null;
    
  //login e registrati
  modal: boolean = false;
  signupForm: SignupForm = new SignupForm();
  signinForm: SigninForm = new SigninForm();

  // per gestire la vista del registrati o login
  registrati: boolean = false;
  registratiView() {
    this.registrati = !this.registrati;
  }

  
  selectedOption: string = '';
  errorMessage: string = '';
  succesfulMessage: string = "";
  // mail: string = 'giorgio.modeo@gmail.com';
  // password: string = 'sonoio';

  /**
   * per accedere al profilo, posso inserire: username, e-mail o numero di telefono
   */
  login(): void {
    this.apiService.SendLogin(this.signinForm.mail, this.signinForm.password).subscribe(
      (risposta:any) => {
        this.token=risposta.token;
        localStorage.setItem('authToken', risposta.token);
        this.succesfulMessage = risposta.error;
        console.log('Registrazione andata a buon fine');
        // console.log('Registrazione andata a buon fine: ', risposta.status);
        this.router.navigate(['/profilo']);
      },
      (errore) => {
        this.errorMessage = "Accesso fallito! " + errore.error.error;
        console.log('Accesso fallito');
        // console.log('Accesso fallito: ', errore.error.error);
      }
    );
  }

  /**
   * creazione di un account
   */
  signup(){
    if(this.signupForm.passwd === this.signupForm.ripetiPassword){
      console.log(this.signupForm.first_name+"-"+ this.signupForm.last_name+"-"+ this.signupForm.birthDate+"-"+ this.signupForm.mail+"-"+ this.signupForm.passwd+"-"+ this.signupForm.numeroTelefono+"-"+ this.signupForm.username+"-"+ this.signupForm.sesso)
      this.apiService.SendSignup(this.signupForm.first_name, this.signupForm.last_name, this.signupForm.birthDate, this.signupForm.mail, this.signupForm.passwd, this.signupForm.numeroTelefono, this.signupForm.username, this.signupForm.sesso).subscribe(
        (risposta: any) => {
          this.succesfulMessage=risposta.error;
          console.log('Registrazione andata a buon fine');
          this.registratiView();
          // console.log('Registrazione andata a buon fine: ', risposta.status);
        },
        (errore) => {
          if(errore.status>=200 && errore.status<=299){  
            this.succesfulMessage="Registrazione effettuata";
            this.errorMessage="";
            console.log('Registrazione andata a buon fine');
            this.registratiView();
            // console.log('Registrazione andata a buon fine: ', errore.status);
          }else{
            this.errorMessage = "Registrazione fallita! " + errore.error.error;
            this.succesfulMessage="";
            console.log('Registrazione fallita');
            // console.log('Registrazione fallita: ', errore.error.error);
          }
        }
      )
    }else{
      this.errorMessage="Le password non corrispondo!";
    }
  }
}