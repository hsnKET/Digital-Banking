import { Injectable } from '@angular/core';
import {User} from "../model/user.model";
const TOKEN_KEY = 'auth-token-mine';
const USER_KEY = 'auth-user-mine';
@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }
  signOut(): void {
    window.sessionStorage.clear();
    window.location.reload();
  }
  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }
  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }
  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }

  public isAdmin():boolean{
    let isAdm = false;

    if (this.getUser() && this.getUser().appRoles){
      (<User>this.getUser()).appRoles
        .forEach(role => {
          if (role.roleName=="ADMIN")
            isAdm = true;
        })
    }
    return isAdm;
  }
  public  isLogin():boolean{
    return !!this.getToken();
  }
}
