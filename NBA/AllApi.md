GET informazioni essenziali tutte le squadre: http://localhost:8080/teams/All fatta
GET calendario data: http://localhost:8080/games/date?date=2023-12-24 fatta
GET calendario team: http://localhost:8080/games/teamId?teamId=1 fatta
GET classifica W%: http://localhost:8080/standings/All fatta
GET statistiche Team: http://localhost:8080/teams/statistics?id=1 fatta
GET squadra essenziale: http://localhost:8080/teams/squadra?id=1 credo inutile
GET calendario tutte le partite tutte le squadre: http://localhost:8080/games/All credo inutile
GET tutti i player di una squadra: http://localhost:8080/player/teamId?id=1 fatta
GET singolo player: http://localhost:8080/player/Id?playerId=382 non serve
GET singola partita: http://localhost:8080/games/gameId?id=12478 fatta
GET tabellino: http://localhost:8080/games/partitaStat?idPartita=12478 fatta
GET ultime 20 partite: http://localhost:8080/games/Last20?date=2023-10-13 fatta
GET tutti gli articoli (pagina blog): http://localhost:8080/blog/simple fatta
GET articolo completo: http://localhost:8080/blog/completo?id=6 fatta

## LOGIN
POST profilo utente: http://localhost:8080/user/profilo fatta
{
token: ""
}
POST singup aggiornato: http://localhost:8080/user/signup fatta
{
"first_name": "Giorgio",
"last_name": "Modeo",
"birthDate": "2002-12-08",
"email": "giorgio.modeo@gmail.com",
"passwd": "sonoio",
"numeroTelefono": "1234567891"
"username":"domanico",
"sesso":"m"
}
POST singin aggiornato: http://localhost:8080/user/signin fatta
{
"email": "giorgio.modeo@gmail.com",
"passwd": "sonoio"
}
Oppure
{
"email": "domanico",
"passwd": "sonoio"
}

## PREFERITI
POST aggiunta/rimossione team preferiti: http://localhost:8080/user/teamPreferito fatta
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDU5MzA5MDEsImlhdCI6MTcwNTg5NDkwMX0.hxiylAzqxOhxw-QVWHbQKJZ5dlzRTyz21Kkwuvt4Lyg",
"idTeam": "9"
}
POST aggiunta/rimossione articoli preferiti: http://localhost:8080/user/articoloPreferito fatta
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDU5MzI2MDUsImlhdCI6MTcwNTg5NjYwNX0.Ik_a0i5kFkYMfMABpriFuMjrXjXiaECud0CgwYQx-Bg",
"idArticolo": "6" (questo e l'id blog)
}
POST vedere gli articoli preferiti di un utente: http://localhost:8080/user/getArticoliPreferiti fatta
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDU5MzI2MDUsImlhdCI6MTcwNTg5NjYwNX0.Ik_a0i5kFkYMfMABpriFuMjrXjXiaECud0CgwYQx-Bg"
}
POST vedere i team preferiti di un utente: http://localhost:8080/user/getTeamPreferiti fatta
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDU5MzI2MDUsImlhdCI6MTcwNTg5NjYwNX0.Ik_a0i5kFkYMfMABpriFuMjrXjXiaECud0CgwYQx-Bg"
}
POST vedere team e articoli di un utente: http://localhost:8080/user/teamArticoliPreferiti inutile
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk2MDc5NjcsImlhdCI6MTcwNjAwNzk2N30.EUrB1W4Nz0tw0OqwM9BaXCy-YP6Psc_9axkqKknu7hs"
}

## COMMENTI
POST commenta partita/blog: http://localhost:8080/commenti/add fatta
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk1Njg3NzQsImlhdCI6MTcwNTk2ODc3NH0.aRAtlfFt_v6zrWjs8W9UkedD65yXJWRYAfw2mcnztLw",
"testo":"risposta al commentoaaaaaaaaaa",
"id_games": 13773
}
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk1Njg3NzQsImlhdCI6MTcwNTk2ODc3NH0.aRAtlfFt_v6zrWjs8W9UkedD65yXJWRYAfw2mcnztLw",
"testo":"risposta al commentoaaaaaaaaaa",
"id_blog": 13773
}
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk1Njg3NzQsImlhdCI6MTcwNTk2ODc3NH0.aRAtlfFt_v6zrWjs8W9UkedD65yXJWRYAfw2mcnztLw",
"testo":"risposta al commentoaaaaaaaaaa",
"id_commento_padre": 13773
}
POST commenti del utente: http://localhost:8080/commenti/commentiUtente fatta ma manca il link all articolo
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk2MDc5NjcsImlhdCI6MTcwNjAwNzk2N30.EUrB1W4Nz0tw0OqwM9BaXCy-YP6Psc_9axkqKknu7hs"
}
GET commenti del articolo: http://localhost:8080/blog/commentiBlog?id=6 fatta
GET commenti del game: http://localhost:8080/games/commentiPartita?id=13774 fatta

## AMICI - SEGUITI - WOLLOWER
POST segui/rimuovi un utente: http://localhost:8080/user/seguiUtente
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDU5MzI2MDUsImlhdCI6MTcwNTg5NjYwNX0.Ik_a0i5kFkYMfMABpriFuMjrXjXiaECud0CgwYQx-Bg",
"idUtente":"5"
}
POST chi seguo io: http://localhost:8080/user/seguiti
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk2NDQ3ODEsImlhdCI6MTcwNjA0NDc4MX0.yQLT5HqeXZfTQgqSPm6lSOYe_EVXH5m_wQhAukqJtYQ"
}
POST chi ti followa: http://localhost:8080/user/followers
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk2NDQ3ODEsImlhdCI6MTcwNjA0NDc4MX0.yQLT5HqeXZfTQgqSPm6lSOYe_EVXH5m_wQhAukqJtYQ"
}
POST segui/rimuovi amici: http://localhost:8080/user/seguiAmico
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk2NDQ3ODEsImlhdCI6MTcwNjA0NDc4MX0.yQLT5HqeXZfTQgqSPm6lSOYe_EVXH5m_wQhAukqJtYQ",
"idUtente":"9"
}
POST amici stretti: http://localhost:8080/user/amiciSeguiti
{
"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZFVzZXIiOjUsInJvbGUiOiJ1dGVudGUiLCJleHAiOjE3MDk2NDQ3ODEsImlhdCI6MTcwNjA0NDc4MX0.yQLT5HqeXZfTQgqSPm6lSOYe_EVXH5m_wQhAukqJtYQ"
}
