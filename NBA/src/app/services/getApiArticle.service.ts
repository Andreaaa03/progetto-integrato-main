import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { map } from 'rxjs';
import * as dayjs from 'dayjs';
import { cardArticle, detailArticle } from '../models/typeArticle';


@Injectable({
    providedIn: 'root',
})
export class GetApiServiceArticle {
    constructor(private apiService: ApiService) { }

    getSearchAllArticle() {
        return this.apiService.SearchAllArticle().pipe(
            map((res: any) => {
                return res as cardArticle;
            })
        )
    }

    getSearchSingleArticle(id:string) {
        return this.apiService.SearchArticle(id).pipe(
            map((res: any) => {
                res.blog.giorno=dayjs(res.blog.creazione).format("DD-MM-YYYY");
                res.blog.orario=dayjs(res.blog.creazione).format("HH:mm");
                return res as detailArticle;
            })
        )
    }
}
