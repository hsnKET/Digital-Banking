import { Component, OnInit } from '@angular/core';
import {Customer} from "../model/customer.model";
import {catchError, Observable, throwError} from "rxjs";
import {Accounts} from "../model/accounts-model";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {AccountsService} from "../services/accounts.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import Swal from "sweetalert2";

@Component({
  selector: 'app-new-account',
  templateUrl: './new-account.component.html',
  styleUrls: ['./new-account.component.css']
})
export class NewAccountComponent implements OnInit {

  customerId! : number ;
  customer! : Customer;
  private errorMessage: any;
  accountFromGroup!: FormGroup;
  nametype!: String;

  constructor(
              private accountService:AccountsService,
              private route : ActivatedRoute,
              private fb: FormBuilder,
              private router:Router) {
    this.customer=this.router.getCurrentNavigation()?.extras.state as Customer;


  }

  ngOnInit(): void {

    this.customerId = this.route.snapshot.params['id'];

    this.accountFromGroup=this.fb.group({
      accountType : this.fb.control("current"),
      initialBalance : this.fb.control(0),
      amountType : this.fb.control(0)
    })

    this.nametype = "Over Draft";

  }

  handleCurrentAccount(){
    this.nametype = "Over Draft";
  }
  handleSavingAccount(){
    this.nametype = "Interest Rate";
  }

  handleAddAccount() {

    this.accountService.addAccount(this.customerId,this.accountFromGroup.value.type,this.accountFromGroup.value.initialBalance,this.accountFromGroup.value.amountType)
      .subscribe(data => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Account Added successfully',
            showConfirmButton: false,
            timer: 1500
          })
        },
        error => {
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Error ',
            showConfirmButton: false,
            timer: 1500
          })
        }
      )

  }
}
