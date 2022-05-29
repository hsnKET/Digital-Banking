import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../environments/environment";
import {UserLogin} from "../model/user-login.model";
import {User} from "../model/user.model";
import {AccountDetails} from "../model/account.model";
import {TokenResponse} from "../model/token-response.model";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }



  login(userLogin:any): Observable<TokenResponse> {

    return this.http.post<TokenResponse>(environment.baseUrl + 'login',userLogin);
  }

  findUser(token:String): Observable<User> {
    return this.http.post<User>(environment.baseUrl + 'findUser/'+token,httpOptions);
  }

  register(userLogin:UserLogin): Observable<User> {
    return this.http.post<User>(environment.baseUrl + 'users', userLogin, httpOptions);
  }

}
