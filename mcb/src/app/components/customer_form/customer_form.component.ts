import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators,FormArray} from '@angular/forms';
import {AuthenticationService} from '../../services/authentication.service';
import autoData from 'src/assets/mock/autoPopData.json';   
import { CustomerFormService } from './customer_form.service';

@Component({
    selector: 'customer_form',
    templateUrl: './customer_form.component.html',
    styleUrls: ['./customer_form.component.css'],
    providers:[CustomerFormService]
})
export class CustomerFormComponent implements OnInit {

    customerForm = {} as FormGroup;
    submitStatus: boolean = false;
    isEditCusNumber:boolean = true;
    educationList: any = [];
    
    validation_edumessage = {
      customerName: [{ type: 'required', message: 'School Name is required' }],
      degree: [{ type: 'required', message: 'Degree is required' }],
      startDt: [{ type: 'required', message: 'Start Date is required' }],
    };
    constructor(private fb: FormBuilder, private cServ: CustomerFormService) {
      
    }
    ngOnInit(): void {
      this.customerForm = this.fb.group({  
        quantities: this.fb.array([this.newQuantity()],[Validators.required]) ,  
      });  
    }
    get quantities() : FormArray {  
      return this.customerForm.get("quantities") as FormArray  
    }  
    
    getGroupControl(index) {
      return ((<FormArray>this.customerForm.get('quantities')).controls[index] as FormGroup)
    }
    
    newQuantity(): FormGroup {  
      let cObj = {  
        customer_type: new FormControl(  '',  [Validators.required] ),
        customerReference:new FormControl( '',  [Validators.pattern("(CUS\\d{9,13})"),] ),
        customer_number:new FormControl({'disabled':this.isEditCusNumber,value:''},  [] ),
        customerName: new FormControl('',  [] ),
        customerAddress: new FormControl('',  [] ),
        customer_phone_no: new FormControl('', []),
        customerTransferAmount: new FormControl('',  [Validators.pattern("^[0-9]*$"),] ),
        customerTransferCurrency: new FormControl('',  [] ),
        beneficiary_bank: new FormControl('',  [Validators.pattern("^[a-zA-Z]*$"),] ),
        beneficiary_acc_no: new FormControl('',  [] ),
        payment_details: new FormControl('',  [Validators.pattern("^[a-zA-Z]*$"),] ),
        credit_debit_card_details: new FormControl('',  [] ),
        region: new FormControl('',  [] ),
      };
      return this.fb.group(cObj)  
    }  
       
    addQuantity() {  
      let fg = this.newQuantity();
      this.quantities.push(fg);  
    }  
    resetQuantity(i:number) {  
      this.getGroupControl(i).reset();
    }  
    removeQuantity(i:number) {  
      this.quantities.removeAt(i);  
    }  
    onSubmit() {  
      //console.log(this.customerForm.value);  
    }  
    CustData = autoData["responseXML"]["getCustomerInfoResponse"]["getCustomerInfoResult"]["CUST_INFO"];
    currencyList: string[] = ["AED", "EUR","CHF", "MUR" , "USD"];
    regionList: string[] = ["Port Louis", "Curepipe", "Vacoas", "Port Mathurin"];
    
    setValidations(event,fgName,fName){
      let custType = event.value;
      console.log('custType',custType,fgName,fName)
      let taskListArrays = this.customerForm.get('quantities') as FormArray;
      if(fName === 'customer_type'){
        if (custType === 'new') {
          (taskListArrays.controls[fgName] as FormGroup).get("customer_phone_no").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("customerName").setValidators([Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("customerReference").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("customer_number").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("customerTransferAmount").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("customerTransferCurrency").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("beneficiary_bank").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("beneficiary_acc_no").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("payment_details").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("credit_debit_card_details").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("region").clearValidators();
          (taskListArrays.controls[fgName] as FormGroup).get("customerAddress").clearValidators();
          this.isEditCusNumber = true;
        } else {
          (taskListArrays.controls[fgName] as FormGroup).get("customer_phone_no").setValidators([Validators.pattern("^[0-9]*$"),Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("customerName").setValidators([Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("customerReference").setValidators([Validators.pattern("(CUS\\d{9,13})"),Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("customer_number").setValidators([Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("customerTransferAmount").setValidators([Validators.pattern("^[0-9]*$"),Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("customerTransferCurrency").setValidators([Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("beneficiary_bank").setValidators([Validators.pattern("^[a-zA-Z]*$"),Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("beneficiary_acc_no").setValidators([Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("payment_details").setValidators([Validators.pattern("^[a-zA-Z]*$"),Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("credit_debit_card_details").setValidators([Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("region").setValidators([Validators.required]);
          (taskListArrays.controls[fgName] as FormGroup).get("customerAddress").setValidators([Validators.required]);
          this.isEditCusNumber = false;
        }

        (taskListArrays.controls[fgName] as FormGroup).get("customer_phone_no").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("customerName").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("customerReference").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("customer_number").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("customerTransferAmount").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("customerTransferCurrency").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("beneficiary_bank").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("beneficiary_acc_no").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("payment_details").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("credit_debit_card_details").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("region").updateValueAndValidity();
        (taskListArrays.controls[fgName] as FormGroup).get("customerAddress").updateValueAndValidity();
      }
      if(fName === 'region'){
        if (custType === "Port Mathurin") {
          (taskListArrays.controls[fgName] as FormGroup).get("customerAddress").setValidators([Validators.required]);
        } else {
          (taskListArrays.controls[fgName] as FormGroup).get("customerAddress").clearValidators();
        }
        (taskListArrays.controls[fgName] as FormGroup).get("customerAddress").updateValueAndValidity();
      }
    }
    getCustDetails(event,fgName){
      let custNo = event.target.value;
      let cusObj = {};
      if(custNo === "23423"){
        cusObj['customerAddress'] = this.CustData["STREET_ADDR"] +', '+this.CustData["TOWN_COUNTRY"] +', '+this.CustData["COUNTRY"];
        cusObj['customer_number'] = this.CustData["CUST_NO"];
        cusObj['customerName'] = this.CustData["SHORT_NAME"];
        cusObj['customer_phone_no'] = this.CustData['CONTACT_INFO_V7']['CONTACT_INFO_V7']['PHONE_LIST_V7']['PHONE_LIST_ITEM_V7']['PHONE'];
        let taskListArrays = this.customerForm.get('quantities') as FormArray;
        (taskListArrays.controls[fgName] as FormGroup).patchValue(cusObj);
      } else {
        this.cServ.getTxn(custNo).subscribe(result => {
          cusObj['customerAddress'] = result.customerAddress;
          cusObj['customer_number'] = result.customerNumber;
          cusObj['customerName'] = result.customerName;
          cusObj['customer_phone_no'] = result.customerPhoneNumber;
          let taskListArrays = this.customerForm.get('quantities') as FormArray;
          (taskListArrays.controls[fgName] as FormGroup).patchValue(cusObj);
        },error => {
          alert("No Details found")
        });
      }

    }
}
