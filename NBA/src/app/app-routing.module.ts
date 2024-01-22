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


const routes: Routes = [
  {
    path: "home", component: HomePageComponent, resolve: {
      ResolveStanding: () => {
        return inject(GetApiServiceStanding).getSearchStanding();
      },
      ResolveMatchData: (route: ActivatedRouteSnapshot) => {
        const data=dayjs().format("YYYY-MM-DD");
        return inject(GetApiServiceMatch).getSearchMatchDate(data!);
      }, 
    }, title: "SLAM STATS"
  }, //title: "SLAM STATS"
  {
    path: "classifica", component: ClassificaPageComponent, resolve: {
      ResolveStanding: () => {
        return inject(GetApiServiceStanding).getSearchStanding();
      }
    }, title: "SLAM STATS - Classifica"
  },
  {
    path: "calendario/:data", component: CalendarioPageComponent, resolve: {
      ResolveMatchData: (route: ActivatedRouteSnapshot) => {
        return inject(GetApiServiceMatch).getSearchMatchDate(route.paramMap.get("data")!);
      }, 
      ResolveMatchDataLast20: (route: ActivatedRouteSnapshot) => { 
        let yesterday=dayjs().subtract(1, 'day').format('YYYY-MM-DD');
        return inject(GetApiServiceMatch).getSearchMatchDataLast20(yesterday!); 
      }
    }, title: "SLAM STATS - Calendario"
  },
  {
    path: "squadre", component: SquadrePageComponent, resolve: {
      ResolveTeams: () => {
        return inject(GetApiServiceTeams).getSearchTeams();
      }
    }, title: "SLAM STATS - Squadre"
  },
  {
    path: "squadraDetail/:id", component: SquadraDetailPageComponent, resolve: {
      ResolveSingleTeamStatistics: (route: ActivatedRouteSnapshot) => {
        return inject(GetApiServiceSingleTeam).getSearchSingleTeamStatistics(route.paramMap.get("id")!);
      },
      ResolveSingleTeamCalendar: (route: ActivatedRouteSnapshot) => {
        return inject(GetApiServiceSingleTeam).getSearchSingleTeamCalendar(route.paramMap.get("id")!);
      },
      ResolveSingleTeamPlayer: (route: ActivatedRouteSnapshot) => {
        return inject(GetApiServiceSingleTeam).getSearchSingleTeamPlayer(route.paramMap.get("id")!);
      }
    }, title: "SLAM STATS - Squadra"
  },
  {
    path: "tabellino/:id", component: PartitaPageComponent, resolve: {
      ResolveMatchStats: (route: ActivatedRouteSnapshot) => {
        return inject(GetApiServiceMatch).getSearchMatchStats(route.paramMap.get("id")!);
      }
    }, title: "SLAM STATS - Tabellino"
  },
  { path: "storia&regole/:page", component: StoriaERegolePageComponent, title: "SLAM STATS - Storia&Regole" },
  { path: "blog", component: BlogPageComponent, title: "SLAM STATS - Blog" },
  {
    path: "profilo", component: ProfiloPageComponent, resolve: {
      ResolveTeams: () => {
        return inject(GetApiServiceTeams).getSearchTeams();
      }
    }, title: "SLAM STATS - Profilo"
  },
  { path: "articolo", component: ArticoloDetailPageComponent, title: "SLAM STATS - Articolo" },
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
