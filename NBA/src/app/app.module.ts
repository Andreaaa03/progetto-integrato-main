import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './views/home-page/home-page.component';
import { ClassificaPageComponent } from './views/classifica-page/classifica-page.component';
import { CalendarioPageComponent } from './views/calendario-page/calendario-page.component';
import { SquadrePageComponent } from './views/squadre-page/squadre-page.component';
import { StoriaERegolePageComponent } from './views/storia-e-regole-page/storia-e-regole-page.component';
import { BlogPageComponent } from './views/blog-page/blog-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { ErrorePageComponent } from './views/errore-page/errore-page.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ClassificaComponent } from './components/classifica-component/classifica.component';
import { PartiteDelGiornoPreviewComponent } from './components/partite-del-giorno-preview/partite-del-giorno-preview.component';
import { CardPartitaComponent } from './components/card-partita/card-partita.component';
import { PartitaPageComponent } from './views/partita-page/partita-page.component'
import { SquadraDetailPageComponent } from './views/squadra-detail-page/squadra-detail-page.component';
import { PostArticoloComponent } from './components/post-articolo/post-articolo.component';
import { ArticoloDetailPageComponent } from './views/articolo-detail-page/articolo-detail-page.component';
import { CardCommentoComponent } from './components/card-commento/card-commento.component';
import { ProfiloPageComponent } from './views/profilo-page/profilo-page.component';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';



@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    ClassificaPageComponent,
    CalendarioPageComponent,
    SquadrePageComponent,
    StoriaERegolePageComponent,
    BlogPageComponent,
    NavbarComponent,
    FooterComponent,
    ErrorePageComponent,
    ClassificaComponent,
    PartiteDelGiornoPreviewComponent,
    CardPartitaComponent,
    PartitaPageComponent,
    SquadraDetailPageComponent,
    ArticoloDetailPageComponent,
    PostArticoloComponent,
    CardCommentoComponent,
    ProfiloPageComponent,
    PieChartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
