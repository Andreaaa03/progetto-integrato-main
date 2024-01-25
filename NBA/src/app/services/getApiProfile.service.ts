import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { map } from 'rxjs';
import * as dayjs from 'dayjs';
import { detailArticle } from '../models/typeArticle';
import { team } from '../models/typeStanding';
import { otherUser, profilo } from '../models/typeProfilo';


@Injectable({
    providedIn: 'root',
})
export class GetApiServiceProfilo {
    constructor(private apiService: ApiService) { }

    getSearchFavouriteTeams(token: string) {
        return this.apiService.SearchFavouriteTeams(token).pipe(
            map((res: any) => {
                return res.reverse() as team[];
            })
        )
    }

    getSearchFavouriteArticle(token: string) {
        return this.apiService.SearchFavouriteArticle(token).pipe(
            map((res: any) => {
                return res.reverse() as detailArticle[];
            })
        )
    }

    getSearchInfoUser(token: string) {
        return this.apiService.SearchInfoUser(token).pipe(
            map((res:any)=>{
                return res as profilo;
            })
        )
    }
    
    getSearchSeguiti(token: string) {
        return this.apiService.Seguiti(token).pipe(
            map((res:any)=>{
                res.forEach((user:any)=>{
                    user.stato="NON SEGUIRE";
                })
                return res as otherUser[];
            })
        )
    }
}