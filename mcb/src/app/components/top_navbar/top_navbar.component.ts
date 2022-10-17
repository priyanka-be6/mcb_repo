import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import { Router } from '@angular/router';

@Component({
    selector: 'top_navbar',
    templateUrl: './top_navbar.component.html',
    styleUrls: ['./top_navbar.component.css']
})
export class NavBarTopComponent implements OnInit {
    Username = sessionStorage.getItem('username');
    constructor(private authenticationService: AuthenticationService,private router: Router) {}

    ngOnInit(): void {}
    logout() {
        this.authenticationService.logout();
    }
    createTransaction(){
        this.router.navigate(['/customer_form']);
    }
    viewTransactions(){
        this.router.navigate(['/submitted_transactions']);
    }
    gotoDashboard(){
        this.router.navigate(['/dashboard']);
    }
}
