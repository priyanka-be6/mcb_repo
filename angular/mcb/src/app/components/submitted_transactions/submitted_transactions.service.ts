import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {ApiService} from 'src/app/services/api.service';
import { apipaths } from 'src/environments/api-paths';
import { catchError, tap } from 'rxjs/operators';

@Injectable()
export class SubmittedTransactionService {

    constructor(private apiService: ApiService, private router: Router) {
    }

    getSubmittedTransaction() {
        let siteUrl = `${apipaths.transaction}`;
        return this.apiService.get(siteUrl).pipe(
            tap((resdata: any) => {
              return resdata;
            }), catchError( error => {
              return error;
            })
        )
    }

}
