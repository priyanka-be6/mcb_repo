import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {AuthGuard} from './auth.guard';
import {NewTransactionComponent} from './components/new_transaction/new_transaction.component';
import { SubmittedTransactionComponent } from './components/submitted_transactions/submitted_transactions.component';
import { CustomerFormComponent } from './components/customer_form/customer_form.component';

const routes: Routes = [
    {path: 'customer_form', component: CustomerFormComponent},
    {path: 'new_transaction', component: NewTransactionComponent},
    {path: 'submitted_transactions', component: SubmittedTransactionComponent},
    {path: 'login', component: LoginComponent},
    {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
    {path: '**', component: DashboardComponent, canActivate: [AuthGuard]}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
