import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CustomerService} from "../services/customer.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {Customer, CustomerPage} from "../model/customer.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {TokenStorageService} from "../services/token-storage.service";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customersPage !: CustomerPage;
  customers !: Customer[];
  errorMessage !: string;
  MyformGroup !:FormGroup;
  currentPage :number = 0;
  pageSize :number = 2;
  totalPage !:number;
  isAdmin: boolean = false;

  constructor(private customerService:CustomerService,
              private fb:FormBuilder,
              private router : Router,
              private tokenStorage:TokenStorageService) {

  }
  ngOnInit(): void {

    this.isAdmin=this.tokenStorage.isAdmin();

    this.MyformGroup = this.fb.group({
      keyword:this.fb.control("")
    });

    this.searchCustomer()

  }

  searchCustomer() {

    let kw = this.MyformGroup?.value.keyword;
    this
      .customerService.searchCustomers(kw,this.currentPage,this.pageSize)
      .subscribe(value => {
          this.customersPage = value;
          this.customers = this.customersPage.customerDTOS;
          this.currentPage = this.customersPage.currentPage;
          this.pageSize = this.customersPage.pageSize;
          this.totalPage = this.customersPage.totalPages;
        },
        err => {
          this.errorMessage = err.message;
          return throwError(err);
        })

  }

  deleteCustomer(cutomer:Customer) {
    this.customerService.deleteCustomers(cutomer.id)
      .subscribe({
        next:(reps)=>{
          this.customers = this.customers
            .slice(this.customers.indexOf(cutomer))
        },
        error:(err)=>{
            console.log(err)
        }
      })
  }

  detailsCustomer(customer: Customer) {
    this.router.navigateByUrl("/customer-accounts/"+customer.id,{state :customer});
  }

  toPage(page: number) {
    this.currentPage=page;
    this.searchCustomer();
  }
}
