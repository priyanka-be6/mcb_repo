import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {ApiService} from 'src/app/services/api.service';
import { apipaths } from 'src/environments/api-paths';
import { TransactionRequest } from './TransactionRequest';
import { catchError, tap } from 'rxjs/operators';

@Injectable()
export class NewTransactionService {

    constructor(private apiService: ApiService, private router: Router) {
    }

    createNewTransaction(tReq: TransactionRequest) {
        let siteUrl = `${apipaths.transaction}`;
        return this.apiService.post(siteUrl, tReq).pipe(
            tap((resdata: any) => {
              return resdata;
            }), catchError( error => {
              return error;
            })
        )
    }

}
