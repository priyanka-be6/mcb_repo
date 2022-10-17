import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {ApiService} from 'src/app/services/api.service';
import { apipaths } from 'src/environments/api-paths';
import { environment } from 'src/environments/environment';
import { catchError, tap } from 'rxjs/operators';

@Injectable()
export class CustomerFormService {

    constructor(private apiService: ApiService, private router: Router) {
    }

    getTxn(id) {
        let siteUrl = `${environment.HOST}${apipaths.transaction}`+'/'+id;

        return this.apiService.get(siteUrl).pipe(
            tap((resdata: any) => {
              return resdata;
            }), catchError( error => {
              return error;
            })
        )
    }

}
