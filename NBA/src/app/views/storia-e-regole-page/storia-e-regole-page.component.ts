import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

interface glossaryItem {
  titolo: string;
  sottotitolo: string;
  descrizione: string;
  id: number;
}

@Component({
  selector: 'app-storia-e-regole-page',
  templateUrl: './storia-e-regole-page.component.html',
  styleUrls: ['./storia-e-regole-page.component.css']
})
export class StoriaERegolePageComponent implements OnInit {
  constructor(private route: ActivatedRoute) { }
  ngOnInit(): void {
    const url = this.route.snapshot.url.join('/');
    // Dividere l'URL in segmenti
    const segments = url.split('/');
    // Ottenere l'ultimo segmento dell'URL
    const ultimaParteUrl = segments[segments.length - 1];
    // Confrontare l'ultimo segmento con la stringa desiderata
    ultimaParteUrl === "regole" ? this.functionSelectionRegole(true) : this.functionSelectionRegole(false);
  }
  selectedRegole: boolean = true
  isShorter: boolean = true
  selectedSection: number | null = null;

  /**
   * Funzione che cambia il valore della variabile booleana per cambiare vista della pagina
   * @param selectedRegole : boolean
   */
  functionSelectionRegole(selectedRegole: boolean) {
    this.selectedRegole = selectedRegole;
  }

  /**
   * Funzione che doveva ridurre un articolo in mobile che abbiamo deciso di non implementare
   * @param isShorter : boolean
   */
  functionIsShorter(isShorter: boolean) {
    this.isShorter = !isShorter;
  }

