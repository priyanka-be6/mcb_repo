import {Component, OnInit} from '@angular/core';
import {TransactionRequest} from './TransactionRequest';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../services/authentication.service';
import autoData from 'src/assets/mock/autoPopData.json';   
import { NewTransactionService } from './new_transaction.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-create-transaction',
    templateUrl: './new_transaction.component.html',
    styleUrls: ['./new_transaction.component.css'],
    providers:[NewTransactionService]
})
export class NewTransactionComponent implements OnInit {

    txnRequest: TransactionRequest;
    submitStatus: boolean = false;
    
    constructor(private router: Router,private nServ: NewTransactionService) {
    }
    CustData = autoData["responseXML"]["getCustomerInfoResponse"]["getCustomerInfoResult"]["CUST_INFO"];
    currencyList: string[] = ["AED", "EUR","CHF", "MUR" , "USD"];
    regionList: string[] = ["Port Louis", "Curepipe", "Vacoas", "Port Mathurin"];
    keyMap:any = {
      customerType:'new',
      customerReference:'CUS2022101401',
      customerNumber:'1234',
      customerName:'James',
      customerPhoneNumber:'45645456456',
      customerTransferAmount:'500',
      customerTransferCurrency:'MUR',
      beneficiaryBank:'MCB',
      beneficiaryAccNo:'33352423',
      paymentDetails:'test',
      creditDebitCardDetails:'test',
      region:'Port Louis',
      customer_address:'Street 1, Port Louis'
    }
    
    createTransForm: FormGroup = new FormGroup({
        customerType: new FormControl(  '',  [Validators.required] ),
        customerReference:new FormControl( '',  [Validators.pattern("(CUS\\d{9,13})"),Validators.required] ),
        customerNumber:new FormControl('',  [Validators.required] ),
        customerName: new FormControl('',  [Validators.required] ),
        customer_address: new FormControl('',  [Validators.required] ),
        customerPhoneNumber: new FormControl('', [Validators.pattern("^[0-9]*$"),Validators.required]),
        customerTransferAmount: new FormControl('',  [Validators.pattern("^[0-9]*$"),Validators.required] ),
        customerTransferCurrency: new FormControl('',  [Validators.required] ),
        beneficiaryBank: new FormControl('',  [Validators.pattern("^[a-zA-Z]*$"),Validators.required] ),
        beneficiaryAccNo: new FormControl('',  [Validators.required] ),
        paymentDetails: new FormControl('',  [Validators.pattern("^[a-zA-Z]*$"),Validators.required] ),
        creditDebitCardDetails: new FormControl('',  [Validators.required] ),
        region: new FormControl('',  [Validators.required] ),
    });

    ngOnInit(): void {
        this.setValidations();
        this.buildForm(); // Populate your form on component init
    }

    private buildForm() {
      this.createTransForm.setValue(this.keyMap);
    }
    setValidations() {
        const phoneControl = this.createTransForm.get("customerPhoneNumber");
        phoneControl.setValidators([
          Validators.pattern("^[0-9]*$"),
          Validators.required,
        ]);
    }
    getCustDetails(event){
      console.log(event.target.value);
      let custNo = event.target.value;
      let cusObj = {};
      if(custNo === "23423"){
        cusObj['customer_address'] = this.CustData["STREET_ADDR"] +', '+this.CustData["TOWN_COUNTRY"] +', '+this.CustData["COUNTRY"];
        cusObj['customerNumber'] = this.CustData["CUST_NO"];
        cusObj['customerName'] = this.CustData["SHORT_NAME"];
        cusObj['customerPhoneNumber'] = this.CustData['CONTACT_INFO_V7']['CONTACT_INFO_V7']['PHONE_LIST_V7']['PHONE_LIST_ITEM_V7']['PHONE'];
        this.createTransForm.patchValue(cusObj);
      }
    }
    submitForm() {
      let reqObj = this.createTransForm.value; 
      console.log('reqObj',reqObj);
      let tRequest = {
      "customerType": reqObj.customerType,
      "customerReference": reqObj.customerReference,
      "customerNumber": parseInt(reqObj.customerNumber),
      "customerName": reqObj.customerName,
      "customerPhoneNumber":parseInt(reqObj.customerPhoneNumber),
      "customerTransferAmount": parseInt(reqObj.customerTransferAmount),
      "customerTransferCurrency": reqObj.customerTransferCurrency,
      "beneficiaryBank": reqObj.beneficiaryBank,
      "beneficiaryAccNo": parseInt(reqObj.beneficiaryAccNo),
      "paymentDetails": reqObj.paymentDetails,
      "creditDebitCardDetails": reqObj.creditDebitCardDetails,
      "region": reqObj.region,
      "customerAddress":reqObj.customerAddress
      }
      this.nServ.createNewTransaction(tRequest).subscribe(result => {
        this.submitStatus = true;
        alert("Transaction Created")
        this.router.navigate(['submitted_transactions']);
      },error => {
        this.submitStatus = false;
      });
    }
    resetForm(){
      this.createTransForm.reset();
    }
}
