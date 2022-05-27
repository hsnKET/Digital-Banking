import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CustomerService} from "../services/customer.service";

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css']
})
export class CustomerFormComponent implements OnInit {

  customerFormGroup:FormGroup;

  constructor(private fb:FormBuilder,private customerService:CustomerService) {
    this.customerFormGroup = this.fb.group(
      {
        name:fb.control(null),
        email:fb.control(null)
      }
    )
  }

  ngOnInit(): void {
  }

  saveCustomer() {

    //this.customerService


  }
}
