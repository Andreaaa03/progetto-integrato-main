import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { map } from 'rxjs';
import * as dayjs from 'dayjs';
import { commento } from '../models/typeComment';


@Injectable({
    providedIn: 'root',
})
export class GetApiServiceComment {
    constructor(private apiService: ApiService) { }

    getSearchAllCommentsArticle(id: string) {
        return this.apiService.SearchCommentForArticle(id).pipe(
            map((res: any) => {
                if (res != null) {
                    res.forEach((comment: any) => {
                        comment.giorno = comment.giorno.slice(0, 3);
                    })
                    return res.reverse() as commento;
                }
                return res as commento;
            })
        )
    }

    getSearchAllCommentsGame(id: string) {
        return this.apiService.SearchCommentForGame(id).pipe(
            map((res: any) => {
                if (res != null) {
                    res.forEach((comment: any) => {
                        comment.giorno = comment.giorno.slice(0, 3);
                    })
                    return res.reverse() as commento;
                }
                return res as commento;
            })
        )
    }

    getSearchAllCommentsUser(token: string) {
        return this.apiService.SearchCommentForUser(token).pipe(
            map((res: any) => {
                if (res != null) {
                    res.forEach((comment: any) => {
                        comment.giorno = comment.giorno.slice(0, 3);
                    })
                    return res.reverse() as commento;
                }
                return res as commento;
            })
        )
    }
}
