import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

    constructor(private authenticationService: AuthenticationService,private router: Router) {}

    ngOnInit(): void {}

    createTransaction(){
        this.router.navigate(['/new_transaction']);
    }
    viewTransactions(){
        this.router.navigate(['/submitted_transactions']);
    }
}
