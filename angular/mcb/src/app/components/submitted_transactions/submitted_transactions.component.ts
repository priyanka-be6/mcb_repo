import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { Router } from '@angular/router';
import { SubmittedTransactionService } from './submitted_transactions.service';

export interface UserData {
    id: string;
    customerName: string;
    customerTransferAmount:string;
    customerTransferCurrency:string;
    customerReference:string;
  }
  

const NAMES: string[] = [
'Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack', 'Charlotte', 'Theodore', 'Isla', 'Oliver',
'Isabella', 'Jasper', 'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'
];

@Component({
    selector: 'submitted-transaction',
    templateUrl: './submitted_transactions.component.html',
    styleUrls: ['./submitted_transactions.component.css'],
    providers:[SubmittedTransactionService]
})
​
export class SubmittedTransactionComponent implements OnInit {
    displayedColumns: string[] = [ "customerName", "customerTransferAmount","customerTransferCurrency","customerReference"];
    dataSource: MatTableDataSource<UserData>;
  
    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
    @ViewChild(MatSort, {static: true}) sort: MatSort;
  
    constructor(private router: Router,private sServ: SubmittedTransactionService) {
      // Create 100 users
      const users = Array.from({length: 100}, (_, k) => createNewUser(k + 1));
      //this.dataSource = new MatTableDataSource(users);
      this.getTxnList();
    }
  
    ngOnInit() {
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }
    getTxnList(){
      this.sServ.getSubmittedTransaction().subscribe(result => {
        // Assign the data to the data source for the table to render
        this.dataSource = new MatTableDataSource(result);
      },error => {
      });
    }
    createNewTransaction(){
      this.router.navigate(['/new_transaction']);
    }
    applyFilter(event: Event) {
      const filterValue = (event.target as HTMLInputElement).value;
      this.dataSource.filter = filterValue.trim().toLowerCase();
  
      if (this.dataSource.paginator) {
        this.dataSource.paginator.firstPage();
      }
    }
}
/** Builds and returns a new User. */
function createNewUser(id: number): UserData {
  const name = NAMES[Math.round(Math.random() * (NAMES.length - 1))] + ' ' +
      NAMES[Math.round(Math.random() * (NAMES.length - 1))].charAt(0) + '.';

  return {
    id: id.toString(),
    customerName: name,
    customerTransferAmount: Math.round(Math.random() * 100).toString(),
    customerTransferCurrency: name,
    customerReference:name
  };
}