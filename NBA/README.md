# SLAM STATS

## Indice

1. [Introduzione](#introduzione)
2. [Tecnologie](#tecnologie)
3. [Autori](#autori)
4. [Struttura del progetto](#struttura-del-progetto)
    1. [Frontend](#front-end)
    2. [Backend](#back-end)
    3. [Digital](#digital)
5. [Usage](#usage)
6. [Conclusioni](#conclusioni)


## Introduzione

Questo progetto è stato sviluppato utilizzando [Angular CLI](https://github.com/angular/angular-cli) version 16.2.7.

Il nostro sito web di statistiche NBA offre agli appassionati uno strumento completo per seguire da vicino la stagione di basket NBA. Oltre alle statistiche, il sito include un sistema di login per consentire agli utenti di lasciare commenti sotto gli articoli del blog e salvare le loro squadre preferite.

## Tecnologie

Front-end: Angular e Tailwind
Back-end: Java, SpringBoot e DB in mySQL

## Autori

Author: Lorenzo Garnero  
Mail: lorenzo.garnero@ed.its-ictpiemonte.it
Role: Front-end Developer

Author: Andrea Cavalca 
Mail: andrea.cavalca@ed.its-ictpiemonte.it
Role: Front-end Developer

Author: Riccardo Cacciatore
Mail: riccardo.cacciatore@ed.its-ictpiemonte.it
Role: Front-end Developer

Author: Giorgio Modeo
Mail: giorgio.modeo@ed.its-ictpiemonte.it
Role: Back-end Developer

Author: Armir 
Mail: armir.cacciatore@ed.its-ictpiemonte.it
Role: Back-end Developer

Author: Manuel
Mail: manuel.cacciatore@ed.its-ictpiemonte.it
Role: Back-end Developer

Author: Stefano Alfieri
Mail: stefano.alfieri@ed.its-ictpiemonte.it
Role: Digital Strategist

Author: Angelica Biondo
Mail: angelica.biondo@ed.its-ictpiemonte.it
Role: Digital Strategist

## Struttura del Progetto

## Front-end

Lo sviluppo front-end è partito suddividendo il progetto in cartelle, contenenti le varie pagine che da visualizzare[view](../NBA/src/app/views/). Successivamente si è passati allo sviluppo dei componenti[componets](../NBA/src/app/components/), ove era necessaria la ripetizione di una specifica parte di codice o di logica(motivo principale della scelta di angular).
Come primo step pratico si sono decisi gli spazi della viewport da utilizzare per dare al progetto un suo senso logico e una sua identità, come suggerito dai colleghi di digital strategist.
Una volta fatto ciò si è sviluppata la [navbar](../NBA/src/app/components/navbar) e il [footer](../NBA/src/app/components/footer) per iniziare a stabilirne le dimenzioni e per iniziare a collegare la navigazione delle pagine che è garantita dal  [app-routing.module.ts](../NBA/src/app/app-routing.module.ts).
Successivamente si è collegato il nostro servizio front-end a quello fornito dai nostri colleghi di back-end per ottenere i dati necessari [services](../NBA/src/app/services/), in questa cartella sono presenti tutti i file che ci permettono il collegamento all'api corrispondente e fanno tutti riferimento al file [api.service.ts](../NBA/src/app/services/api.service.ts) tra le api utilizzate riconoscere:

-[getApiArticle](../NBA/src/app/services/getApiArticle.service.ts),
-[getApiComment](../NBA/src/app/services/getApiComment.),
-[getApiMatch](../NBA/src/app/services/getApiMatch),
-[getApiProfile](../NBA/src/app/services/getApiProfile),
-[getApiSingleTeam](../NBA/src/app/services/getApiSingle),
-[getApiSteding](../NBA/src/app/services/getApiSteding.),
-[getApiTeam](../NBA/src/app/services/getApisTeam).

Queste sono quelle che abbiamo scelto di utilizzare tra quelle fornite dai nosti colleghi.

Detto ciò vi sarà una descrizione del nostro progetto, partendo dalla pagina più caratteristica, la [home-page](../NBA/src/app/views/home-page) che fa da preview a tutte le altre pagine, dato che al suo interno contiene componenti che danno un incipit del contenuto delle varie schermate che si andranno a visualizzare durate la visualizzazione del progetto.
La prima sezione che salta all'occhio è quella che illustra i dati delle partite odierne tramite un carosello di [card](../NBA/src/app/components/partite-del-giorno-preview), che lascia intendere il tipo di comunicazione che sarà presente nella pagina [calendario](../NBA/src/app/views/calendario-page/). Inoltre questa sezione permette all'utente di guardare la pagina dedicata al [tabellino](../NBA/src/app/views/partita-page) nella quale sono presenti tutte le informazioni e i dati di una partita.
Subito sotto a questa sezione è presente una riduzione della classifica divisa per conference, nella quale sono presenti molte funzioni logiche gestite dal componente [classifica-component](../NBA/src/app/components/classifica-component), ma più nello specifico dal file [ts](../NBA/src/app/components/classifica-component/classifica.component.ts) di quest'ultimo; inoltre la [classifica](../NBA/src/app/views/classifica-page) possiede un'apposita pagina.
Proseguendo nella vista della [home-page](../NBA/src/app/views/home-page) abbiamo un semplice link che ci conduce alla nostra pagina podcast, che si appoggia al servizio di spotify.
Concludiamo dunque la pagina con una preview degli ultimi tre [articoli](../NBA/src/app/components/post-articolo), che hanno la possipbilità di essere aperti e quindi di mostrare la pagina [articolo_dettaglio](../NBA/src/app/views/articolo-detail-page), della nostra pagina [blog](../NBA/src/app/views/blog-page), a cura dei nostri colleghi del corso di digital strategist.
Sempre utilizzando la [navbar](../NBA/src/app/components/navbar) è possibile arrivare alla pagina dedicata a tutte le [squadre](../NBA/src/app/views/squadre-page) che fornisce all'utente la visualizzazione di tutte e trenta le franchige della NBA, divise nelle rispettive conference di appartenenza. Premendo su ognuna di esse si naviga verso la pagina di [dettaglio](../NBA/src/app/views/squadra-detail-page) dedicata, la quale mostra due viewport. Nella prima di esse vengono fornite le statistiche e il calendario completo della squadra in questa stagione. Nella seconda invece abbiamo una lista di tutti i giocatori della squadra con le rispettive statistiche delle ultime cinque partite.
Proseguendo con l'utilizzo della [navbar](../NBA/src/app/components/navbar) navighiamo verso una pagina caratteristica del nostro progetto chiamatasi [regole_e_glossario](../NBA/src/app/views/storia-e-regole-page). Anche questa pagina presenta due viewport, una nella quale vengono spiegate alcune regole fondamentali della NBA e una nella quale viene visualizzato il glossario che introduce l'utente alla terminologia di base di cui ha bisogno per iniziare a seguire questo sport.
Concludendo con l'uso della nostra [navbar](../NBA/src/app/components/navbar), vorremmo soffermarci sulle funzioni implementate nel file [ts](../NBA/src/app/components/navbar/navbar.component.ts), le quali gestiscono il login e la registrazione degli utenti, permettendo l'accesso ad un area personale di [profilo](../NBA/src/app/views/profilo-page).
Infine se l'utente commette un errore durante la navigazione viene indirizzato verso una pagina di [errore](../NBA/src/app/views/errore-page).

## Back-end

### Struttura del Database

Il database è composto dalle seguenti tabelle:

- **Blog**: rappresenta il blog.  In esso sono contenuti i testi che verranno pubblicati sul portale al fine di “raccontare” il portale stesso al pubblico, oltre a fornire una deep immersion nella NBA nel suo complesso.
- **Commenti**: rappresenta i commenti che consentono agli utenti di esprimere la loro opinione ed eventualmente confrontarsi sulle tematiche più dibattute del momento.
- **Conference**: rappresenta le conference, ovvero le due principali divisioni all'interno della National Basketball Association (NBA). La Western Conference e la Eastern Conference determinano quali squadre si affronteranno nella corsa per le finali NBA
- **Division**: rappresenta le division, ovvero ulteriori divisioni all'interno delle due conference sopracitate. Ciascuna delle due conference, la Western e la Eastern Conference, è suddivisa ulteriormente in tre divisioni. Questa organizzazione è basata principalmente su criteri geografici per facilitare la pianificazione delle partite.
- **Games**: rappresenta le partite. Ad ogni partita viene assegnato un identificatore univoco. Sono inoltre presenti ulteriori dati (quali la data, il luogo, le squadre contendenti, i punti realizzati in ogni frazione di gioco, ect) che contribuiscono a fornire un quadro esaustivo del match in questione.
- **League**: rappresenta la lega. Contiene un ID univoco e due chiavi esterne, rispettivamente l'ID di Conference e l'ID di Division.
- **Paragrafo**: rappresenta il paragrafo, ovvero una porzione di testo  che tratta di un particolare argomento. Ad ogni paragrafo è stato assegnato un identificatore univoco e una chiave esterna che fa riferimento alla chiave primaria contenuta nella tabella Blog.
- **Player**: contiene i giocatori. Ogni giocatore presenta un'associazione con un identificatore univoco, oltre a una chiave esterna che individua la squadra di appartenenza(per l'identificativo della squadra fare riferimento alla tabella Team) e una serie di dati personali quali nome, cognome, data di nascita, altezza, peso, college di provenienza e numero di maglia.
- **Player_statistics**: rappresenta le performance di un determinato giocatore in una determinata partita. Contiene le chiavi esterne player_ID, teams_ID, game_ID che fanno rispettivamente riferimento alle chiavi primarie della tabella Player, Teams e Games.
- **Roles**: rappresenta i vari ruoli o gradi di autorità all'interno del sistema dove gli amministratori hanno il massimo controllo, gli utenti partecipano in modi diversi e i blogger si concentrano sulla creazione di contenuti.
- **Season**: rappresenta le annate sportive in cui sono stati disputati i vari incontri.
- **Teams**: rappresenta i dettagli relativi a ciascun team. Oltre all'identificatore univoco, ogni squadra presenta un nome, un logo, la città di riferimento e la lega di appartenenza(riferimento alla chiave primaria della tabella League).
- **Scores**: rappresenta i dettagli statistici di una determinata partita (chiavi esterne games_ID e teams_ID fanno rispettivamente riferimento alle tabelle Games e Teams). È possibile individuare tra gli altri, i punti realizzati da ciascuna squadra nelle varie frazioni di gioco.
- **Teams_statistics**: contiene una serie completa di dati sulle performance delle varie squadre ottenute durante un arco di tempo definito. Oltre alle chiavi esterne teams_ID e season_ID utili a identificare la squadra e la stagione in questione sono disponibili informazioni riguardanti il numero di punti realizzati, la percentuale di tiri realizzati, il numero di rimbalzi offensivi e difensivi, il numero di falli, assists, palle rubate e molto altro.
- **Team_standings**: rappresenta le prestazioni di ogni singolo team. Sono presenti oltre alle chiavi esterne teams_ID e season_ID(facenti riferimento alle chiavi primarie rispettivamente della tabella Teams e della tabella Season) numerosi altri dati quali le vittorie/sconfitte casalinghe e le vittorie/sconfitte in trasferta, la percentuale di successo/sconfitta e le serie di successo e sconfitta consecutive.
- **Utente**: rappresenta i dati personali dell'utente, tra i quali le credenziali di accesso email e password. Ad ogni utente viene associato un identificatore univoco.


## Digital

Uno sviluppo Digital è partito dalla Brand Identity, visualizzando Mission, Vision e nome del brand. A seguito è stata fatta un'analisi dei competitors nel settore dello sport scelto e poi una serie di idee e decisioni per il progetto riguardanti il suo sviluppo. Sono state pensate delle possibili personas in linea con il servizio che vogliamo offrire e infine siamo passati a delle idee creative e innovative, riguardanti i social e la community che vogliamo creare.
Dopo di che, siamo passati alla prototipazione e progettazione insieme ai Front-end riuscendo ad inserire la strategia pensata inizialmente per il progetto.
Abbiamo voluto iniziare con la versione Mobile per poi passare alla versione Desktop.
In contemporanea è stata creata la parte social: Instagram, Facebook, Spotify Podcast e X (post, copy, audio).
Sono stati creati degli articoli (6) pensati accuratamente e divisi per categorie ben distinte (Aneddoti, Match, Player, Team); con l'aggiunta di uno o più "tag" per ogni articolo (parole chiave).
Ci siamo dedicati a creare le presentazioni da portare al cliente con un'indea concordata da tutto il gruppo.
Tutto questo è stato fatto in ore extra-scolastiche, grazie all'organizzazione che è tenuta su Discord e Whatsapp, condividendo il materiale tramite Google Drive.


## Usage

Per permettere il completo utilizzo del nostro servizio l'utente deve essere fornito dei programmi elencati qui sotto:

-[IntelliJ](https://www.jetbrains.com/idea/download/?section=windows)
-[XAMPP](https://www.apachefriends.org/it/index.html)
-[Visual_Studio](https://code.visualstudio.com/download)

Per iniziare la procedura di avvio, una volta installati questi tre programmi, l'utente dovrà avviare XAMPP e assicurarsi di avere la porta 8080 libera. Dopodichè dovrà eseguire, con il tasto start, Apache e mySQL. Successivamente attraverso il bottone admin l'utente deve accedere a phpmyAdmin, attraverso la sezione importa, l'utente dovrà importare il databese fornito denominato nba.
A questo punto si deve aprire IntelliJ, precedentemente installato, e su di questo aprire il file denominato SlamStats e seguire questo [percorso](SlamStats/src/main/java/com.SlamDunkers.SlamStats/SlamStatsApplication) ed avviare il back-end del nostro prodotto.
In ultimo luogo l'utente deve accedere su Visual Studio eseguire con la tastiera il comando (Ctrl+j/Command+j) per aprire il terminale, una volta aperto si deve dirigere all'interno della cartella NBA eseguendo il comando (cd+percorso_richiesto_per_raggiungere_la_cartella). Una volta entrati nella cartella del progetto bisogna eseguire, sempre a terminale, il comando (npm install) per installare il pacchetto node_modules. Successivamente assicurarsi di avere installaro angular e se non si è fatto eseguire (npm install @angular/cli@16.2.10) e avviare il progetto con i comandi(npm start --open e ng serve --open).

## Conclusioni

Il gruppo di lavoro SlamDunkers ringrazia per l'attenzione e si augura che la vostra esperienza utente sia piacevole e che l'obiettivo sia stato raggiunto.

