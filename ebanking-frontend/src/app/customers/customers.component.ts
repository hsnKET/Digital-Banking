import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CustomerService} from "../services/customer.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {Customer} from "../model/customer.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers !: Observable<Array<Customer>>;
  errorMessage !: string;
  MyformGroup !:FormGroup;


  constructor(private customerService:CustomerService,
              private fb:FormBuilder,
              private router : Router) {

  }
  ngOnInit(): void {

    this.MyformGroup = this.fb.group({
      keyword:this.fb.control("")
    });


    this.customers =
      this
        .customerService
        .getCustomers()
        .pipe(
          catchError(err => {
            this.errorMessage = err.message;
            return throwError(err);
          })
        );

  }

  searchCustomer() {

    let kw = this.MyformGroup?.value.keyword;
    this.customers = this
      .customerService.searchCustomers(kw)
      .pipe(
        catchError(err => {
          this.errorMessage = err.message;
          return throwError(err);
        })
      );

  }

  deleteCustomer(cutomer:Customer) {
    this.customerService.deleteCustomers(cutomer.id)
      .subscribe({
        next:(reps)=>{
          this.customers = this.customers
            .pipe(map(data=>{
              data.slice(data.indexOf(cutomer),1)
              return data;
            }))
        },
        error:(err)=>{
            console.log(err)
        }
      })
  }

  detailsCustomer(customer: Customer) {
    this.router.navigateByUrl("/customer-accounts/"+customer.id,{state :customer});
  }
}
