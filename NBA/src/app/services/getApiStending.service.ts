import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { map } from 'rxjs';
import { Classifica } from '../models/typeStanding';



@Injectable({
    providedIn: 'root',
})
export class GetApiServiceStanding {
    constructor(private apiService: ApiService) {}

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
            map((res: any) => {
                res.response.forEach((singleTeam: any) => {
                    if (singleTeam.conference.name === "east") {
                        this.standings.allStanding.eastConference.push(singleTeam);
                        if (singleTeam.preferiti === true) {
                            this.standings.favouriteStandings.eastConference.push(singleTeam);
                        }
                    } else {
                        this.standings.allStanding.westConference.push(singleTeam);
                        if (singleTeam.preferiti === true) {
                            this.standings.favouriteStandings.westConference.push(singleTeam);
                        }
                    }
                });
                return this.standings as Classifica;
            })
        )
    }
}