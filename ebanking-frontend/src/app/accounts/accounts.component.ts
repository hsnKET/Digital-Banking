import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {AccountDetails} from "../model/account.model";
import {catchError, Observable, throwError} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../model/customer.model";
import {Accounts} from "../model/accounts-model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

  accountFormGroup!: FormGroup;
  operationFromGroup!: FormGroup;
  currentPage :number = 0;
  pageSize :number = 5;
  accountObservable! : Observable<AccountDetails>
  errorMessage! :string ;
  private accountId: any;
  private account: Accounts;


  constructor(private fb: FormBuilder,
              private accountService: AccountsService,
              private route : ActivatedRoute,
              private router:Router) {

    this.account=this.router.getCurrentNavigation()?.extras.state as Accounts;

  }

  ngOnInit(): void {
    //

    this.accountId = this.route.snapshot.params['id'];


    this.accountFormGroup = this.fb.group({
      accountId: this.fb.control(this.accountId)
    });

    this.operationFromGroup=this.fb.group({
      operationType : this.fb.control(null),
      amount : this.fb.control(0),
      description : this.fb.control(null),
      accountDestination : this.fb.control(null)
    })

    this.searchAccountByID();


  }

  searchAccountByID() {
    let accountId : string =this.accountFormGroup.value.accountId;
    if (!accountId)return;
    this.accountObservable=this.accountService.getAccount(accountId,this.currentPage, this.pageSize).pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      })
    );
  }

  handleAccountOperation() {
    let accountId :string = this.accountFormGroup.value.accountId;
    let operationType=this.operationFromGroup.value.operationType;
    let amount :number =this.operationFromGroup.value.amount;
    let description :string =this.operationFromGroup.value.description;
    let accountDestination :string =this.operationFromGroup.value.accountDestination;
    if(operationType=='DEBIT'){
      this.accountService.debit(accountId, amount,description).subscribe({
        next : (data)=>{
          alert("Success Credit");
          this.operationFromGroup.reset();
          this.searchAccountByID();
        },
        error : (err)=>{
          console.log(err);
        }
      });
    } else if(operationType=='CREDIT'){
      this.accountService.credit(accountId, amount,description).subscribe({
        next : (data)=>{
          alert("Success Debit");
          this.operationFromGroup.reset();
          this.searchAccountByID();
        },
        error : (err)=>{
          console.log(err);
        }
      });
    }
    else if(operationType=='TRANSFER'){
      this.accountService.transfer(accountId,accountDestination, amount,description).subscribe({
        next : (data)=>{
          alert("Success Transfer");
          this.operationFromGroup.reset();
          this.searchAccountByID();
        },
        error : (err)=>{
          console.log(err);
        }
      });

    }

  }

  toPage(page: number) {
    this.currentPage=page;
    this.searchAccountByID();
  }


}
