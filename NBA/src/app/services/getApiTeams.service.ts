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
        this.teams.NorthWest=[];
        this.teams.SouthWest=[];
        this.teams.SoutHeast=[];
        this.teams.Atlantic=[];
        this.teams.Central=[];
        this.teams.Pacific=[];
        return this.apiService.SearchTeams().pipe(
            map((res: any) => {
                res.forEach((singleTeam: any) => {
                    if (singleTeam.divisionName === "Northwest") {
                        this.teams.NorthWest.push(singleTeam);
                    }
                    if (singleTeam.divisionName === "Southwest") {
                        this.teams.SouthWest.push(singleTeam);
                    }
                    if (singleTeam.divisionName === "Southeast") {
                        this.teams.SoutHeast.push(singleTeam);
                    }
                    if (singleTeam.divisionName === "Atlantic") {
                        this.teams.Atlantic.push(singleTeam);
                    }
                    if (singleTeam.divisionName === "Central") {
                        this.teams.Central.push(singleTeam);
                    }
                    if (singleTeam.divisionName === "Pacific") {
                        this.teams.Pacific.push(singleTeam);
                    }
                });
                return this.teams as division;
            })
        )
    }
}