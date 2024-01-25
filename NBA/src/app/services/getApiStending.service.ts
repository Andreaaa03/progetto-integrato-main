import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { map } from 'rxjs';
import { Classifica, team } from '../models/typeStanding';



@Injectable({
    providedIn: 'root',
})
export class GetApiServiceStanding {
    constructor(private apiService: ApiService) { }

    standings: Classifica = {
        allStanding: {
            eastConference: [],
            westConference: [],
        },
        favouriteStandings: {
            eastConference: [],
            westConference: [],
        }
    }
    getSearchStanding() {
        this.standings.allStanding.eastConference = [];
        this.standings.allStanding.westConference = [];
        this.standings.favouriteStandings.eastConference = [];
        this.standings.favouriteStandings.westConference = [];
        return this.apiService.SearchStanding().pipe(
            map((resStanding: any) => {
                if (localStorage.getItem('authToken')) {
                    this.apiService.SearchFavouriteTeams(localStorage.getItem('authToken') as string).subscribe(
                        (resFavourite: any) => {
                            resStanding.forEach((singleTeam: any) => {
                                if (singleTeam.team.conferenceName === "East") {
                                    this.standings.allStanding.eastConference.push(singleTeam);
                                    singleTeam.favourite = false;
                                    for (let i = 0; i < resFavourite.length; i++) {
                                        if (singleTeam.team.id == resFavourite[i].id) {
                                            this.standings.favouriteStandings.eastConference.push(singleTeam);
                                            singleTeam.favourite = true;
                                        }
                                    }
                                } else if (singleTeam.team.conferenceName === "West") {
                                    this.standings.allStanding.westConference.push(singleTeam);
                                    singleTeam.favourite = false;
                                    for (let i = 0; i < resFavourite.length; i++) {
                                        if (singleTeam.team.id == resFavourite[i].id) {
                                            this.standings.favouriteStandings.westConference.push(singleTeam);
                                            singleTeam.favourite = true;
                                        }
                                    }
                                }
                            });
                        })
                } else {
                    resStanding.forEach((singleTeam: any) => {
                        if (singleTeam.team.conferenceName === "East") {
                            this.standings.allStanding.eastConference.push(singleTeam);
                        } else if (singleTeam.team.conferenceName === "West") {
                            this.standings.allStanding.westConference.push(singleTeam);
                        }
                    });
                }
                return this.standings as Classifica;
            })
        )
    }
}