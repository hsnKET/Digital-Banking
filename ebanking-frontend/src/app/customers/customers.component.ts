import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CustomerService} from "../services/customer.service";
import {catchError, Observable, throwError} from "rxjs";
import {Customer} from "../model/customer.model";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers !: Observable<Array<Customer>>;
  errorMessage !: string;
  formGroup !:FormGroup;


  constructor(private customerService:CustomerService) {

  }
  ngOnInit(): void {
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

}