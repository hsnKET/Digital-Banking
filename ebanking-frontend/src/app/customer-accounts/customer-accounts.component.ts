import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../model/customer.model";
import {AccountsService} from "../services/accounts.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {Accounts} from "../model/accounts-model";
import {TokenStorageService} from "../services/token-storage.service";

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrls: ['./customer-accounts.component.css']
})
export class CustomerAccountsComponent implements OnInit {

  customerId! : number ;
  customer! : Customer;
  accounts!:Observable<Array<Accounts>>;
  private errorMessage: any;
  isAdmin: boolean=false;
  constructor(private route : ActivatedRoute,
              private accountService:AccountsService,
              private router : Router,
              private tokenStorage:TokenStorageService) {
    this.customer=this.router.getCurrentNavigation()?.extras.state as Customer;
  }

  ngOnInit(): void {
    this.isAdmin=this.tokenStorage.isAdmin();
    this.customerId = this.route.snapshot.params['id'];
    this.accounts = this.accountService
      .getAccounts(this.customerId)
      .pipe(
        catchError(err => {
          this.errorMessage = err.message;
          return throwError(err);
        })
      );

  }


  deleteAccount(account: Accounts) {
    this.accountService.deleteAccount(account.id)
      .subscribe({
        next:(reps)=>{
          this.accounts = this.accounts
            .pipe(map(data=>{
              data.slice(data.indexOf(account),1)
              return data;
            }))
        },
        error:(err)=>{
          console.log(err)
        }
      })
  }

  detailsAcount(account: Accounts) {
    this.router.navigateByUrl("/accounts/"+account.id,{state :account});

  }

  goTo() {
    this.router.navigateByUrl("/new-account/"+this.customerId,{state :this.customer});
  }

}
