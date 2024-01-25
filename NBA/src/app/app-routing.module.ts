import { NgModule, inject } from '@angular/core';
import { ActivatedRouteSnapshot, RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './views/home-page/home-page.component';
import { ClassificaPageComponent } from './views/classifica-page/classifica-page.component';
import { SquadrePageComponent } from './views/squadre-page/squadre-page.component';
import { StoriaERegolePageComponent } from './views/storia-e-regole-page/storia-e-regole-page.component';
import { BlogPageComponent } from './views/blog-page/blog-page.component';
import { CalendarioPageComponent } from './views/calendario-page/calendario-page.component';
import { ErrorePageComponent } from './views/errore-page/errore-page.component';
import { GetApiServiceStanding } from './services/getApiStending.service';
import { GetApiServiceTeams } from './services/getApiTeams.service';
import { PartitaPageComponent } from './views/partita-page/partita-page.component';
import { SquadraDetailPageComponent } from './views/squadra-detail-page/squadra-detail-page.component';
import { GetApiServiceSingleTeam } from './services/getApiSingleTeam.service';
import { ArticoloDetailPageComponent } from './views/articolo-detail-page/articolo-detail-page.component';
import { ProfiloPageComponent } from './views/profilo-page/profilo-page.component';
import { GetApiServiceMatch } from './services/getApiMatch.service';
import * as dayjs from 'dayjs';
import { GetApiServiceArticle } from './services/getApiArticle.service';
import { GetApiServiceProfilo } from './services/getApiProfile.service';
import { GetApiServiceComment } from './services/getApiComment.service';
import { ApiService } from './services/api.service';


const routes: Routes = [
  {
    path: "home", component: HomePageComponent, resolve: {
      ResolveStanding: () => {
        // classifica 
        return inject(GetApiServiceStanding).getSearchStanding();
      },
      ResolveMatchData: () => {
        // data per giorno
        const data = dayjs().format("YYYY-MM-DD");
        return inject(GetApiServiceMatch).getSearchMatchDate(data!);
      },
      ResolveAllArticle: () => {
        // articoli 
        return inject(GetApiServiceArticle).getSearchAllArticle();
      }
    }, title: "SLAM STATS"
  },
  {
    path: "classifica", component: ClassificaPageComponent, resolve: {
      ResolveStanding: () => {
        // classifica
        return inject(GetApiServiceStanding).getSearchStanding();
      }
    }, title: "SLAM STATS - Classifica"
  },
  {
    path: "calendario/:data", component: CalendarioPageComponent, resolve: {
      ResolveMatchData: (route: ActivatedRouteSnapshot) => {
        // partite per giorno
        return inject(GetApiServiceMatch).getSearchMatchDate(route.paramMap.get("data")!);
      },
      ResolveMatchDataLast20: () => {
        // ultimi 20 partite a partire da ieri
        let yesterday = dayjs().subtract(1, 'day').format('YYYY-MM-DD');
        return inject(GetApiServiceMatch).getSearchMatchDataLast20(yesterday!);
      }
    }, title: "SLAM STATS - Calendario"
  },
  {
    path: "squadre", component: SquadrePageComponent, resolve: {
      ResolveTeams: () => {
        // tutte e squadre
        return inject(GetApiServiceTeams).getSearchTeams();
      }
    }, title: "SLAM STATS - Squadre"
  },
  {
    path: "squadraDetail/:id", component: SquadraDetailPageComponent, resolve: {
      ResolveSingleTeamStatistics: (route: ActivatedRouteSnapshot) => {
        // dati statistici singola squadra
        return inject(GetApiServiceSingleTeam).getSearchSingleTeamStatistics(route.paramMap.get("id")!);
      },
      ResolveSingleTeamCalendar: (route: ActivatedRouteSnapshot) => {
        // calendario singola squadra
        return inject(GetApiServiceSingleTeam).getSearchSingleTeamCalendar(route.paramMap.get("id")!);
      },
      ResolveSingleTeamPlayer: (route: ActivatedRouteSnapshot) => {
        // giocatori con info varie per singola squadra
        return inject(GetApiServiceSingleTeam).getSearchSingleTeamPlayer(route.paramMap.get("id")!);
      }
    }, title: "SLAM STATS - Squadra"
  },
  {
    path: "tabellino/:id", component: PartitaPageComponent, resolve: {
      ResolveMatchStats: (route: ActivatedRouteSnapshot) => {
        // statistiche di una partita
        return inject(GetApiServiceMatch).getSearchMatchStats(route.paramMap.get("id")!);
      },
      ResolveCommentForGame: (route: ActivatedRouteSnapshot) => {
        // tutte i commenti del singolo articolo
        return inject(GetApiServiceComment).getSearchAllCommentsGame(route.paramMap.get("id")!);
      }
    }, title: "SLAM STATS - Tabellino"
  },
  { path: "storia&regole/:page", component: StoriaERegolePageComponent, title: "SLAM STATS - Storia&Regole" },
  {
    path: "blog", component: BlogPageComponent, resolve: {
      ResolveAllArticle: () => {
        // tutti gli articoli
        return inject(GetApiServiceArticle).getSearchAllArticle();
      }
    }, title: "SLAM STATS - Blog"
  },
  {
    path: "profilo", component: ProfiloPageComponent, resolve: {
      ResolveFavouriteTeams: () => {
        // squadre preferite
        return inject(GetApiServiceProfilo).getSearchFavouriteTeams(localStorage.getItem('authToken')!);
      },
      ResolveTeams: () => {
        // tutti le squadre
        return inject(GetApiServiceTeams).getSearchTeams();
      },
      ResolveFavouriteArticle: () => {
        // articoli preferiti
        return inject(GetApiServiceProfilo).getSearchFavouriteArticle(localStorage.getItem('authToken')!);
      },
      ResolveCommentForUser: () => {
        // tutte i commenti del singolo articolo
        return inject(GetApiServiceComment).getSearchAllCommentsUser(localStorage.getItem('authToken')!);
      },
      ResolveInfoUser: () => {
        return inject(GetApiServiceProfilo).getSearchInfoUser(localStorage.getItem('authToken')!);
      },
      ResolveSeguiti: () => {
        return inject(GetApiServiceProfilo).getSearchSeguiti(localStorage.getItem('authToken')!);
      }
    }, title: "SLAM STATS - Profilo"
  },
  {
    path: "articolo/:id", component: ArticoloDetailPageComponent, resolve: {
      ResolveArticle: (route: ActivatedRouteSnapshot) => {
        // tutte le info del singolo articolo
        return inject(GetApiServiceArticle).getSearchSingleArticle(route.paramMap.get("id")!);
      },
      ResolveCommentForArticle: (route: ActivatedRouteSnapshot) => {
        // tutte i commenti del singolo articolo
        return inject(GetApiServiceComment).getSearchAllCommentsArticle(route.paramMap.get("id")!);
      }
    }, title: "SLAM STATS - Articolo"
  },
  { path: "", redirectTo: "home", pathMatch: "full" }, //prima di pagina d'errore
  { path: "errore", component: ErrorePageComponent, pathMatch: "full" },
  { path: "**", component: ErrorePageComponent, title: "ERR. 404 - pagina non trovata" },
];

@NgModule({
  // imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'top' })],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
