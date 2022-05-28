import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {AccountDetails} from "../model/account.model";
import {catchError, Observable, throwError} from "rxjs";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

  accountFormGroup!: FormGroup;
  currentPage :number = 0;
  pageSize :number = 5;
  accountObservable! : Observable<AccountDetails>


  constructor(private fb: FormBuilder, private accountService: AccountsService) {
  }

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId: this.fb.control('')
    });

  }

  searchAccountByID() {
    let accountId : string =this.accountFormGroup.value.accountId;
    this.accountObservable=this.accountService.getAccount(accountId,this.currentPage, this.pageSize).pipe(
      catchError(err => {
        return throwError(err);
      })
    );
  }

  toPage(page: number) {
    this.currentPage=page;
    this.searchAccountByID();
  }


}
