import { Injectable } from '@angular/core';
import {AccountDetails} from "../model/account.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http:HttpClient) { }

  public getAccount(accountId : string, page : number, size : number):Observable<AccountDetails>{
    return this.http.get<AccountDetails>(environment.baseUrl+"accounts/"+accountId+"/pageOperations?page="+page+"&size="+size);
  }

}
