import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validator, Validators} from "@angular/forms";
import {CustomerService} from "../services/customer.service";

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css']
})
export class NewCustomerComponent implements OnInit {

  formGroup!:FormGroup;
  successMessage!: string;
  errorMessage!: string;
  constructor(private formBuilder:FormBuilder,
              private customerService:CustomerService) {

    this.formGroup = formBuilder.group(
      {
        name:this.formBuilder.control("",[Validators.required,
          Validators.min(3)]),
        email:this.formBuilder.control("",[Validators.email,Validators.required]),

      }
    )
  }

  ngOnInit(): void {
  }

  addCustomer(){
    let customer = this.formGroup.value;
   this.customerService
      .addCustomers(customer)
      .subscribe({
        next:(data)=>{
          this.successMessage = "Customer has been added Successfully!"
        },
        error:(err)=>{
          this.errorMessage = err.message;
        }
      })
  }



}
