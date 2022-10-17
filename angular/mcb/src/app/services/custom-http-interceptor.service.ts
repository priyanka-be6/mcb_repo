import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError,tap} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class CustomHttpInterceptorService implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const token = sessionStorage.getItem('token');

        req = req.clone({
            setHeaders: {
                //Authorization: `Bearer ${token}`
                'Access-Control-Allow-Origin':'*'
            }
        });

        return next.handle(req).pipe(
            tap(event => {
            }, err=>{
            })
          );
    }

    handleError(error: HttpErrorResponse) {

        if (error.error instanceof ErrorEvent) {
            console.log('Client side error message', 'Client side error');
        } else {
            console.log('Server side error message :', error.error.message);
        }

        return throwError(error.error);
    }
}
