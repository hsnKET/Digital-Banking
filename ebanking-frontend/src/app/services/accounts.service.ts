import { Injectable } from '@angular/core';
import {AccountDetails} from "../model/account.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Accounts} from "../model/accounts-model";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http:HttpClient) { }

  public getAccount(accountId : string, page : number, size : number):Observable<AccountDetails>{
    return this.http.get<AccountDetails>(environment.baseUrl+"accounts/"+accountId+"/pageOperations?page="+page+"&size="+size);
  }

  public addAccount(idCustomer : number, type:string,initialBalance:number,amountType:number):Observable<Accounts>{
    return this.http.post<Accounts>(environment.baseUrl+"accounts/",
      {
              "idCustomer":idCustomer,
              "type":type,
              "initialBalance":initialBalance,
              "amountType":amountType

    });
  }

  public deleteAccount(accountId : string){
    return this.http.delete(environment.baseUrl+"accounts/"+accountId);
  }


  public getAccounts(customerId : number):Observable<Array<Accounts>>{
    return this.http.get<Array<Accounts>>(environment.baseUrl+"customers/accounts?id="+customerId);
  }

  public debit(accountId : string, amount : number, description:string){
    let data={accountId : accountId, amount : amount, description : description}
    return this.http.post(environment.baseUrl+"accounts/debit",data);
  }
  public credit(accountId : string, amount : number, description:string){
    let data={accountId : accountId, amount : amount, description : description}
    return this.http.post(environment.baseUrl+"accounts/credit",data);
  }
  public transfer(accountSource: string,accountDestination: string, amount : number, description:string){
    let data={accountSource, accountDestination, amount, description }
    return this.http.post(environment.baseUrl+"accounts/transfer",data);
  }

}