  //json contenente le informazione della view glossario
  jsonInfo: glossaryItem[] = [
    {
      "titolo": "GP",
      "sottotitolo": "PARTITE GIOCATE/GAME PLAYED",
      "descrizione": "La somma delle partite giocate dalla squadra o dal giocatore.",
      "id": 0,
    },
    {
      "titolo": "GS",
      "sottotitolo": "PARTITE INIZIATE/GAME STARTED",
      "descrizione": "La somma delle partite giocate in quintetto base dal giocatore.",
      "id": 1,
    },
    {
      "titolo": "MP; Min",
      "sottotitolo": "MINUTI GIOCATI/MINUTES PLAYED",
      "descrizione": "La somma dei minuti giocati dalla squadra o dal giocatore.",
      "id": 2,
    },
    {
      "titolo": "W/L",
      "sottotitolo": "PERCENTUALE DI VITTORIA/WINNING PERCENTAGE",
      "descrizione": "Il conteggio delle partite vinte e perse dalla squadra o dal giocatore.",
      "id": 3,
    },
    {
      "titolo": "W%",
      "sottotitolo": "PERCENTUALE DI VITTORIA/WINNING PERCENTAGE",
      "descrizione": "La percentuale di partite vinte dalla squadra o dal giocatore.",
      "id": 4,
    },
    {
      "titolo": "2PA; 2FGA",
      "sottotitolo": "TIRI DA 2 PUNTI TENTATI/TWO POINT ATTEMPTED; TWO POINT FIELD GOALS ATTEMPTED",
      "descrizione": "La somma dei tiri da 2 punti tentati.",
      "id": 5,
    },
    {
      "titolo": "2PM; 2FGM",
      "sottotitolo": "TIRI DA 2 PUNTI REALIZZATI/TWO POINT MADE; TWO POINT FIELD GOALS MADE",
      "descrizione": "La somma dei tiri da 2 punti realizzati.",
      "id": 6,
    },
    {
      "titolo": "2P; 2FG%",
      "sottotitolo": "PERCENTUALE DA 2 PUNTI/TWO POINT PERCENTAGE; TWO POINT FIELD GOALS PERCENTAGE",
      "descrizione": "La percentuale di realizzazione dei tiri da 2 punti.",
      "id": 7,
    },
    {
      "titolo": "%2P; 2FG FREQ",
      "sottotitolo": "FREQUENZA DI TIRO DA 2 PUNTI/TWO POINT FREQUENCY; TWO POINT FIELD GOALS FREQUENCY",
      "descrizione": "La percentuale dei tiri da 2 punti presi rispetto alla somma di tutti i tiri dal campo.",
      "id": 8,
    },
    {
      "titolo": "3PA",
      "sottotitolo": "TIRI DA 3 PUNTI TENTATI/THREE POINT ATTEMPTED",
      "descrizione": "La somma dei tiri da 3 punti tentati.",
      "id": 9,
    },
    {
      "titolo": "3PM",
      "sottotitolo": "TIRI DA 3 PUNTI REALIZZATI/THREE POINT MADE",
      "descrizione": "La somma dei tiri da 3 punti realizzati.",
      "id": 10,
    },
    {
      "titolo": "3P%",
      "sottotitolo": "PERCENTUALE DA 3 PUNTI/THREE POINT PERCENTAGE",
      "descrizione": "La percentuale di realizzazione dei tiri da 3 punti.",
      "id": 11,
    },
    {
      "titolo": "%3P; 3P FREQ",
      "sottotitolo": "FREQUENZA DI TIRO DA 3 PUNTI/THREE POINT FREQUENCY",
      "descrizione": "La percentuale dei tiri da 3 punti presi rispetto alla somma di tutti i tiri dal campo.",
      "id": 12,
    },
    {
      "titolo": "FGA",
      "sottotitolo": "TIRI DAL CAMPO TENTATI/FIELD GOALS ATTEMPTED",
      "descrizione": "La somma dei tiri dal campo (tiri da 2 e 3 punti) tentati.",
      "id": 13,
    },
    {
      "titolo": "FGM",
      "sottotitolo": "TIRI DAL CAMPO REALIZZATI/FIELD GOALS MADE",
      "descrizione": "La somma dei tiri dal campo (tiri da 2 e 3 punti) realizzati.",
      "id": 14,
    },
    {
      "titolo": "FG%",
      "sottotitolo": "PERCENTUALE DAL CAMPO/FIELD GOALS PERCENTAGE",
      "descrizione": "La percentuale di realizzazione dal campo.",
      "id": 15,
    },
    {
      "titolo": "FTA",
      "sottotitolo": "TIRI LIBERI TENTATI/FREE THROWS ATTEMPTED",
      "descrizione": "La somma dei tiri liberi tentati.",
      "id": 16,
    },
    {
      "titolo": "FTM",
      "sottotitolo": "TIRI LIBERI REALIZZATI/FREE THROWS MADE",
      "descrizione": "La somma dei tiri liberi realizzati.",
      "id": 17,
    },
    {
      "titolo": "FT%",
      "sottotitolo": "PERCENTUALE DEI TIRI LIBERI/FREE THROWS PERCENTAGE",
      "descrizione": "La percentuale di realizzazione dei tiri liberi.",
      "id": 18,
    },
    {
      "titolo": "PTS",
      "sottotitolo": "PUNTI/POINTS",
      "descrizione": "La somma dei punti realizzati.",
      "id": 19,
    },
    {
      "titolo": "REB",
      "sottotitolo": "RIMBALZI/REBOUNDS",
      "descrizione": "Un rimbalzo si verifica quando la palla, a seguito di un tiro, per l'appunto rimbalza sul tabellone o sul ferro e torna in campo nelle mani di un giocatore.",
      "id": 20,
    },
    {
      "titolo": "OR; OREB",
      "sottotitolo": "RIMBALZI OFFENSIVI/OFFENSIVE REBOUNDS",
      "descrizione": "La somma dei rimbalzi offensivi catturati.",
      "id": 21,
    },
    {
      "titolo": "DR; DREB",
      "sottotitolo": "RIMBALZI DIFENSIVI/DEFENSIVE REBOUNDS",
      "descrizione": "La somma dei rimbalzi difensivi catturati.",
      "id": 22,
    },
    {
      "titolo": "TR; TREB",
      "sottotitolo": "RIMBALZI TOTALI/TOTAL REBOUNDS",
      "descrizione": "La somma dei rimbalzi (offensivi e difensivi) catturati.",
      "id": 23,
    },
    {
      "titolo": "AST",
      "sottotitolo": "ASSIST/ASSIST",
      "descrizione": "La somma degli assist distribuiti.",
      "id": 24,
    },
    {
      "titolo": "ST; STL",
      "sottotitolo": "PALLE RUBATE/STEALS",
      "descrizione": "La somma delle palle rubate. Una palla persa si verifica quando la squadra in difesa intercetta in qualsiasi modo il pallone gestito dalla squadra che al momento è in attacco. ",
      "id": 25,
    },
    {
      "titolo": "TO; TOV",
      "sottotitolo": "PALLE PERSE/TURNOVERS",
      "descrizione": "La somma delle palle perse. Una palla persa si verifica quando la squadra che ha il possesso di palla in attacco lo perde in qualunque modo.",
      "id": 26,
    },
    {
      "titolo": "BLK",
      "sottotitolo": "STOPPATE/BLOCKS",
      "descrizione": "La somma delle stoppate fatte. Una stoppata si verifica quando un tiro diretto a canestro viene deviato prima di iniziare la fase di discesa della propria parabola. Può verificarsi una stoppata su qualsiasi tipo di tiro(sia da 2 punti che da 3 punti).",
      "id": 27,
    },
    {
      "titolo": "BLKA; BA",
      "sottotitolo": "STOPPATE SUBITE/BLOCKS AGAINST",
      "descrizione": "La somma delle stoppate subite.",
      "id": 28,
    },
    {
      "titolo": "PF",
      "sottotitolo": "FALLI FATTI/PERSONAL FOULS",
      "descrizione": "La somma dei falli commessi.",
      "id": 29,
    },
    {
      "titolo": "DF; PDF",
      "sottotitolo": "FALLI SUBITI/PERSONAL FOULS DRAWN",
      "descrizione": "La somma dei falli subiti.",
      "id": 30,
    }
  ]

  /**
   * funzione che permette lo scroll della vista al punto desiderato
   * @param i : number
   */
  scrollToSection(i: number): void {
    this.selectedSection = i;
    const section = document.getElementById('' + this.jsonInfo[i].id);

    //confronta il valore selezionato corrisponda a uno di quelli presenti ed esegue lo scoll
    if (section) {
      section.scrollIntoView({ behavior: 'smooth', block: 'center' });
    }

    setTimeout(() => {
      this.selectedSection = null;
    }, 1000);
  }

}
