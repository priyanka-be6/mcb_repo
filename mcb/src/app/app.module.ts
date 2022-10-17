import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {LoginComponent} from './components/login/login.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ApiService} from './services/api.service';
import {AuthGuard} from './auth.guard';
import {CustomHttpInterceptorService} from './services/custom-http-interceptor.service';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {NewTransactionComponent} from './components/new_transaction/new_transaction.component';
import { MatTableModule } from '@angular/material/table'  
import { SubmittedTransactionComponent } from './components/submitted_transactions/submitted_transactions.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { NavBarTopComponent } from './components/top_navbar/top_navbar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatRadioModule} from '@angular/material/radio';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import { CustomerFormComponent } from './components/customer_form/customer_form.component';

export const createTranslateLoader = (http: HttpClient) => {
    return new TranslateHttpLoader(http, './assets/i18/', '.json');
};

@NgModule({
    declarations: [
        AppComponent,
        NewTransactionComponent,
        LoginComponent,
        DashboardComponent,
        SubmittedTransactionComponent,
        NavBarTopComponent,
        CustomerFormComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatToolbarModule,
        MatIconModule,
        MatMenuModule,
        MatRadioModule,
        MatCardModule,
        MatInputModule,
        MatButtonModule,
        MatSelectModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: createTranslateLoader,
                deps: [HttpClient]
            }
        })
    ],
    providers: [ApiService, AuthGuard, {
        provide: HTTP_INTERCEPTORS,
        useClass: CustomHttpInterceptorService,
        multi: true
    }],
    bootstrap: [AppComponent]
})
export class AppModule {
}
