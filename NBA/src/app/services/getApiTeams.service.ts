import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { map } from 'rxjs';
import { division, SingleTeamTeams } from '../models/typeTeams';

@Injectable({
    providedIn: 'root',
})
export class GetApiServiceTeams {
    constructor(private apiService: ApiService) {}

    teams:division = {
        NorthWest: [],
        SouthWest: [],
        SoutHeast: [],
        Atlantic: [],
        Central: [],
        Pacific: [],
    };
    getSearchTeams() {
        return this.apiService.SearchTeams().pipe(
            map((res: any) => {
                res.forEach((singleTeam: any) => {
                    if (singleTeam.leagues.standard.division === "Northwest") {
                        this.teams.NorthWest.push(singleTeam);
                    }
                    if (singleTeam.leagues.standard.division === "Southwest") {
                        this.teams.SouthWest.push(singleTeam);
                    }
                    if (singleTeam.leagues.standard.division === "Southeast") {
                        this.teams.SoutHeast.push(singleTeam);
                    }
                    if (singleTeam.leagues.standard.division === "Atlantic") {
                        this.teams.Atlantic.push(singleTeam);
                    }
                    if (singleTeam.leagues.standard.division === "Central") {
                        this.teams.Central.push(singleTeam);
                    }
                    if (singleTeam.leagues.standard.division === "Pacific") {
                        this.teams.Pacific.push(singleTeam);
                    }
                });
                return this.teams as division;
            })
        )
    }
}