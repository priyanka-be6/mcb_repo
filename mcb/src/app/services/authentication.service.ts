import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {ApiService} from './api.service';
import {LoginRequest} from '../components/login/LoginRequest';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { apipaths } from 'src/environments/api-paths';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private apiService: ApiService, private router: Router) {
    }

    navigateToDashboardPageIfLoggedIn() {

        const loggedIn = this.isLoggedIn();

        if (loggedIn) {
            this.router.navigate(['dashboard']);
        }

    }

    getToken(): string {
        return sessionStorage.getItem('token');
    }

    isLoggedIn(): boolean {
        return !!this.getToken();
    }

    
    login(loginRequest: LoginRequest) : Observable<any> {
        // Send request
        /* sessionStorage.setItem('token', "resp.token");
        sessionStorage.setItem('username', loginRequest.username);
        
        return of({
            isOk: true,
            message: "Authentication success",
        }); */
        let siteUrl = `${apipaths.login}`+'username='+loginRequest.username+'&password='+loginRequest.password;
        
        try {
          
           return this.apiService.post(siteUrl, loginRequest).pipe(
            tap((resdata: any) => {
              console.log('resdata',resdata)
              let finResponse = {
                isOk: true,
                message:"Authentication success"
              }
              this.router.navigate(['dashboard']);
              return of(finResponse);
            }), catchError( error => {
              let msg = error;
              console.log('te',error)
              if(error?.error?.text){
                msg = error.error.text
              }
              console.log('msg',msg)
              let isOkBool = false;
              if(error.status === 200 && msg === "success"){
                isOkBool = true;
                sessionStorage.setItem('token', "sample.token");
                sessionStorage.setItem('username', loginRequest.username);
              }
              let finResponse = {
                isOk: isOkBool,
                message:msg
              }
              return of(finResponse);
            })
         )
        } catch {
          //check
          return of({
            isOk: false,
            message: "Authentication failed",
          });
        }
      }
    logout() {
        sessionStorage.removeItem('token');
        this.router.navigate(['']);
    }

}
